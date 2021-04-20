package com.coder.labsystem.core.chuchai.repository;

import com.coder.labsystem.model.entity.ChuChai;

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

}
