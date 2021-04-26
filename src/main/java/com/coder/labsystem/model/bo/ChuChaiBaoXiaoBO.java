package com.coder.labsystem.model.bo;

import java.math.BigDecimal;

/**
 * @author : JQK
 * @date : 2021-04-25 19:00
 * @description :
 */
public class ChuChaiBaoXiaoBO {

    private String username;
    private String fullName;
    private BigDecimal money;

    public ChuChaiBaoXiaoBO(String username, String fullName, BigDecimal money) {
        this.username = username;
        this.fullName = fullName;
        this.money = money;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
