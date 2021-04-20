package com.coder.labsystem.core.fapiao.repository;

import com.coder.labsystem.constant.BaoXiaoState;
import com.coder.labsystem.model.entity.FaPiao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : JQK
 * @date : 2021-04-19 22:10
 * @description :
 */
@Repository
public interface FaPiaoRepository extends MongoRepository<FaPiao, String>, PagingAndSortingRepository<FaPiao, String> {

    /**
     * 通过用户名查询发票报销记录 + 分页查询
     * @param username
     * @param state 报销状态，已报销/未报销
     * @param pageable
     * @return
     */
    Page<FaPiao> findByUsernameAndState(String username, BaoXiaoState state, Pageable pageable);

}