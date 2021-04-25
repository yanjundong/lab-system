package com.coder.labsystem.model.entity;

import cn.hutool.core.util.IdUtil;
import com.coder.labsystem.constant.BaoXiaoState;
import com.coder.labsystem.model.bo.FaPiaoBo;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : JQK
 * @date : 2021-04-19 17:58
 * @description : 发票
 */
@Document(value = "faPiao")
public class FaPiao {

    /** 出差id，仅供数据库内部使用，唯一，自动生成 */
    @MongoId
    private String id;

    /** 用户名称，由token获取，后端填写 */
    private String username;

    /** 购买商品名称，必填项 */
    @NotEmpty(message = "正确填写商品名")
    private String shangPinName;

    /** 发票金额，必填项 */
    @NotEmpty(message = "正确填写金额")
    private BigDecimal money;

    /** 发票文件对应的id */
    private String fileID;

    /** 报销状态，程序自动维护 */
    private BaoXiaoState state;

    /** 发票提交日期， 自动生成*/
    private LocalDateTime createDateTime;

    public FaPiao() {
        this.id = IdUtil.objectId();
        this.createDateTime = LocalDateTime.now();
    }

    public FaPiao(String username, String shangPinName, BigDecimal money, String fileID, BaoXiaoState state) {
        this();
        this.username = username;
        this.shangPinName = shangPinName;
        this.money = money;
        this.fileID = fileID;
        this.state = state;
    }

    public FaPiao(String username, FaPiaoBo faPiaoBo) {
        this(username, faPiaoBo.getShangPinName(),
                faPiaoBo.getMoney(),
                faPiaoBo.getFileID(), BaoXiaoState.UNTREATED);
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

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
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
