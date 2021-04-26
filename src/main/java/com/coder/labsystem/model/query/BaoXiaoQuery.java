package com.coder.labsystem.model.query;

import com.coder.labsystem.constant.BaoXiaoState;

import java.time.YearMonth;

/**
 * @author : JQK
 * @date : 2021-04-25 16:34
 * @description :
 */
public class BaoXiaoQuery extends PageQuery {

    /** 报销状态，默认为未报销 */
    private BaoXiaoState state;

    /** 查询月份，默认为上一个月 */
    private YearMonth yearMonth;

    public BaoXiaoState getState() {
        return state;
    }

    public void setState(BaoXiaoState state) {
        this.state = state;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = yearMonth;
    }

    @Override
    public void adjust() {
        super.adjust();
        if (null == state) {
            state = BaoXiaoState.UNTREATED;
        }
        if (null == yearMonth) {
            yearMonth = YearMonth.now().minusMonths(1);
        }
    }

}
