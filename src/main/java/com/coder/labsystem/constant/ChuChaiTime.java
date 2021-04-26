package com.coder.labsystem.constant;

/**
 * @author : JQK
 * @date : 2021-04-19 17:48
 * @description : 出差时间段
 */
public enum ChuChaiTime {

    /** 上午 */
    FORENOON(1, 30),

    /** 下午 */
    AFTERNOON(2, 30),

    /** 全天 */
    ALLDAY(3, 50);

    private int val;
    private int money;

    ChuChaiTime(int val, int money) {
        this.val = val;
        this.money = money;
    }

    public int getVal() {
        return val;
    }

    public int getMoney() {
        return money;
    }
}
