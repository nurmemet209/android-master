package com.cn.entity;

/**
 * Created by nurmemet on 2016/3/19.
 */
public class City extends BaseEntity {
    private long cityId;
    private long provinceId;
    private String name;

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }

    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
