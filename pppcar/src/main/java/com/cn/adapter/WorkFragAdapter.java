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

import java.util.AbstractMap;
import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/7.
 */
public class WorkFragAdapter extends RecyclerView.Adapter {
    private ArrayList<Item> list;
    private Context mContext;

    public WorkFragAdapter(Context mContext,ArrayList<Item> list ) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_work_frag, null);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(list.get(position).getTitle());
        TextView subTitle = (TextView) view.findViewById(R.id.sub_title);
        subTitle.setText(list.get(position).getTitle());
        SimpleDraweeView titleImg = (SimpleDraweeView) view.findViewById(R.id.title_img);
        titleImg.setImageURI(Uri.parse(list.get(position).getImgAddress()));

        SimpleDraweeView headImg = (SimpleDraweeView) view.findViewById(R.id.head_portrait);
        //headImg.setImageURI(Uri.parse(list.get(position).getImgAddress()));


        TextView phoneNum = (TextView) view.findViewById(R.id.phone_num);
        phoneNum.setText("13072182811");

        TextView time = (TextView) view.findViewById(R.id.time);


    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)) {
            return list.size();
        }
        return 0;
    }
}
