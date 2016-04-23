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
 * Created by nurmemet on 2016/4/20.
 */
public class AuctionAdapter extends RecyclerView.Adapter {
    //全部竞拍
    public final static int AUCTION_ALL=1;
    //正在竞拍
    public final static int AUCTION_ING=2;
    //竞拍成功
    public final static int AUCTION_SUCCESS=3;
    //竞拍失败
    public final static int AUCTION_FAILED=4;

    private ArrayList<Item> list;
    private Context mContext;
    private int adapterType=1;

    public AuctionAdapter(ArrayList<Item> list, Context mContext,int adapterType) {
        this.list = list;
        this.mContext = mContext;
        this.adapterType=adapterType;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_all_auction,null);
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
