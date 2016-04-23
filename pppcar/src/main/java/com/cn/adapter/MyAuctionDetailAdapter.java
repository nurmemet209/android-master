package com.cn.adapter;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.commans.Constants;
import com.cn.commans.DateUtil;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.Bind;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by nurmemet on 2016/4/20.
 */
public class MyAuctionDetailAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<Item> list;

    private Item headContent;
    private ArrayList<Object> objectList = new ArrayList<>();

    public MyAuctionDetailAdapter(Context mContext, ArrayList<Item> lsit,Item headContent) {
        this.mContext = mContext;
        this.list = lsit;
        this.headContent=headContent;
        revert();
    }

    private void revert() {

        if (Util.isNoteEmpty(list)) {
            String temp = "";
            String date = "";
            for (int i = 0; i < list.size(); i++) {
                temp = DateUtil.convertTime(list.get(i).getTime());
                if (!temp.equals(date)) {
                    objectList.add(temp);
                    objectList.add(list.get(i));
                } else {
                    objectList.add(list.get(i));
                }
                date = temp;
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(mContext).inflate(R.layout.header_auction_product_frag, null);
        } else if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_bid_record_date, null);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_bid_record_main, null);
        }
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            bindHeadData(holder.itemView,headContent);
        }
        else if (objectList.get(position-1) instanceof String){
            String date=(String)objectList.get(position-1);
            View view=holder.itemView;
            TextView dateTv=(TextView) view.findViewById(R.id.time);
            dateTv.setText(date);
        }else{
            Item item=(Item)objectList.get(position-1);
            View view=holder.itemView;
            TextView price=(TextView) view.findViewById(R.id.price);
            price.setText(Float.toString(item.getPrice()));
            TextView date=(TextView)view.findViewById(R.id.time);
            date.setText(DateUtil.convertTime(item.getTime()));

            TextView telNum=(TextView)view.findViewById(R.id.tel_num);
            telNum.setText(item.getPhoneNum());
            //状态，出局，或领先
            TextView state=(TextView)view.findViewById(R.id.state);
            GradientDrawable dr=(GradientDrawable)mContext.getResources().getDrawable(R.drawable.round_rect);
            dr.setColor(mContext.getResources().getColor(R.color.main_red));
            state.setBackground(dr);
            state.setTextColor(mContext.getResources().getColor(R.color.white));


        }

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            if (objectList.get(position-1) instanceof String) {
                return 1;
            } else {
                return 2;
            }

        }
    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)) {
            return list.size();
        }
        return 0;
    }


    private void bindHeadData(View view,Item item) {

        AutoScrollViewPager banner = (AutoScrollViewPager) view.findViewById(R.id.banner);
        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.banner_indicator);
        ArrayList<String> list = getList();
        ArrayList viewList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            SimpleDraweeView img = new SimpleDraweeView(mContext);
            Uri uri = Uri.parse(list.get(i));
            img.setImageURI(uri);
            viewList.add(img);
        }
        BannerAdapter adapter = new BannerAdapter(mContext, viewList);
        banner.setAdapter(adapter);
        banner.setInterval(4000);
        // banner.setScrollDurationFactor(5);
        banner.setCycle(true);
        banner.setOffscreenPageLimit(list.size());
        banner.setBorderAnimation(true);
        banner.startAutoScroll();
        indicator.setViewPager(banner);

        TextView title=(TextView)view.findViewById(R.id.title);
        title.setText(item.getTitle());

        TextView bidCount=(TextView)view.findViewById(R.id.bid_number);
        SpannableStringBuilder builder = new SpannableStringBuilder(mContext.getString(R.string.bid_number_));
        int start = builder.length();
        builder.append("23");
        // builder.setSpan(new AbsoluteSizeSpan(16,true),start,builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.main_red)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        bidCount.setText(builder);

    }


    private ArrayList<String> getList() {
        ArrayList<String> imageUrlList = new ArrayList<>();
        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg");
        imageUrlList
                .add("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg");
        imageUrlList
                .add("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg");
        imageUrlList
                .add("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg");
        return imageUrlList;
    }
}
