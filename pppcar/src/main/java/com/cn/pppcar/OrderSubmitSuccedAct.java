package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cn.commans.ActivitySwitcher;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/5.
 * 订单提交成功
 */
public class OrderSubmitSuccedAct extends BaseAct {

    private long orderId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_submit_succed);
        ButterKnife.bind(this);
        orderId=getIntent().getLongExtra("orderId",-1);
    }


    public void seeOrder(View view) {
        finish();
        ActivitySwitcher.toMyOrderAct(this);


    }

    public void continueBuy(View view) {

    }
}
