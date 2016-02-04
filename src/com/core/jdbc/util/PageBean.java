package com.core.jdbc.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageBean<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int DEFAULT_PAGE_SIZE = 30;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private long start;
    private List<T> result;
    private int totalPageCount;
    private String pageUrl;

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public PageBean(long start, int totalCount, int pageSize, List<T> data) {
        this.start = start;
        this.totalPageCount = totalCount;
        this.pageSize = pageSize;
        this.result = data;
    }

    public PageBean() {
        new PageBean(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
    }

    public int getTotalCount() {
        return this.totalPageCount;
    }

    public long getTotalPageCount() {
        if (this.totalPageCount % this.pageSize == 0L) {
            return this.totalPageCount / this.pageSize;
        }
        return this.totalPageCount / this.pageSize + 1L;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public List<T> getResult() {
        return this.result;
    }

    public void setResult(List<T> data) {
        this.result = data;
    }

    public long getCurrentPageNo() {
        return this.start / this.pageSize + 1L;
    }

    public boolean hasPreviousPage() {
        return getCurrentPageNo() > 1L;
    }

    public boolean hasNextPage() {
        return getCurrentPageNo() <= getTotalPageCount() - 1L;
    }

    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }
}