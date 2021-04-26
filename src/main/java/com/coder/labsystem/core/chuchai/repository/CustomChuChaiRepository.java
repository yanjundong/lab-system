package com.coder.labsystem.core.chuchai.repository;

import com.coder.labsystem.model.bo.ChuChaiBaoXiaoBO;
import com.coder.labsystem.model.entity.ChuChai;
import com.coder.labsystem.model.query.ChuChaiQuery;
import com.coder.labsystem.model.vo.ChuChaiVo;
import org.springframework.data.domain.Page;

import java.time.YearMonth;
import java.util.List;

/**
 * @author : JQK
 * @date : 2021-04-20 20:40
 * @description : 使用 mongoTemplate 自实现的一些方法
 */
public interface CustomChuChaiRepository {

    /**
     * 更新出差记录
     * @param chuChai
     * @return
     */
    boolean updateChuChai(ChuChai chuChai);

    /**
     * 通过用户名和参数 查询出差记录 + 分页查询，并且按出差时间排序
     * @param username 用户名
     * @param chuChaiQuery  其他查询参数
     * @return
     */
    Page<ChuChaiVo> getByUsernameAndBaoXiaoQuery(String username, ChuChaiQuery chuChaiQuery);

    /**
     * 按用户列出某月的出差待报销情况
     * @param yearMonth
     * @return 用户的出差报销金额等信息
     */
    List<ChuChaiBaoXiaoBO> listChuChaiBaoXiaoMoney(YearMonth yearMonth);

    /**
     * 更新某年某月出差的报销状态
     * @param yearMonth 年月
     * @return 是否更新成功
     */
    boolean updateChuChaiStateByYearMonth(YearMonth yearMonth);

}
