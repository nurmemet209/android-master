package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
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
 * Created by nurmemet on 2016/4/2.
 */
public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.CustomViewHolder>  {

    private ArrayList<Item> list;
    private Context mContext;

    public GoodsAdapter(Context context, ArrayList<Item> list) {
        this.list = list;
        mContext=context;
    }

    @Override
    public GoodsAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView= LayoutInflater.from(mContext).inflate(R.layout.item_list_goods,null);
        CustomViewHolder holder=new CustomViewHolder(mainView);

        return holder;
    }

    @Override
    public void onBindViewHolder(GoodsAdapter.CustomViewHolder holder, int position) {
        View view=holder.itemView;
        TextView price=(TextView)view.findViewById(R.id.item_price);
        TextView title=(TextView)view.findViewById(R.id.item_title);
        SimpleDraweeView img=(SimpleDraweeView)view.findViewById(R.id.title_img) ;
        TextView collectedNum=(TextView)view.findViewById(R.id.collect_num);
        price.setText(Float.toString(list.get(position).getPrice()));
        title.setText(list.get(position).getTitle());
        collectedNum.setText(Integer.toString(list.get(position).getCollectNum()));
        img.setImageURI(Uri.parse(list.get(position).getImgAddress()));



    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)){
            return list.size();
        }
        return 0;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(View itemView) {
            super(itemView);
        }
    }
}
