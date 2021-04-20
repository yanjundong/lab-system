package com.coder.labsystem.model.entity;

import com.coder.labsystem.constant.BaoXiaoState;
import com.coder.labsystem.constant.ChuChaiTime;
import com.coder.labsystem.model.bo.ChuChaiBo;
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
    private LocalDate date;

    /** 出差时间段，必填项 */
    @NotNull
    @Field(value = "chuChaiTime")
    private ChuChaiTime time;

    /** 出差地址，必填项 */
    @NotEmpty(message = "正确填写出差地址")
    private String destination;

    /** 报销状态，程序自动维护 */
    private BaoXiaoState state;

    /** 出差提交日期， 自动生成*/
    private LocalDateTime createDateTime;

    public ChuChai(String username, ChuChaiBo chuChaiBo) {
        this.id = chuChaiBo.getId();
        this.username = username;
        this.date = chuChaiBo.getDate();
        this.time = chuChaiBo.getTime();
        this.destination = chuChaiBo.getDestination();
        this.state = BaoXiaoState.UNTREATED;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ChuChaiTime getTime() {
        return time;
    }

    public void setTime(ChuChaiTime time) {
        this.time = time;
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

    @Override
    public String toString() {
        return "ChuChai{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", destination='" + destination + '\'' +
                ", state=" + state +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChuChai chuChai = (ChuChai) o;
        return id.equals(chuChai.id) && username.equals(chuChai.username) && date.equals(chuChai.date) && time == chuChai.time && destination.equals(chuChai.destination);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, date, time, destination);
    }
}
