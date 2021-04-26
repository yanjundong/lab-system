package com.coder.labsystem.exception;

/**
 * @author : JQK
 * @date : 2021-04-26 16:58
 * @description : 用户名已存在
 */
public class UsernameExistException extends BaseException{
    public UsernameExistException(Throwable cause) {
        super(cause);
    }

    public UsernameExistException(String message) {
        super(message);
    }

    public UsernameExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
