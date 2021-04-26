package com.coder.labsystem.core.user.controller;

import com.coder.labsystem.core.user.service.UserService;
import com.coder.labsystem.model.entity.User;
import com.coder.labsystem.model.entity.UserBasicInfo;
import com.coder.labsystem.model.entity.UserExtendInfo;
import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import com.coder.labsystem.model.query.UserQuery;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : JQK
 * @date : 2021-04-19 19:59
 * @description :
 */
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册用户
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseBody addUser(@RequestBody @Valid UserBasicInfo user, BindingResult bindingResult) {
        boolean addUser = userService.addUser(user);
        return ResponseBody.getInstance(ErrorCode.OK, "添加成功");
    }

    /**
     * 补充用户信息
     *
     * @param extendInfo 用户补充信息
     * @return
     */
    @PostMapping(value = "/user/supplement")
    public ResponseBody supplyUserInfo(@RequestBody @Valid UserExtendInfo extendInfo, BindingResult bindingResult) {
        boolean supplyExtendInfo = userService.supplyUserExtendInfo(extendInfo);
        return ResponseBody.getInstance(ErrorCode.OK, "补充成功");
    }

    /**
     * 查询用户信息
     *
     * @return
     */
    @GetMapping(value = "/user")
    public ResponseBody getUser() {
        UserExtendInfo user = userService.getUser();
        if (user != null) {
            return ResponseBody.getInstance("", "查询成功", user);
        } else {
            return ResponseBody.getInstance("", "查询失败");
        }
    }

    /**
     * 批量查询用户信息
     * @param query
     * @return
     */
    @PostMapping(value = "/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseBody getUsers(@RequestBody UserQuery query) {
        Page<User> users = userService.getUsers(query);

        return ResponseBody.getInstance("", "查询成功", users);
    }

    /**
     * 通过用户名删除用户
     *
     * @param username 用户名
     * @return
     */
    @DeleteMapping(value = "/user/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseBody removeUser(@PathVariable(value = "username") String username) {

        return null;
    }
}
