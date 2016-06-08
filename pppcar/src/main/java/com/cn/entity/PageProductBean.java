package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class PageProductBean extends BasePageableItem<ProductBean> {


    private ArrayList<ProductBean> productBean;
    
    public ArrayList<ProductBean> getProductBean() {
        return productBean;
    }

    public void setProductBean(ArrayList<ProductBean> productBean) {
        this.productBean = productBean;
    }


    @Override
    public List<ProductBean> getList() {
        return productBean;
    }
}
