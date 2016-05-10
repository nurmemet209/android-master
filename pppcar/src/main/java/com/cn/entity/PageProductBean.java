package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PageProductBean extends BaseEntity {


    private ArrayList<ProductBean> productBean;


    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }


    public ArrayList<ProductBean> getProductBean() {
        return productBean;
    }

    public void setProductBean(ArrayList<ProductBean> productBean) {
        this.productBean = productBean;
    }


}
