package com.cn.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.pppcar.R;
import com.cn.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/4/8.
 */
public class SearchSeggestAdapter extends BaseListAdapter<String> {
    private View.OnClickListener onClickListener;

    public SearchSeggestAdapter(Context mContext, List<String> list, View.OnClickListener onClickListener) {
        super(mContext,list);
        this.onClickListener = onClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = new TextView(mContext);
        int left = mContext.getResources().getDimensionPixelSize(R.dimen.padding_normal);
        int top = mContext.getResources().getDimensionPixelSize(R.dimen.padding_big);
        tv.setPadding(left, top, left, top);
        tv.setBackground(ContextCompat.getDrawable(mContext,R.drawable.list_item_bg));
        tv.setClickable(true);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(params);
        if (onClickListener != null) {
            tv.setOnClickListener(onClickListener);
        }
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(tv) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView tv = (TextView) holder.itemView;
        tv.setText(list.get(position));
    }

}
