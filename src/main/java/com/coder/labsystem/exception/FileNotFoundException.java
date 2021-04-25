package com.coder.labsystem.exception;

/**
 * @author : JQK
 * @date : 2021-04-22 16:35
 * @description :
 */
public class FileNotFoundException extends BaseException {
    public FileNotFoundException(Throwable cause) {
        super(cause);
    }

    public FileNotFoundException(String message) {
        super(message);
    }

    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
