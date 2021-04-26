package com.coder.labsystem.core.chuchai.repository;

import com.coder.labsystem.constant.BaoXiaoState;
import com.coder.labsystem.constant.CollectionName;
import com.coder.labsystem.model.bo.ChuChaiBaoXiaoBO;
import com.coder.labsystem.model.entity.ChuChai;
import com.coder.labsystem.model.query.ChuChaiQuery;
import com.coder.labsystem.model.vo.ChuChaiVo;
import com.coder.labsystem.util.MongoOperatorUtil;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * @author : JQK
 * @date : 2021-04-20 20:41
 * @description :
 */
@Repository
public class CustomChuChaiRepositoryImpl implements CustomChuChaiRepository {

    private final MongoTemplate mongoTemplate;

    public CustomChuChaiRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean updateChuChai(ChuChai chuChai) {
        Query query = query(where("id").is(chuChai.getId()));
        Update update = MongoOperatorUtil.beanToUpdate(chuChai);
        UpdateResult result = mongoTemplate.updateFirst(query, update, ChuChai.class, CollectionName.CHUCHAI_COLLECTION_NAME);
        return result.getModifiedCount() > 0 ? true : false;
    }

    @Override
    public Page<ChuChaiVo> getByUsernameAndBaoXiaoQuery(String username, ChuChaiQuery chuChaiQuery) {
        YearMonth yearMonth = chuChaiQuery.getYearMonth();
        // 该月的第一天
        LocalDate startDate = yearMonth.atDay(1);
        // 下个月的第一天
        LocalDate stopDate = startDate.with(TemporalAdjusters.firstDayOfNextMonth());
        // 分页查询时按出差日期排序
        PageRequest pageRequest = PageRequest.of(chuChaiQuery.getPageNum() - 1,
                chuChaiQuery.getPageSize(), Sort.Direction.ASC, "chuChaiDate");
        Query query = query(where("username").is(username)
                .and("state").is(chuChaiQuery.getState())
                .and("chuChaiDate").gte(startDate).lt(stopDate));
        Query queryWithPage = query.with(pageRequest);
        List<ChuChaiVo> chuChaiVoList = mongoTemplate.find(query, ChuChaiVo.class, CollectionName.CHUCHAI_COLLECTION_NAME);
        mongoTemplate.count(queryWithPage, CollectionName.CHUCHAI_COLLECTION_NAME);
        return new PageImpl(chuChaiVoList, pageRequest, chuChaiVoList.size());
    }

    @Override
    public List<ChuChaiBaoXiaoBO> listChuChaiBaoXiaoMoney(YearMonth yearMonth) {
        // 该月的第一天
        LocalDate startDate = yearMonth.atDay(1);
        // 下个月的第一天
        LocalDate stopDate = startDate.with(TemporalAdjusters.firstDayOfNextMonth());
        Query query = query(where("state").is(BaoXiaoState.UNTREATED)
                .and("chuChaiDate").gte(startDate).lt(stopDate));
        List<ChuChai> chuChais = mongoTemplate.find(query, ChuChai.class, CollectionName.CHUCHAI_COLLECTION_NAME);

        /* 按用户分组 */
        Map<String, List<ChuChai>> collect =
                chuChais.stream().collect(Collectors.groupingBy(item -> item.getUsername()));
        /* 计算出每个用户的金额 */
        List<ChuChaiBaoXiaoBO> result = new ArrayList<>(collect.size());
        for (Map.Entry<String, List<ChuChai>> entry : collect.entrySet()) {
            int money = 0;
            for (ChuChai chuChai : entry.getValue()) {
                money += chuChai.getChuChaiTime().getMoney();
            }
            // TODO：目前用户的真实名称没有做处理，全部为null
            ChuChaiBaoXiaoBO chuChaiBaoXiaoBO = new ChuChaiBaoXiaoBO(entry.getKey(), null, new BigDecimal(money));
            result.add(chuChaiBaoXiaoBO);
        }
        return result;
    }

    @Override
    public boolean updateChuChaiStateByYearMonth(YearMonth yearMonth) {
        // 该月的第一天
        LocalDate startDate = yearMonth.atDay(1);
        // 下个月的第一天
        LocalDate stopDate = startDate.with(TemporalAdjusters.firstDayOfNextMonth());
        Query query = query(where("chuChaiDate").gte(startDate).lt(stopDate));
        Update update = Update.update("", BaoXiaoState.PROCESSED);
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, ChuChai.class, CollectionName.CHUCHAI_COLLECTION_NAME);
        return updateResult.getMatchedCount() == updateResult.getModifiedCount();
    }
}
