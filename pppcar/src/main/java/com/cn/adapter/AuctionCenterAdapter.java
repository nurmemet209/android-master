package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.commans.ActivitySwitcher;
import com.cn.commans.AuctionHelper;
import com.cn.commans.Constants;
import com.cn.commans.SpanHelper;
import com.cn.entity.AuctionResBean;
import com.cn.entity.BaseEntity;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by nurmemet on 2016/4/18.
 */
public class AuctionCenterAdapter extends BaseListAdapter<RecyclerView.ViewHolder,AuctionResBean> {

    private int ACUTION_ING = 0;
    private int AUCTION_OVER = 1;
    private Context mContext;
    private List<AuctionResBean> list;
    private RecyclerView recyclerView;
    private AuctionHelper auctionHelper;


    public AuctionCenterAdapter(Context mContext,RecyclerView recyclerView, List<AuctionResBean> list) {
        super(mContext, list);
        this.mContext = mContext;
        this.list = list;
        this.recyclerView=recyclerView;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        View view = null;
        RecyclerView.ViewHolder holder = null;
        if (viewType == ACUTION_ING) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_acution_ing, null);
            view.setLayoutParams(params);
            holder = new RecyclerView.ViewHolder(view) {
            };
        } else if (viewType == AUCTION_OVER) {
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
        AuctionResBean item = list.get(position);
        if (getItemViewType(position) == ACUTION_ING) {

            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(item.getProductName());
            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
            img.setImageURI(Uri.parse(item.getImgUrl()));

            TextView currentPrice = (TextView) view.findViewById(R.id.current_price);
            currentPrice.setText(spanHelper.priceSpanSmaller(R.string.current_price_, item.getCurrentPrice()));

            TextView fixedPrice = (TextView) view.findViewById(R.id.fixed_price);
            fixedPrice.setText(spanHelper.priceSpanSmaller(R.string.fixed_price_, item.getaPrice()));

            TextView bidNum = (TextView) view.findViewById(R.id.bid_number);
            bidNum.setText(spanHelper.priceSpanSmaller(R.string.bid_number_, item.getBidNumber()));

            TextView leftTime = (TextView) view.findViewById(R.id.left_time);
            leftTime.setText(spanHelper.auctionTime(item.getCurrentTime(), item.getEndTime()));

            if (auctionHelper==null){
                auctionHelper=new AuctionHelper();
            }

            TextView term = (TextView) view.findViewById(R.id.auction_stage);
            builder.clear();
            term.setText(builder.append("第").append(String.valueOf(item.getTerm())).append("期").toString());

        } else if (getItemViewType(position) == AUCTION_OVER) {
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(item.getProductName());
            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
            img.setImageURI(Uri.parse(item.getImgUrl()));
            TextView term = (TextView) view.findViewById(R.id.auction_stage);
            builder.clear();
            term.setText(builder.append("第").append(String.valueOf(item.getTerm())).append("期").toString());
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActivitySwitcher.toAuctionDetailAct((Activity) mContext);
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
        AuctionResBean item = list.get(position);
        if (item.getState() == Constants.AUCTION_STATE_ING) {
            return 0;
        } //正在拍卖
        return 1;
    }

    public void update(final int start, final int end) {
        for (int i = start; i <= end; i++) {
            if (getItemViewType(i) == ACUTION_ING) {
                    RecyclerView.ViewHolder holder=recyclerView.findViewHolderForAdapterPosition(i);
                TextView leftTime=(TextView) holder.itemView.findViewById(R.id.left_time);
                AuctionResBean item = list.get(i);
                leftTime.setText(spanHelper.auctionTime(item.getCurrentTime()+auctionHelper.getDelta(), item.getEndTime()));
            }
        }
    }
}
