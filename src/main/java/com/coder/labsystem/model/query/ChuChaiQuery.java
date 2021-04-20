package com.coder.labsystem.model.query;

import com.coder.labsystem.constant.BaoXiaoState;

import java.time.Month;
import java.time.YearMonth;

/**
 * @author : JQK
 * @date : 2021-04-19 23:22
 * @description :
 */
public class ChuChaiQuery extends PageQuery {

    /** 报销状态，默认为未报销 */
    private BaoXiaoState state;

    /** 查询月份，默认为当前年月 */
    private YearMonth yearMonth;

    @Override
    public void adjust() {
        super.adjust();
        if (null == state) {
            state = BaoXiaoState.UNTREATED;
        }
        if (null == yearMonth) {
            yearMonth = YearMonth.now();
        }
    }
}
