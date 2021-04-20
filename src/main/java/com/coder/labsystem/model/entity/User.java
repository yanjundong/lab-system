package com.coder.labsystem.model.entity;

import com.coder.labsystem.constant.UserRole;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.*;
import java.time.LocalDate;

/**
 * @author : JQK
 * @date : 2021-04-20 16:46
 * @description : 用户信息，对应User集合
 */
@Document(value = "user")
public class User {
    /**
     * 用户id，仅供数据库内部使用，唯一，自动生成
     */
    @MongoId
    private String id;

    /**
     * 用户名，用户登录使用，唯一，只能为英文字符，必须
     */
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 6, max = 12, message = "用户名长度为6-12位")
    private String username;

    /**
     * 用户名登录密码，必须
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 12, message = "密码长度为6-12位")
    private String password;

    /**
     * 用户角色，必须
     */
    @NotNull(message = "必须指定用户角色")
    private UserRole userRole;

    /** 用户姓名必须 */
    @NotEmpty(message = "姓名不能为空")
    private String fullName;

    /** 用户出生日期，非必须 */
    @Past(message = "错误的出生日期")
    private LocalDate birthday;

    /** 用户邮箱，非必须 */
    @Email
    private String email;

    /** 用户手机，非必须 */
    @Length(min = 11, max = 11, message = "手机号码长度不正确")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号码格式错误")
    private String phone;

    /** 用户qq，非必须 */
    private String qq;

    public User() {
    }

    public User(UserBasicInfo basicInfo, UserExtendInfo extendInfo) {
        if (basicInfo != null) {
            this.id = basicInfo.getId();
            this.username = basicInfo.getUsername();
            this.password = basicInfo.getPassword();
            this.userRole = basicInfo.getUserRole();
        }
        if (extendInfo != null) {
            this.fullName = extendInfo.getFullName();
            this.birthday = extendInfo.getBirthday();
            this.email = extendInfo.getEmail();
            this.phone = extendInfo.getPhone();
            this.qq = extendInfo.getQq();
        }

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
