package com.cn.pppcar;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.cn.adapter.MyOrderViewPagerAdapter;
import com.cn.viewpager.CustomViewPager;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class MyOrderAct extends BaseAct {

    @Bind(R.id.view_pager)
    protected CustomViewPager viewPager;
    @Bind(R.id.tab_container)
    protected AdvancedPagerSlidingTabStrip tabLayout;

    private MyOrderViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_my_order);
        ButterKnife.bind(this);
        init();

    }

    private void init(){
        adapter=new MyOrderViewPagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
        tabLayout.showDot(1);

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(actFinishAnimInResId,actFinishAnimOutResId);
    }

}
