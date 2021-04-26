package com.coder.labsystem.model.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

/**
 * @author : JQK
 * @date : 2021-04-19 17:18
 * @description :
 */
public class UserExtendInfo {
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
    @Length(min = 0, max = 11, message = "手机号码长度不正确")
    @Pattern(regexp = "^(((13[0-9])|(14[579])|(15([0-3]|[5-9]))|(16[6])|(17[0135678])|(18[0-9])|(19[89]))\\d{8})$", message = "手机号码格式错误")
    private String phone;

    /** 用户qq，非必须 */
    private String qq;

    public UserExtendInfo() {
    }

    public UserExtendInfo(User user) {
        this(user.getFullName(), user.getBirthday(), user.getEmail(), user.getPhone(), user.getQq());
    }

    public UserExtendInfo(String fullName, LocalDate birthday, String email, String phone, String qq) {
        this.fullName = fullName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.qq = qq;
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

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "UserExtendInfo{" +
                "fullName='" + fullName + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", qq='" + qq + '\'' +
                '}';
    }
}
