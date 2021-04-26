package com.coder.labsystem.core.exception;

import com.coder.labsystem.exception.AccountNotExistException;
import com.coder.labsystem.exception.UsernameExistException;
import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : JQK
 * @date : 2021-04-26 17:10
 * @description :用户相关异常
 */
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = {UsernameExistException.class})
    public ResponseBody usernameExistExceptionHandler(HttpServletRequest request, UsernameExistException e) {
        return ResponseBody.getInstance(ErrorCode.USER_USERNAME_EXIST_ERROR, e.getMessage());
    }

    @ExceptionHandler(value = {AccountNotExistException.class})
    public ResponseBody accountNotExistExceptionHandler(HttpServletRequest request, AccountNotExistException e) {
        return ResponseBody.getInstance(ErrorCode.USER_ACCOUNT_NOT_EXIST_ERROR, e.getMessage());
    }

}
