package com.coder.labsystem.core.user.service;

import com.coder.labsystem.core.user.repository.CustomUserRepository;
import com.coder.labsystem.core.user.repository.UserRepository;
import com.coder.labsystem.exception.AccountNotExistException;
import com.coder.labsystem.exception.DaoException;
import com.coder.labsystem.exception.UsernameExistException;
import com.coder.labsystem.model.entity.User;
import com.coder.labsystem.model.entity.UserBasicInfo;
import com.coder.labsystem.model.entity.UserExtendInfo;
import com.coder.labsystem.model.query.UserQuery;
import com.coder.labsystem.security.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : JQK
 * @date : 2021-04-19 19:40
 * @description :
 */
@Service
public class UserService {
    private final static Logger LOG = LoggerFactory.getLogger(UserService.class);
    private final static Object LOCK = new Object();
    private final UserRepository userRepository;
    private final CustomUserRepository customUserRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CustomUserRepository customUserRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customUserRepository = customUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 注册用户
     * @param basicInfo
     * @return 注册成功，返回true；否则返回false
     */
    public boolean addUser(UserBasicInfo basicInfo) {
        synchronized (LOCK) {
            User byUsername = userRepository.findByUsername(basicInfo.getUsername());
            if (byUsername != null) {
                throw new UsernameExistException("用户名已被使用");
            }
            String password = basicInfo.getPassword();
            basicInfo.setPassword(passwordEncoder.encode(password));
            User user = new User(basicInfo, null);
            User insert = userRepository.insert(user);
            if (insert == null) {
                throw new DaoException("添加用户信息错误");
            }
            return true;
        }
    }

    /**
     * 补充用户信息
     * @param extendInfo 补充信息
     * @return
     */
    public boolean supplyUserExtendInfo(UserExtendInfo extendInfo) {
        UserBasicInfo basicInfo = SecurityUtil.getCurrentUser();
        boolean userExtendInfo = customUserRepository.insertUserExtendInfo(basicInfo.getUsername(), extendInfo);
        if (!userExtendInfo) {
            throw new DaoException("补充用户信息错误");
        }
        return true;
    }

    /**
     * 查询用户信息
     * @return 查询成功，返回用户信息；没有该用户，返回null
     */
    public UserExtendInfo getUser(String username) {
        if (null == username) {
            return null;
        }
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new AccountNotExistException("该用户名不存在");
        }
        return new UserExtendInfo(user);
    }

    /**
     * 查询用户信息
     * @return 查询成功，返回用户信息；没有该用户，返回null
     */
    public UserExtendInfo getUser() {
        UserBasicInfo basicInfo = SecurityUtil.getCurrentUser();
        return getUser(basicInfo.getUsername());
    }

    /**
     * 批量查询用户信息
     * @param query 查询过滤条件
     * @return 用户信息，如果页码数 > 最大页码数，则返回最后一页的数据，如果页码数 < 1，则返回第一页数据
     */
    public Page<User> getUsers(UserQuery query) {
        /*Page<User> users = userRepository.findByFullNameContains(query.getName() ,PageRequest.of(query.getPageNum() - 1, query.getPageSize()));*/

        return null;
    }
}
