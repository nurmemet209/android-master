package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心-订单
 *
 * @author chendongdong
 */

public class PageResPersonalCenterOrder extends BaseEntity {
    private ArrayList<ResOrder> resOrders;

    @Override
    public long getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }


    public ArrayList<ResOrder> getResOrders() {
        return resOrders;
    }

    public void setResOrders(ArrayList<ResOrder> resOrders) {
        this.resOrders = resOrders;
    }


}
