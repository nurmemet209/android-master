package com.cn.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/9.
 */
public class GridItemDecoration extends RecyclerView.ItemDecoration {

    private GradientDrawable drawable;
    int height = 1;
    private int span = 0;
    private boolean hasHeader=false;

    public GridItemDecoration(Context context, int dividerHeight/*px为单位*/, int span) {
        drawable = new GradientDrawable();
        drawable.setColor(context.getResources().getColor(R.color.main_bg_gray));
        height = dividerHeight;
        this.span = span;
    }

    public GridItemDecoration(Context context, int dividerHeight/*px为单位*/, int span, boolean hasHeader) {
        drawable = new GradientDrawable();
        drawable.setColor(context.getResources().getColor(R.color.main_bg_gray));
        height = dividerHeight;
        this.span = span;
        this.hasHeader = hasHeader;
    }


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int positon = parent.getChildAdapterPosition(view);
        if (positon == 0) {
            if (hasHeader) {
                outRect.set(0, 0, 0, 0);
                return;
            }
        }
        int index = positon + 1;
        if (hasHeader) {
            index = index - 1;

        }
        if (index % span == 1) {
            outRect.set(height, height, height / 2, 0);
        } else if (index % span == 0) {
            outRect.set(height / 2, height, height, 0);
        } else {
            outRect.set(height / 2, height, height / 2, 0);
        }
    }
}