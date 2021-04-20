package com.coder.labsystem.core.user.repository;

import com.coder.labsystem.model.entity.UserExtendInfo;

/**
 * @author : JQK
 * @date : 2021-04-20 16:28
 * @description : 使用 mongoTemplate 自实现的一些方法
 */
public interface CustomUserRepository {

    /**
     * 补充用户信息，该信息插入user集合中，
     * @param id 用户id
     * @param extendInfo 用户扩展信息
     * @return
     */
    boolean insertUserExtendInfo(String id, UserExtendInfo extendInfo);

    /**
     * 通过用户名查找用户信息
     * @param username
     * @return
     */
    UserExtendInfo findByUsername(String username);

}
