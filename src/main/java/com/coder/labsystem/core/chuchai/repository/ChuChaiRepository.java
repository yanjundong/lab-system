package com.coder.labsystem.core.chuchai.repository;

import com.coder.labsystem.constant.BaoXiaoState;
import com.coder.labsystem.model.entity.ChuChai;
import com.coder.labsystem.model.vo.ChuChaiVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

/**
 * @author : JQK
 * @date : 2021-04-19 22:10
 * @description :
 */
@Repository
public interface ChuChaiRepository extends MongoRepository<ChuChai, String>, PagingAndSortingRepository<ChuChai, String> {

    /**
     * 通过用户名查询出差记录 + 分页查询，并且按出差时间排序
     * @param username 用户名
     * @param state 报销状态
     * @param startDate 查询开始日期（包括）
     * @param stopDate 查询截止日期（不包括）
     * @param pageable
     * @return
     */
    Page<ChuChaiVo> findByUsernameAndStateAndChuChaiDateBetween(String username, BaoXiaoState state, LocalDate startDate, LocalDate stopDate, Pageable pageable);

}
