package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/25.
 */
public class BrandAdapter extends BaseListAdapter<RecyclerView.ViewHolder,Item> {

    public BrandAdapter(Context mContext, ArrayList<Item> list) {
        super(mContext, list);
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_act_brand_center, null);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDraweeView img = (SimpleDraweeView) holder.itemView.findViewById(R.id.title_img);
        img.setScaleType(ImageView.ScaleType.CENTER);
        img.setImageURI(Uri.parse(list.get(position).getImg()));
        TextView title=(TextView)holder.itemView.findViewById(R.id.title);
        title.setText(list.get(position).getName());

    }


}
