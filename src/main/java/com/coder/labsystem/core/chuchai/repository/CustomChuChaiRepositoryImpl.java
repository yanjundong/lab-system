package com.coder.labsystem.core.chuchai.repository;

import com.coder.labsystem.model.entity.ChuChai;
import com.coder.labsystem.util.MongoOperatorUtil;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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

    // TODO 通过 _id 查询 记录
    @Override
    public boolean updateChuChai(ChuChai chuChai) {
        Query query = Query.query(Criteria.where("_id").is(new ObjectId(chuChai.getId())));
        Query query1 = query.with(Sort.by(Sort.Direction.DESC, "_id"));
        Update update = MongoOperatorUtil.beanToUpdate(chuChai);
        UpdateResult result = mongoTemplate.updateFirst(query, update, ChuChai.class);
        return result.getModifiedCount() > 0 ? true : false;
    }
}
