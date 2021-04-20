package com.coder.labsystem.model.entity;

import com.coder.labsystem.constant.BaoXiaoState;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : JQK
 * @date : 2021-04-19 17:58
 * @description : 发票
 */
public class FaPiao {

    /** 出差id，仅供数据库内部使用，唯一，自动生成 */
    private String id;

    /** 用户名称，由token获取，后端填写 */
    private String username;

    /** 购买商品名称，必填项 */
    @NotEmpty(message = "正确填写商品名")
    private String shangPinName;

    /** 发票金额，必填项 */
    @NotEmpty(message = "正确填写金额")
    private BigDecimal money;

    /** 发票文件 */
    private byte[] date;

    /** 报销状态，程序自动维护 */
    private BaoXiaoState state;

    /** 发票提交日期， 自动生成*/
    private LocalDateTime createDateTime;

    public FaPiao() {
        this.createDateTime = LocalDateTime.now();
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

    public String getShangPinName() {
        return shangPinName;
    }

    public void setShangPinName(String shangPinName) {
        this.shangPinName = shangPinName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public BaoXiaoState getState() {
        return state;
    }

    public void setState(BaoXiaoState state) {
        this.state = state;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime createDateTime) {
        this.createDateTime = createDateTime;
    }

    public byte[] getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "FaPiao{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", shangPinName='" + shangPinName + '\'' +
                ", money=" + money.setScale(2, BigDecimal.ROUND_UP) +
                ", state=" + state +
                ", createDateTime=" + createDateTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FaPiao faPiao = (FaPiao) o;
        return id.equals(faPiao.id) && username.equals(faPiao.username) && shangPinName.equals(faPiao.shangPinName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, shangPinName);
    }
}
