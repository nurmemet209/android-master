package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cn.adapter.IntegralDetailAdapter;
import com.cn.adapter.IntegralDetailViewPagerAdapter;
import com.cn.adapter.IntegralMallViewPagerAdapter;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralDetailAct extends BaseViewPagerAct {

    IntegralDetailViewPagerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_integral_detail);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        adapter = new IntegralDetailViewPagerAdapter(getSupportFragmentManager(), this);

        setUpViewPager(adapter);
    }
}
