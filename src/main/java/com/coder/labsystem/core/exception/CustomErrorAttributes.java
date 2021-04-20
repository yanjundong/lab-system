package com.coder.labsystem.core.exception;

import com.coder.labsystem.model.http.ErrorCode;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : JQK
 * @date : 2021-04-20 19:57
 * @description :
 */
@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> map = super.getErrorAttributes(webRequest, options);
        Map<String, Object> newMap = new HashMap<>(3);
        newMap.put("code", ErrorCode.SERVER_ERROR);
        newMap.put("msg", map.get("message"));
        newMap.put("data", null);
        return newMap;
    }
}
