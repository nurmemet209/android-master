package com.cn.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 个人中心-订单
 *
 * @author chendongdong
 */

public class PageResPersonalCenterOrder extends BasePageableItem<ResOrder> {
    private ArrayList<ResOrder> resOrders;

    public ArrayList<ResOrder> getResOrders() {
        return resOrders;
    }

    public void setResOrders(ArrayList<ResOrder> resOrders) {
        this.resOrders = resOrders;
    }


    @Override
    protected List getList() {
        return resOrders;
    }
}
