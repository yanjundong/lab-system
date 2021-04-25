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
     * 出差日期，必填项
     */
    @NotNull
    private LocalDate chuChaiDate;

    /**
     * 出差时间段，必填项
     */
    @NotNull
    private ChuChaiTime chuChaiTime;

    /**
     * 出差地址，必填项
     */
    @NotEmpty(message = "正确填写出差地址")
    private String destination;

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
}
