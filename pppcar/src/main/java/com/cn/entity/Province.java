package com.cn.entity;

/**
 * Created by nurmemet on 2016/3/19.
 */
public class Province extends BaseEntity {

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;
    private long provinceId;

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    private String province;

    public long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(long provinceId) {
        this.provinceId = provinceId;
    }


    @Override
    public String getName() {
        return province;
    }
}
