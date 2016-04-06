package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class OrderAdapter extends RecyclerView.Adapter {
    //全部订单
    public final static int ALLORDER=1;
    //待审核
    public final static int WAIT_EXAMINATE=2;
    //待付款
    public final static int WAIT_PAY=3;
    //待发货
    public final static int WAIT_DELIVER=4;
    //待收货
    public final static int WAIT_RECEIVE=1;

    private ArrayList<Item> list;
    private Context mContext;
    private int adapterType=1;

    public OrderAdapter(ArrayList<Item> list, Context mContext,int adapterType) {
        this.list = list;
        this.mContext = mContext;
        this.adapterType=adapterType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list_order,null);
        RecyclerView.ViewHolder holder=new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view=holder.itemView;
        SimpleDraweeView img=(SimpleDraweeView)view.findViewById(R.id.title_img);
        TextView title=(TextView)view.findViewById(R.id.item_title);
        img.setImageURI(Uri.parse(list.get(position).getImgAddress()));
        title.setText(list.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)){
            return list.size();
        }
        return 0;
    }
}
