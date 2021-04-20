package com.coder.labsystem.security;

import com.coder.labsystem.model.entity.UserBasicInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author : JQK
 * @date : 2020-07-30 9:58
 * @description : security相关操作
 */
public class SecurityUtil {
    /**
     * 获取当前用户信息
     * @return
     */
    public static UserBasicInfo getCurrentUser() {
        UserBasicInfo user = null;
        Authentication authentication = getAuthentication();
        if(authentication != null) {
            Object principal = authentication.getPrincipal();
            if(principal != null && principal instanceof UserDetails) {
                user = (UserBasicInfo) principal;
            }
        }
        return user;
    }

    /**
     * 获取当前登录信息
     * @return
     */
    public static Authentication getAuthentication() {
        if(SecurityContextHolder.getContext() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication;
    }

}
