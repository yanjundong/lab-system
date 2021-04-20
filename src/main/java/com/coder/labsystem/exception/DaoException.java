package com.coder.labsystem.exception;

/**
 * @author : JQK
 * @date : 2021-04-20 20:18
 * @description : DAO异常
 */
public class DaoException extends BaseException {
    public DaoException(Throwable cause) {
        super(cause);
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
