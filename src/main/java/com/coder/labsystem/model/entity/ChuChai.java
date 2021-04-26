package com.coder.labsystem.model.entity;

import cn.hutool.core.util.IdUtil;
import com.coder.labsystem.constant.BaoXiaoState;
import com.coder.labsystem.constant.ChuChaiTime;
import com.coder.labsystem.model.bo.ChuChaiBO;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author : JQK
 * @date : 2021-04-19 17:17
 * @description : 出差报销
 */
@Document(value = "chuChai")
public class ChuChai {

    /** 出差id，仅供数据库内部使用，唯一，自动生成 */
    @MongoId
    private String id;

    /** 用户名称，由token获取，后端填写 */
    private String username;

    /** 出差日期，必填项 */
    @NotNull
    @Field(value = "chuChaiDate")
    private LocalDate chuChaiDate;

    /** 出差时间段，必填项 */
    @NotNull
    @Field(value = "chuChaiTime")
    private ChuChaiTime chuChaiTime;

    /** 出差地址，必填项 */
    @NotEmpty(message = "正确填写出差地址")
    private String destination;

    /** 报销状态，程序自动维护 */
    private BaoXiaoState state;

    /** 出差提交日期， 自动生成*/
    private LocalDateTime createDateTime;

    public ChuChai() {
        this.id = IdUtil.objectId();
        this.createDateTime = LocalDateTime.now();
    }

    public ChuChai(String username, ChuChaiBO chuChaiBo) {
        this();
        this.username = username;
        this.chuChaiDate = chuChaiBo.getChuChaiDate();
        this.chuChaiTime = chuChaiBo.getChuChaiTime();
        this.destination = chuChaiBo.getDestination();
        this.state = BaoXiaoState.UNTREATED;
    }

    public ChuChai(String id, String username, ChuChaiBO chuChaiBo) {
        this.id = id;
        this.username = username;
        this.chuChaiDate = chuChaiBo.getChuChaiDate();
        this.chuChaiTime = chuChaiBo.getChuChaiTime();
        this.destination = chuChaiBo.getDestination();
        this.state = BaoXiaoState.UNTREATED;
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

    public LocalDate getChuChaiDate() {
        return chuChaiDate;
    }

    public void setChuChaiDate(LocalDate chuChaiDate) {
        this.chuChaiDate = chuChaiDate;
    }

    public ChuChaiTime getChuChaiTime() {
        return chuChaiTime;
    }

    public void setChuChaiTime(ChuChaiTime chuChaiTime) {
        this.chuChaiTime = chuChaiTime;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
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

    @Override
    public String toString() {
        return "ChuChai{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", date=" + chuChaiDate +
                ", time=" + chuChaiTime +
                ", destination='" + destination + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChuChai chuChai = (ChuChai) o;
        return id.equals(chuChai.id) && username.equals(chuChai.username) && chuChaiDate.equals(chuChai.chuChaiDate) && chuChaiTime == chuChai.chuChaiTime && destination.equals(chuChai.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, chuChaiDate, chuChaiTime, destination);
    }
}
