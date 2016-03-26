package com.cn.entity;

/**
 * Created by nurmemet on 2016/3/19.
 */
public class Province extends BaseEntity {
    private long provinceId;
    private String name;

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
