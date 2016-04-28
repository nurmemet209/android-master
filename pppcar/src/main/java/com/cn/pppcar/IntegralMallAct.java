package com.cn.pppcar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.cn.adapter.IntegralDetailViewPagerAdapter;
import com.cn.adapter.IntegralMallViewPagerAdapter;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralMallAct extends BaseViewPagerAct {


    protected IntegralMallViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_integral_mall);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        adapter = new IntegralMallViewPagerAdapter(getSupportFragmentManager(), this);
        setUpViewPager(adapter);
        setDrawables();

    }

    private void setDrawables() {
        TextView integral = (TextView) tabLayout.getTabAt(1).findViewById(R.id.id_tab_txt);
        Drawable d = getResources().getDrawable(R.mipmap.top_bottom);
        d.setBounds(0,0,d.getIntrinsicWidth(), d.getIntrinsicHeight());
        int drawablePadding = getResources().getDimensionPixelOffset(R.dimen.padding_smallest_);
        integral.setCompoundDrawablePadding(drawablePadding);
        integral.setCompoundDrawables(null, null, d, null);

        TextView time = (TextView) tabLayout.getTabAt(2).findViewById(R.id.id_tab_txt);
        time.setCompoundDrawablePadding(drawablePadding);
        time.setCompoundDrawables(null, null, d, null);
    }
}
