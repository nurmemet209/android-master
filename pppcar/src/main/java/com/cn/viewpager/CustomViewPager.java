package com.cn.viewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class CustomViewPager extends ViewPager {

    private boolean canScroll;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public void scrollTo(int x, int y) {
        if (!canScroll)
            super.scrollTo(x, y);
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }
}
