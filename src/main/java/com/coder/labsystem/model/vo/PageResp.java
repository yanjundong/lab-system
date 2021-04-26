package com.coder.labsystem.model.vo;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author : JQK
 * @date : 2021-04-21 10:22
 * @description : 分页数据返回
 */
public class PageResp<T> {

    /** 第几页 */
    private int pageNum;

    /** 每页的元素个数 */
    private int pageSize;

    /** 元素内容 */
    private List<T> content;

    /** 总的元素个数 */
    private long totalElements;

    /** 总页数 */
    private int totalPages;

    public PageResp(int pageNum, int pageSize, List<T> content, long totalElements, int totalPages) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.content = content;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public PageResp(Page page) {
        this(page.getNumber(), page.getSize(),
                page.getContent(), page.getTotalElements(), page.getTotalPages());
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

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

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }
}
