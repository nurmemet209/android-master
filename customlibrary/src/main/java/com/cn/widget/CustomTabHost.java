package com.cn.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TabHost;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class CustomTabHost extends TabHost {

    private ViewPager viewPager;
    private ArrayList<String> tabSpecList=new ArrayList<>();


    public CustomTabHost(Context context) {
        super(context);
    }

    public CustomTabHost(Context context, AttributeSet attrs) {
        super(context, attrs);

    }



    public void setViewPager(final ViewPager viewPager){
        this.viewPager=viewPager;
        if (viewPager==null){
            Log.e(this.getClass().toString(),"viewPager null");
            return;
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                CustomTabHost.this.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        this.setOnTabChangedListener(new OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                int index=tabSpecList.indexOf(tabId);
                viewPager.setCurrentItem(index);

            }
        });
    }

    @Override
    public void addTab(TabSpec tabSpec) {
        super.addTab(tabSpec);
        if (tabSpec!=null){
            tabSpecList.add(tabSpec.getTag());
            Log.e(this.getClass().toString(),"mTag null");
        }



    }
}
