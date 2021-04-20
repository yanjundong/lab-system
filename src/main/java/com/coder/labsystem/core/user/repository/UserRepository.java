package com.coder.labsystem.core.user.repository;

import com.coder.labsystem.model.entity.User;
import com.coder.labsystem.model.entity.UserBasicInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : JQK
 * @date : 2021-04-19 19:32
 * @description :
 */
@Repository
public interface UserRepository extends MongoRepository<User, String>, PagingAndSortingRepository<User, String> {

    /**
     * 通过用户名查询用户基本信息
     * @param username
     * @return
     */
    UserBasicInfo findByUsername(String username);

    /**
     * 用户姓名的模糊查询 + 分页查询
     * @param name
     * @param pageable
     * @return
     */
    /*Page<User> findByFullNameContains(String name, Pageable pageable);*/

}
