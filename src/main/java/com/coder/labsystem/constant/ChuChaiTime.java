package com.coder.labsystem.constant;

/**
 * @author : JQK
 * @date : 2021-04-19 17:48
 * @description : 出差时间段
 */
public enum ChuChaiTime {

    /** 上午 */
    FORENOON(1),

    /** 下午 */
    AFTERNOON(2),

    /** 全天 */
    ALLDAY(3);

    private int val;

    ChuChaiTime(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }
}
