package com.cn.entity;

/**
 * Created by nurmemet on 2016/3/19.
 */
public class Area extends BaseEntity {

    private long areaId;



    private long cityId;
    private String name;


    public long getCityId() {
        return cityId;
    }

    public void setCityId(long cityId) {
        this.cityId = cityId;
    }


    public long getAreaId() {
        return areaId;
    }

    public void setAreaId(long areaId) {
        this.areaId = areaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
