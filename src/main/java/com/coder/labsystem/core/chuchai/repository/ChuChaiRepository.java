package com.coder.labsystem.core.chuchai.repository;

import com.coder.labsystem.constant.BaoXiaoState;
import com.coder.labsystem.model.entity.ChuChai;
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
public interface ChuChaiRepository extends MongoRepository<ChuChai, String>, PagingAndSortingRepository<ChuChai, String> {

    /**
     * 通过用户名查询出差记录 + 分页查询
     * @param username
     * @param state 报销状态，已报销/未报销
     * @param pageable
     * @return
     */
    Page<ChuChai> findByUsernameAndState(String username, BaoXiaoState state, Pageable pageable);

}
