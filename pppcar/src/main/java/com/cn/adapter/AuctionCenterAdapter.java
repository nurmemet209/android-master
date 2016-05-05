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
import com.cn.commans.SpanHelper;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/18.
 */
public class AuctionCenterAdapter extends RecyclerView.Adapter {

    private int ACUTION_ING = 1;
    private int AUCTION_OVER = 2;
    private Context mContext;
    private ArrayList<Item> list;
    SpanHelper spanHelper;


    public AuctionCenterAdapter(Context mContext, ArrayList<Item> list) {
        this.mContext = mContext;
        this.list = list;
        spanHelper=new SpanHelper(mContext);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_auction_year, null);
            view.setLayoutParams(params);
            holder = new RecyclerView.ViewHolder(view) {
            };
        } else if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_acution_ing, null);
            view.setLayoutParams(params);
            holder = new RecyclerView.ViewHolder(view) {
            };
        } else if (viewType == 2) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_auction_end, null);
            view.setLayoutParams(params);
            holder = new RecyclerView.ViewHolder(view) {
            };
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        View view = holder.itemView;
        if (getItemViewType(position) == 0) {

            TextView yearTv = (TextView) view.findViewById(R.id.auction_year);
            yearTv.setText("2016"+mContext.getString(R.string.year));



        } else if (getItemViewType(position) == 1) {
            Item item = list.get(position-1);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(item.getName());
            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
            img.setImageURI(Uri.parse(item.getImg()));

            TextView currentPrice = (TextView) view.findViewById(R.id.current_price);
            currentPrice.setText(spanHelper.priceSpanSmaller(R.string.current_price_,1234.00F) );

            TextView fixedPrice = (TextView) view.findViewById(R.id.fixed_price);
            fixedPrice.setText(spanHelper.priceSpanSmaller(R.string.fixed_price_,1234.00F));

            TextView bidNum = (TextView) view.findViewById(R.id.bid_number);
            bidNum.setText(spanHelper.priceSpanSmaller(R.string.bid_number_,23));

            TextView leftTime = (TextView) view.findViewById(R.id.left_time);
            //leftTime.setText(spanHelper.auctionTime(12,23,34));



        } else if (getItemViewType(position) == 2) {
            Item item = list.get(position-1);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(item.getName());
            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
            img.setImageURI(Uri.parse(item.getImg()));



        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getItemViewType(position)!=0)
                    ActivitySwitcher.toAuctionDetailAct((Activity)mContext);
            }
        });


    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)) {

            return list.size();
        }

        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        //拍卖年份
        if (position==0) {
            return 0;
        } //正在拍卖
        else {
            Item item = list.get(position-1);
            if (item.getState() == ACUTION_ING) {
                return 1;
            }//拍卖结束
            else if (item.getState() == AUCTION_OVER) {
                return 2;
            }
        }
        return 2;
    }




}
