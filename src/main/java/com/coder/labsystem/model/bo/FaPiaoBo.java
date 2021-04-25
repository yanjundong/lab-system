package com.coder.labsystem.model.bo;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

/**
 * @author : JQK
 * @date : 2021-04-21 11:18
 * @description :
 */
public class FaPiaoBo {

    /** 购买商品名称，必填项 */
    @NotEmpty(message = "正确填写商品名")
    private String shangPinName;

    /** 发票金额，必填项 */
    @NotEmpty(message = "正确填写金额")
    private BigDecimal money;

    /** 发票文件对应的ID */
    private String fileID;

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

    public String getFileID() {
        return fileID;
    }

    public void setFileID(String fileID) {
        this.fileID = fileID;
    }
}
