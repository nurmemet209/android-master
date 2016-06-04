package com.cn.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.pppcar.R;

import java.util.List;

/**
 * Created by nurmemet on 2016/5/20.
 */
public class SearchHistoryAdapter extends BaseListAdapter<RecyclerView.ViewHolder,String> {

    private int paddingLeftRight;
    private int padddingTopBottom;

    public SearchHistoryAdapter(Context mContext, List<String> list) {
        super(mContext, list);
        padddingTopBottom = mContext.getResources().getDimensionPixelSize(R.dimen.padding_big);
        paddingLeftRight = mContext.getResources().getDimensionPixelSize(R.dimen.padding_normal);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = new TextView(mContext);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(params);
        tv.setClickable(true);
        tv.setBackground(ContextCompat.getDrawable(mContext, R.drawable.list_item_bg));
        tv.setPadding(paddingLeftRight, padddingTopBottom, paddingLeftRight, padddingTopBottom);
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
