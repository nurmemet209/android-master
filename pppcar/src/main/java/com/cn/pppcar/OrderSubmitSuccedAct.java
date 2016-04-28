package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/5.
 * 订单提交成功
 */
public class OrderSubmitSuccedAct extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_submit_succed);
        ButterKnife.bind(this);
    }


    public void seeOrder(View view) {

    }

    public void continueBuy(View view) {

    }

    @Override
    public void finish() {
        super.finish();
    }
}
