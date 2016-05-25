package com.cn.entity;

import java.util.Date;




public class ResFavorites extends BaseEntity {

    private long id; //收藏id

    private long productId;//产品id

    private String name;//产品名称

    private String imgs;//产品图片

    private double retailPrice;//零售价

    private Date createTime;//收藏时间

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgs() {
        return imgs.split(",")[0];
    }

    public void setImgs(String imgs) {
        this.imgs = imgs;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }


}
