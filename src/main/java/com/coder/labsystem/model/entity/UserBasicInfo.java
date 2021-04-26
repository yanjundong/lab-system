package com.coder.labsystem.model.entity;

import com.coder.labsystem.constant.UserRole;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : JQK
 * @date : 2021-04-20 10:24
 * @description :
 */
public class UserBasicInfo implements UserDetails {

    /**
     * 用户id，仅供数据库内部使用，唯一，自动生成
     */
    private String id;

    /**
     * 用户名，用户登录使用，唯一，只能为英文字符，必须
     */
    @NotEmpty(message = "用户名不能为空")
    @Length(min = 6, max = 30, message = "用户名长度为6-30位")
    private String username;

    /**
     * 用户名登录密码，必须
     */
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度为6-20位")
    private String password;

    /**
     * 用户角色，必须
     */
    @NotNull(message = "必须指定用户角色")
    private UserRole userRole;

    public UserBasicInfo() {
    }

    public UserBasicInfo(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getUserRole());
    }

    public UserBasicInfo(String id, String username, String password, UserRole userRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public UserBasicInfo(String id, String username, String password, List<GrantedAuthority> authorities) {
        this(id, username, password, UserRole.valueOf(authorities.get(0).getAuthority()));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "UserBasicInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userRole=" + userRole +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>(1);
        authorities.add(new SimpleGrantedAuthority(userRole.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
