package com.coder.labsystem.model.query;

import javax.validation.constraints.Min;

/**
 * @author : JQK
 * @date : 2021-04-19 20:54
 * @description : 分页查询
 */
public class PageQuery {

    /** 第几页 */
    private int pageNum;

    /** 每页的大小 */
    private int pageSize;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 调整分页的参数
     */
    public void adjust() {
        if (pageNum <= 0) {
            pageNum = 1;
        }
        if (pageSize <= 0) {
            pageSize = 10;
        }
    }
}
