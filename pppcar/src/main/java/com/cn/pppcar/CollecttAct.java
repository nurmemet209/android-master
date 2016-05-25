package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cn.adapter.CommonOrderAndProOrderAdapter;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/5/23.
 */
public class CollecttAct extends BaseViewPagerAct {


    CommonOrderAndProOrderAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_collect);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        adapter=new CommonOrderAndProOrderAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        setUpViewPager(adapter);
    }
}
