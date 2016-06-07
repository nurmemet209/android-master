package com.cn.entity;

import java.util.List;

/**
 * Created by nurmemet on 6/7/2016.
 */
public abstract class BasePageableItem<T> {

    private int totalPage;
    private int page;
    private int totalSize;
    private int pageSize;

    abstract public List<T> getList();

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public void addNewPage(BasePageableItem moreContent) {
        getList().addAll(moreContent.getList());
        setPage(moreContent.getPage());
    }

}
