package com.coder.labsystem.exception;

/**
 * @author : JQK
 * @date : 2021-04-26 16:52
 * @description : 账号不存在
 */
public class AccountNotExistException extends BaseException{
    public AccountNotExistException(Throwable cause) {
        super(cause);
    }

    public AccountNotExistException(String message) {
        super(message);
    }

    public AccountNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
