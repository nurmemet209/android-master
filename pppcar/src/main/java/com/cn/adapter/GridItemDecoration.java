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

    public GridItemDecoration(Context context, int dividerHeight/*px为单位*/, int span) {
        drawable = new GradientDrawable();
        drawable.setColor(context.getResources().getColor(R.color.main_bg_gray));
        height = dividerHeight;
        this.span = span;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//            int left = 0;
//            int right = 0;
//            int top = 0;
//            int bottom = 0;
//            RecyclerView.LayoutManager manager = parent.getLayoutManager();
//            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
//            int span = gridLayoutManager.getSpanCount();
//            final int childCount = parent.getChildCount();
//            for (int i = 0; i < childCount; i++) {
//                View child = parent.getChildAt(i);
//                if ((i + 1) % span == 1) {
//                    //绘制左边
//                    left = child.getLeft() - height;
//                    top = child.getTop();
//                    right = left + height;
//                    bottom = child.getBottom();
//                    drawable.setBounds(left, top, right, bottom);
//                    drawable.draw(c);
//                    //绘制top
//                    left = child.getLeft() - height;
//                    top = child.getTop() - height;
//                    right = child.getRight() + height / 2;
//                    bottom = child.getTop();
//                    drawable.setBounds(left, top, right, bottom);
//                    drawable.draw(c);
//                    //绘制右侧
//                    left = child.getRight();
//                    top = child.getTop();
//                    right = child.getRight() + height / 2;
//                    bottom = child.getBottom();
//                    drawable.setBounds(left, top, right, bottom);
//                    drawable.draw(c);
//                } else if ((i + 1) % span == 0) {
//                    //绘制左边
//                    left = child.getLeft() - height / 2;
//                    top = child.getTop();
//                    right = left + height / 2;
//                    bottom = child.getBottom();
//                    drawable.setBounds(left, top, right, bottom);
//                    drawable.draw(c);
//                    //绘制top
//                    left = child.getLeft() - height;
//                    top = child.getTop() - height;
//                    right = child.getRight() + height;
//                    bottom = top + height;
//                    drawable.setBounds(left, top, right, bottom);
//                    drawable.draw(c);
//                    //绘制右侧
//                    left = child.getRight();
//                    top = child.getTop();
//                    right = child.getRight() + height;
//                    bottom = child.getBottom();
//                    drawable.setBounds(left, top, right, bottom);
//                    drawable.draw(c);
//                } else {
//                    //绘制左边
//                    left = child.getLeft() - height / 2;
//                    top = child.getTop();
//                    right = left + height / 2;
//                    bottom = child.getBottom();
//                    drawable.setBounds(left, top, right, bottom);
//                    drawable.draw(c);
//                    //绘制top
//                    left = child.getLeft() - height / 2;
//                    top = child.getTop() - height;
//                    right = child.getRight() + height / 2;
//                    bottom = top + height;
//                    drawable.setBounds(left, top, right, bottom);
//                    drawable.draw(c);
//                    //绘制右侧
//                    left = child.getRight();
//                    top = child.getTop();
//                    right = child.getRight() + height / 2;
//                    bottom = child.getBottom();
//                    drawable.setBounds(left, top, right, bottom);
//
//                    drawable.draw(c);
//                }
//
//            }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int positon = parent.getChildAdapterPosition(view);
        int index = positon + 1;
        if (index % span == 1) {
            outRect.set(height, height, height / 2, 0);
        } else if (index % span == 0) {
            outRect.set(height / 2, height, height, 0);
        } else {
            outRect.set(height / 2, height, height / 2, 0);
        }

    }
}