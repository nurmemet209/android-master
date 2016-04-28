package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cn.adapter.BrandViewPagerAdapter;
import com.cn.adapter.MyOrderViewPagerAdapter;
import com.cn.viewpager.CustomViewPager;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/25.
 */
public class BrandAct extends BaseAct {

    @Bind(R.id.view_pager)
    protected CustomViewPager viewPager;
    @Bind(R.id.tab_container)
    protected AdvancedPagerSlidingTabStrip tabLayout;

    BrandViewPagerAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_brand);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        adapter=new BrandViewPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);

    }
}
