package com.cn.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.entity.Child;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;
import com.cn.util.Util;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/5/1.
 */
public class ClassifycationAdapter extends BaseListAdapter<RecyclerView.ViewHolder,Object> {


    public ClassifycationAdapter(Context mContext, ArrayList<Object> list) {
        super(mContext,list);
        this.mContext = mContext;
        this.list =list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view;
        if (viewType == 0) {
            view = new TextView(mContext);
            float size=mContext.getResources().getDimensionPixelSize(R.dimen.text_size_large) ;
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
            view.setTextColor(mContext.getResources().getColor(R.color.main_red));
        } else {
            view = new TextView(mContext);

            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            view.setLayoutParams(params);
            view.setGravity(Gravity.CENTER);
            view.setHeight(mContext.getResources().getDimensionPixelSize(R.dimen.item_height));
            view.setBackground(mContext.getResources().getDrawable(R.drawable.dialog_button_bg_sl));
            view.setClickable(true);
        }
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int padding = mContext.getResources().getDimensionPixelOffset(R.dimen.padding_normal);
        if (getItemViewType(position) == 0) {
            TextView sectionTitle = (TextView) holder.itemView;
            String title=(String)list.get(position);
            sectionTitle.setText(title);


            sectionTitle.setPadding(padding, padding, padding, padding);
        } else if (getItemViewType(position) == 1) {
            TextView item = (TextView) holder.itemView;
            Child content=(Child)list.get(position);
            item.setText(content.getName());

        }


    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof String) {
            return 0;
        }
        return 1;
    }


    public RectDecaration getDecoration() {
        RectDecaration decaration = new RectDecaration(mContext, UIHelper.dip2px(mContext,1), 3);
        return decaration;
    }

    public GridLayoutManager.SpanSizeLookup getSpanSizeLookUp() {
        return new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (getItemViewType(position) == 0) {
                    return 3;
                }
                return 1;
            }
        };
    }


    class RectDecaration extends RecyclerView.ItemDecoration {

        private Context mContext;
        private int span = 1;
        private int height = 0;
        private Drawable drawable;
        private int subItemIndex=0;

        public RectDecaration(Context mContext, int height, int span) {
            this.mContext = mContext;
            this.height = height;
            this.span = span;
            drawable = mContext.getResources().getDrawable(R.drawable.divider_small);
        }


        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
        }

        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {


            int left = 0;
            int right = 0;
            int top = 0;
            int bottom = 0;
            int x = 0;
            RecyclerView.LayoutManager manager = parent.getLayoutManager();
            GridLayoutManager gridLayoutManager = (GridLayoutManager) manager;
            int span = gridLayoutManager.getSpanCount();
            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {

                if (getItemViewType(i) == 0) {
                    x = 0;
                    continue;
                }


                View child = parent.getChildAt(i);
                if ((x + 1) % span == 1) {
                    //绘制左边
                    left = child.getLeft() - height;
                    top = child.getTop();
                    right = left + height;
                    bottom = child.getBottom();
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                    //绘制top
                    left = child.getLeft() - height;
                    top = child.getTop() - height;
                    right = child.getRight() + height;
                    bottom = child.getTop();
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                    //绘制右侧
                    left = child.getRight();
                    top = child.getTop();
                    right = child.getRight() + height;
                    bottom = child.getBottom();
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);

                    //绘制下面
                    left = child.getLeft() - height;
                    top = child.getBottom();
                    right = child.getRight() + height;
                    bottom = child.getBottom() + height;
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                } else if ((x + 1) % span == 0) {
                    //绘制左边
                    left = child.getLeft() - height / 2;
                    top = child.getTop();
                    right = left + height / 2;
                    bottom = child.getBottom()+height;
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                    //绘制top
                    left = child.getLeft() - height;
                    top = child.getTop() - height;
                    right = child.getRight() + height;
                    bottom = top + height;
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                    //绘制右侧
                    left = child.getRight();
                    top = child.getTop() - height;
                    right = child.getRight() + height;
                    bottom = child.getBottom();
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);

                    //绘制下面
                    left = child.getLeft() - height;
                    top = child.getBottom();
                    right = child.getRight() + height;
                    bottom = child.getBottom() + height;
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                } else {
                    //绘制左边
                    left = child.getLeft() - height / 2;
                    top = child.getTop() - height;
                    right = left + height / 2;
                    bottom = child.getBottom();
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                    //绘制top
                    left = child.getLeft() - height / 2;
                    top = child.getTop() - height;
                    right = child.getRight() + height / 2;
                    bottom = top + height;
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                    //绘制右侧
                    left = child.getRight();
                    top = child.getTop() - height;
                    right = child.getRight() + height ;
                    bottom = child.getBottom()+height;
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);

                    //绘制下面
                    left = child.getLeft() - height;
                    top = child.getBottom();
                    right = child.getRight() + height;
                    bottom = child.getBottom() + height;
                    drawable.setBounds(left, top, right, bottom);
                    drawable.draw(c);
                }

                x++;

            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView
                parent, RecyclerView.State state) {

            int position = parent.getChildAdapterPosition(view);

            if (getItemViewType(position) == 0) {
                outRect.set(0, 0, 0, 0);
                subItemIndex=0;
                return;
            } else {
                int index = subItemIndex + 1;
                if (index % span == 1) {
                    outRect.set(height, height, height / 2, 0);
                } else if (index % span == 0) {
                    outRect.set(height / 2, height, height, 0);
                } else {
                    outRect.set(height / 2, height, height / 2, 0);
                }
                subItemIndex++;
            }
        }
    }

}

