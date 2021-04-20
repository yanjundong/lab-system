package com.coder.labsystem.model.bo;

import com.coder.labsystem.constant.ChuChaiTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author : JQK
 * @date : 2021-04-20 10:48
 * @description : 出差记录的业务对象
 */
public class ChuChaiBo {

    /**
     * 出差id，仅供数据库内部使用，唯一，自动生成
     */
    private String id;

    /**
     * 出差日期，必填项
     */
    @NotNull
    private LocalDate date;

    /**
     * 出差时间段，必填项
     */
    @NotNull
    private ChuChaiTime time;

    /**
     * 出差地址，必填项
     */
    @NotEmpty(message = "正确填写出差地址")
    private String destination;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}