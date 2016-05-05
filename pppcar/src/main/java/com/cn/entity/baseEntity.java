package com.cn.entity;

import java.io.Serializable;

/**
 * Created by nurmemet on 2016/3/19.
 */
public abstract class BaseEntity implements Serializable{

   abstract public long getId() ;





    public abstract String getName();

    @Override
    public boolean equals(Object o) {
        if (o!=null){
            BaseEntity baseEntity=(BaseEntity)o;
            if (baseEntity.getId()==this.getId()){
                return true;
            }
        }
        return false;
    }

     public String getImageAddress(){
         return "";
     };

    private int totalPage;
    private int page;
    private int totalSize;

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

    private int pageSize;

}
