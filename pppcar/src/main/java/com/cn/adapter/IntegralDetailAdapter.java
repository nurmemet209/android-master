package com.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.entity.Item;
import com.cn.pppcar.R;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralDetailAdapter extends BaseListAdapter {

    public IntegralDetailAdapter(Context mContext, ArrayList<Item> list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_integral_detail,null);
        RecyclerView.ViewHolder holder=new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }
}
