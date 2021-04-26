package com.coder.labsystem.core.user.service;

import com.coder.labsystem.core.user.repository.UserRepository;
import com.coder.labsystem.model.entity.UserBasicInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author : JQK
 * @date : 2021-04-20 11:13
 * @description : 用户登录
 */
@Service
public class UserBasicInfoService implements UserDetailsService {
    private final static Logger LOG = LoggerFactory.getLogger(UserBasicInfoService.class);

    private final UserRepository userRepository;

    public UserBasicInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserBasicInfo userInfo = new UserBasicInfo(userRepository.findByUsername(username));
        if (null != userInfo) {
            return userInfo;
        }
        throw new UsernameNotFoundException("not find this user");
    }
}
