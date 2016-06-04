package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cn.adapter.IntegralProductDetailViewPagerAdapter;
import com.cn.commans.ActivitySwitcher;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/28.
 */
public class IntegralProductDetailAct extends BaseViewPagerAct {

    IntegralProductDetailViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_integral_product_detail);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        adapter = new IntegralProductDetailViewPagerAdapter(getSupportFragmentManager(), this);
        setUpViewPager(adapter);
    }






}
