package com.coder.labsystem.model.vo;

import com.coder.labsystem.constant.BaoXiaoState;
import com.coder.labsystem.constant.ChuChaiTime;
import com.coder.labsystem.model.entity.ChuChai;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author : JQK
 * @date : 2021-04-20 10:58
 * @description : 出差记录显示层对象
 */
public class ChuChaiVo {
    /**
     * 出差id，仅供数据库内部使用，唯一，自动生成
     */
    private String id;

    /** 出差日期，必填项 */
    @NotNull
    private LocalDate chuChaiDate;

    /** 出差时间段，必填项 */
    @NotNull
    private ChuChaiTime chuChaiTime;

    /**
     * 出差地址，必填项
     */
    @NotEmpty(message = "正确填写出差地址")
    private String destination;

    /**
     * 报销状态，程序自动维护
     */
    private BaoXiaoState state;

    /**
     * 出差提交日期， 自动生成
     */
    private LocalDateTime createDateTime;

    public ChuChaiVo() {
    }

    public ChuChaiVo(String id, LocalDate date, ChuChaiTime time, String destination, BaoXiaoState state, LocalDateTime createDateTime) {
        this.id = id;
        this.chuChaiDate = date;
        this.chuChaiTime = time;
        this.destination = destination;
        this.state = state;
        this.createDateTime = createDateTime;
    }

    public ChuChaiVo(ChuChai chuChai) {
        this(chuChai.getId(), chuChai.getChuChaiDate(), chuChai.getChuChaiTime(), chuChai.getDestination(), chuChai.getState(), chuChai.getCreateDateTime());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
