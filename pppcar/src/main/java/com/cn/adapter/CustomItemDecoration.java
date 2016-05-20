package com.cn.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/6.
 */
public class CustomItemDecoration extends RecyclerView.ItemDecoration {

    private GradientDrawable drawable;
    int height=1;
    public CustomItemDecoration(Context context, int dividerHeight/*px为单位*/) {
        drawable=new GradientDrawable();
        drawable.setColor(ContextCompat.getColor(context,R.color.main_bg_gray));
        height = dividerHeight;
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getTop()-height;
            final int bottom = top + height+ params.topMargin;
            drawable.setBounds(left, top, right, bottom);
            drawable.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0,height,0,0);
    }
}
