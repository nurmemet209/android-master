package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.commans.ActivitySwitcher;
import com.cn.entity.Item;
import com.cn.pppcar.R;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/28.
 */
public class ReceiveAddressListAdapter extends BaseListAdapter {
    private View selected;
    public ReceiveAddressListAdapter(Context mContext, ArrayList<Item> list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list_act_receive_address_list,null);
        RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        RecyclerView.ViewHolder holder=new RecyclerView.ViewHolder(view) {
        };


        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selected!=null){
                    selected.setSelected(false);
                }
                selected=v;
                selected.setSelected(true);
            }
        });

        holder.itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(position);
                ReceiveAddressListAdapter.this.notifyItemRemoved(position);
            }
        });
        holder.itemView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.toReceiveAddressEditAct((Activity)mContext);
            }
        });

    }
}
