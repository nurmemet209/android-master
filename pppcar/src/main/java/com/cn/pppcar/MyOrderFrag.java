package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.adapter.MyOrderViewPagerAdapter;
import com.cn.fragment.BaseFrag;
import com.cn.viewpager.CustomViewPager;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class MyOrderFrag extends BaseFrag {

    @Bind(R.id.view_pager)
    protected CustomViewPager viewPager;
    @Bind(R.id.tab_container)
    protected AdvancedPagerSlidingTabStrip tabLayout;

    private MyOrderViewPagerAdapter adapter;

    public static MyOrderFrag getInstance() {
        MyOrderFrag frag = new MyOrderFrag();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, mainView);
        init();
        return mainView;
    }

    private void init() {
        adapter = new MyOrderViewPagerAdapter(getChildFragmentManager(), getActivity());
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());
        tabLayout.setViewPager(viewPager);
        tabLayout.showDot(1);

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.frag_my_order;
    }
}
