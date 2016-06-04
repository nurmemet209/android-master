package com.cn.pppcar;

import android.support.v4.app.FragmentPagerAdapter;

import com.cn.viewpager.CustomViewPager;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import butterknife.Bind;

/**
 * Created by nurmemet on 2016/4/27.
 */
public abstract class BaseViewPagerAct extends BaseAct {

    @Bind(R.id.view_pager)
    protected CustomViewPager viewPager;
    @Bind(R.id.tab_container)
    protected AdvancedPagerSlidingTabStrip tabLayout;


    protected void setUpViewPager(FragmentPagerAdapter adapter) {
        if (adapter == null) {
            throw new IllegalStateException("null adapter");
        }
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
    }


    abstract protected void init();

}
