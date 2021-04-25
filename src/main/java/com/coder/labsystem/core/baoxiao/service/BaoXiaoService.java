package com.coder.labsystem.core.baoxiao.service;

import com.coder.labsystem.core.chuchai.repository.ChuChaiRepository;
import com.coder.labsystem.core.fapiao.repository.FaPiaoRepository;
import com.coder.labsystem.model.query.BaoXiaoQuery;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Map;

/**
 * @author : JQK
 * @date : 2021-04-25 16:30
 * @description :
 */
@Service
public class BaoXiaoService {

    private final FaPiaoRepository faPiaoRepository;
    private final ChuChaiRepository chuChaiRepository;

    public BaoXiaoService(FaPiaoRepository faPiaoRepository, ChuChaiRepository chuChaiRepository) {
        this.faPiaoRepository = faPiaoRepository;
        this.chuChaiRepository = chuChaiRepository;
    }

    /**
     * 查询出某月需要报销的用户、金额等信息
     * @param yearMonth 要查询的年月
     * @return
     */
    public Map<String, Object> listBaoXiaoMoney(YearMonth yearMonth) {

        return null;
    }

    /**
     * 按用户名查询报销情况——出差记录、发票提交记录
     * @param username
     * @param baoXiaoQuery
     * @return
     */
    public Map<String, Object> listBaoXiaoByUser(String username, BaoXiaoQuery baoXiaoQuery) {

        return null;
    }



}
