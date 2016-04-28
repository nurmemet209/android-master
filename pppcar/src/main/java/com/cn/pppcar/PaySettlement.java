package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * 订单结算
 * Created by nurmemet on 2016/4/4.
 */
public class PaySettlement extends Activity {

    boolean payStageSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pay_settlement);
    }


    public void selectePayWay(View view) {
        View checked = view.findViewWithTag("selector");
        payStageSelected = checked.isSelected();
        checked.setSelected(!payStageSelected);

    }
}
