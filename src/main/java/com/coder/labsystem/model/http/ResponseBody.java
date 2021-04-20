package com.coder.labsystem.model.http;

/**
 * @author : JQK
 * @date : 2021-04-19 20:35
 * @description :
 */
public class ResponseBody {

    private String code;

    private String msg;

    private Object data;

    private ResponseBody(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseBody getInstance(String code, String msg) {
        return new ResponseBody(code, msg, null);
    }

    public static ResponseBody getInstance(String code, String msg, Object data) {
        return new ResponseBody(code, msg, data);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
