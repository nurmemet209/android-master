package com.cn.pppcar.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cn.util.MyLogger;

/**
 * Created by nurmemet on 5/29/2016.
 */
public class CustomRecycleView extends RecyclerView {
    public CustomRecycleView(Context context) {
        super(context);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (canScrollVertically(-1)){

        }
        return super.onInterceptTouchEvent(e);
    }
}
