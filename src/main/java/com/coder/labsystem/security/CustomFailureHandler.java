package com.coder.labsystem.security;

import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author : JQK
 * @date : 2020-07-29 21:39
 * @description : 自定义登录失败后的处理器
 */
@Component
public class CustomFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {

        String msg = null;
        String code = null;
        if (exception instanceof LockedException) {
            msg = "账户被锁定，请联系管理员!";
            code = ErrorCode.USER_ACCOUNT_LOCK_ERROR;
        } else if (exception instanceof CredentialsExpiredException) {
            msg = "密码过期，请联系管理员!";
            code = ErrorCode.USER_ACCOUNT_LOCK_ERROR;
        } else if (exception instanceof AccountExpiredException) {
            msg = "账户过期，请联系管理员!";
            code = ErrorCode.USER_ACCOUNT_LOCK_ERROR;
        } else if (exception instanceof DisabledException) {
            msg = "账户被禁用，请联系管理员!";
            code = ErrorCode.USER_ACCOUNT_LOCK_ERROR;
        } else if (exception instanceof BadCredentialsException) {
            msg = "用户名或者密码输入错误，请重新输入!";
            code = ErrorCode.USERNAME_OR_PASSWORD_ERROR;
        } else {
            msg = "登录失败";
            code = ErrorCode.USERNAME_OR_PASSWORD_ERROR;
        }
        response.setContentType("application/json;charset=utf-8");
        ObjectMapper om = new ObjectMapper();
        PrintWriter out = response.getWriter();
        out.write(om.writeValueAsString(ResponseBody.getInstance(code, msg)));
        out.flush();
        out.close();
    }
}
