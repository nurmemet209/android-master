package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/4.
 */
public class PayFailedAct extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pay_failed);
        ButterKnife.bind(this);
    }
}
