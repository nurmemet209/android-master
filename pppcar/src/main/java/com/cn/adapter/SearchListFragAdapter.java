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
 * Created by nurmemet on 2016/4/8.
 */
public class SearchListFragAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Item> list;

    public SearchListFragAdapter( Context mContext,ArrayList<Item> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_search,null);
        RecyclerView.ViewHolder holder=new RecyclerView.ViewHolder(view) {
        };

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view=holder.itemView;
        TextView title=(TextView)view.findViewById(R.id.title);
        SimpleDraweeView img=(SimpleDraweeView)view.findViewById(R.id.title_img);
        TextView price=(TextView)view.findViewById(R.id.price);
        title.setText(list.get(position).getName());
        img.setImageURI(Uri.parse(list.get(position).getImg()));
        price.setText(Float.toString(list.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)){
            return list.size();
        }
        return 0;
    }
}
