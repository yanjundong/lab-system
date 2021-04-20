package com.coder.labsystem.exception;


/**
 * @author : JQK
 * @date : 2021-04-20 20:18
 * @description : 自定义基础异常
 */
public class BaseException extends RuntimeException {

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(final String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
