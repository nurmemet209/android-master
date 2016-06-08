package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.commans.ActivitySwitcher;
import com.cn.entity.Item;
import com.cn.entity.PageProductBean;
import com.cn.entity.ProductBean;
import com.cn.fragment.LoadMoreRefreshFrag;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/8.
 */
public class SearchListFragAdapter extends BaseLoadMoreAdapter<RecyclerView.ViewHolder, ProductBean> {


    public SearchListFragAdapter( Context mContext,ArrayList<ProductBean> list) {
        super(mContext,list);
        this.list = list;
        this.mContext = mContext;
    }



    @Override
    protected RecyclerView.ViewHolder onCreateItemHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_search,null);
        view.setClickable(true);
        RecyclerView.ViewHolder holder=new RecyclerView.ViewHolder(view) {
        };

        return holder;
    }

    @Override
    protected void onBindItemHolder(final RecyclerView.ViewHolder holder, int position) {
        View view=holder.itemView;
        TextView title=(TextView)view.findViewById(R.id.title);
        SimpleDraweeView img=(SimpleDraweeView)view.findViewById(R.id.title_img);
        TextView price=(TextView)view.findViewById(R.id.price);
        title.setText(list.get(position).getName());
        img.setImageURI(Uri.parse(list.get(position).getImg()));
        price.setText("￥"+Float.toString(list.get(position).getPrice()));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos=holder.getAdapterPosition();
                ActivitySwitcher.toProductDetailAct((Activity) mContext,list.get(pos).getId());
            }
        });
    }





    @Override
    protected RecyclerView.ViewHolder getLoadingMoreViewHolder(View loadingMoreView) {
        RecyclerView.ViewHolder holder=new RecyclerView.ViewHolder(loadingMoreView) {
        };
        return holder;
    }
}
