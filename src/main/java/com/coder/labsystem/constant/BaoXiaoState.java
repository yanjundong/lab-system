package com.coder.labsystem.constant;

/**
 * @author : JQK
 * @date : 2021-04-19 17:34
 * @description : 出差或发票报销状态
 */
public enum BaoXiaoState {

    /** 出差或发票 已报销*/
    PROCESSED(true),
    /** 出差或发票 已报销 */
    UNTREATED(false);

    private boolean val;

    BaoXiaoState(boolean val) {
        this.val = val;
    }

    public static BaoXiaoState of(boolean val) {
        return val ? PROCESSED : UNTREATED;
    }

    public boolean getVal() {
        return val;
    }

}
