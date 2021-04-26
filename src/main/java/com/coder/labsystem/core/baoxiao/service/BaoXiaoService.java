package com.coder.labsystem.core.baoxiao.service;

import com.coder.labsystem.core.chuchai.repository.ChuChaiRepository;
import com.coder.labsystem.core.chuchai.repository.CustomChuChaiRepository;
import com.coder.labsystem.core.fapiao.repository.FaPiaoRepository;
import com.coder.labsystem.exception.DaoException;
import com.coder.labsystem.model.bo.ChuChaiBaoXiaoBO;
import com.coder.labsystem.model.query.ChuChaiQuery;
import com.coder.labsystem.model.vo.ChuChaiVo;
import com.coder.labsystem.model.vo.PageResp;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.List;

/**
 * @author : JQK
 * @date : 2021-04-25 16:30
 * @description :
 */
@Service
public class BaoXiaoService {

    private final FaPiaoRepository faPiaoRepository;
    private final ChuChaiRepository chuChaiRepository;
    private final CustomChuChaiRepository customChuChaiRepository;

    public BaoXiaoService(FaPiaoRepository faPiaoRepository, ChuChaiRepository chuChaiRepository,
                          CustomChuChaiRepository customChuChaiRepository) {
        this.faPiaoRepository = faPiaoRepository;
        this.chuChaiRepository = chuChaiRepository;
        this.customChuChaiRepository = customChuChaiRepository;
    }


    /**
     * 按用户列出某月的出差待报销情况
     * @param yearMonth
     * @return
     */
    public List<ChuChaiBaoXiaoBO> listChuChaiBaoXiaoMoney(YearMonth yearMonth) {
        if (yearMonth == null) {
            yearMonth = YearMonth.now().minusMonths(1);
        }
        List<ChuChaiBaoXiaoBO> baoXiaoBOList = customChuChaiRepository.listChuChaiBaoXiaoMoney(yearMonth);
        return baoXiaoBOList;
    }

    /**
     * 列出某人某月的出差报销情况
     * @param username
     * @param chuChaiQuery
     * @return
     */
    public PageResp listChuChaiBaoXiaoByUsername(String username, ChuChaiQuery chuChaiQuery) {
        Page<ChuChaiVo> chuChaiVos = customChuChaiRepository.getByUsernameAndBaoXiaoQuery(username, chuChaiQuery);
        PageResp<ChuChaiVo> pageResp = new PageResp<>(chuChaiVos);
        return pageResp;
    }

    /**
     * 确定已报销某月的所有出差
     * @return
     */
    public boolean baoXiaoChuChai(YearMonth yearMonth) {
        if (yearMonth == null) {
            yearMonth = YearMonth.now().minusMonths(1);
        }
        boolean updateState = customChuChaiRepository.updateChuChaiStateByYearMonth(yearMonth);
        if (!updateState) {
            throw new DaoException("状态更新错误");
        }
        return true;
    }
}
