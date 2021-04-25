package com.coder.labsystem.exception;

/**
 * @author : JQK
 * @date : 2021-04-22 15:33
 * @description : 文件访问错误
 */
public class FileAccessException extends BaseException {
    public FileAccessException(Throwable cause) {
        super(cause);
    }

    public FileAccessException(String message) {
        super(message);
    }

    public FileAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
