package com.coder.labsystem.core.exception;

import com.coder.labsystem.exception.DaoException;
import com.coder.labsystem.model.http.ErrorCode;
import com.coder.labsystem.model.http.ResponseBody;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : JQK
 * @date : 2021-04-20 20:25
 * @description :
 */
@RestControllerAdvice
public class DaoExceptionHandler {

    @ExceptionHandler(value = {DaoException.class})
    public ResponseBody daoExceptionHandler(HttpServletRequest request, DaoException e) {
        return ResponseBody.getInstance(ErrorCode.DATABASE_ERROR, e.getMessage());
    }

}
