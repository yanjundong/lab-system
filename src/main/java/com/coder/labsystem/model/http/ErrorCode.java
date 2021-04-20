package com.coder.labsystem.model.http;

/**
 * @author : JQK
 * @date : 2021-04-20 19:37
 * @description : 错误码
 */
public class ErrorCode {

    /** 一切正常 */
    public static final String OK = "00000";

    /**
     * 用户端错误---一级宏观错误码
     * */
    public static final String CLIENT_ERROR = "A0001";

    /** 用户注册错误---二级宏观错误码 */
    public static final String USER_REGISTER_ERROR = "A0100";

    /** 用户名已存在---二级宏观错误码 */
    public static final String USER_USERNAME_EXIST_ERROR = "A0111";

    /** 用户密码校验失败---二级宏观错误码 */
    public static final String USER_PASSWORD_CHECK_ERROR = "A0120";

    /** 账号不存在---二级宏观错误码 */
    public static final String USER_ACCOUNT_NOT_EXIST_ERROR = "A0201";

    /** 账号已被冻结---二级宏观错误码 */
    public static final String USER_ACCOUNT_LOCK_ERROR = "A0202";

    /**
     * 服务端错误---一级宏观错误码
     * */
    public static final String SERVER_ERROR = "B0001";

    /**
     * 调用第三方服务错误---一级宏观错误码
     * */
    public static final String OTHER_SERVER_ERROR = "C0001";

    /** 数据库服务超时---二级宏观错误码 */
    public static final String DATABASE_TIMEOUT_ERROR = "B0300";

    /** 数据库服务错误---二级宏观错误码 */
    public static final String DATABASE_ERROR = "B0300";

}
