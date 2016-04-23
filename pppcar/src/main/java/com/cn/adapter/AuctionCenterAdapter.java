package com.cn.adapter;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;
import com.hp.hpl.sparta.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by nurmemet on 2016/4/18.
 */
public class AuctionCenterAdapter extends RecyclerView.Adapter {

    private int ACUTION_ING = 1;
    private int AUCTION_OVER = 2;
    private Context mContext;
    private ArrayList<Item> list;

    private ArrayList<Object> objectList = new ArrayList<>();


    public AuctionCenterAdapter(Context mContext, ArrayList<Item> list) {
        this.mContext = mContext;
        this.list = list;

//        if (Util.isNoteEmpty(list)){
//            year=getYear(list.get(0).getTime());
//        }
        revert();
    }

    private void revert() {

        if (Util.isNoteEmpty(list)) {
            int temp = -1;
            int year = 0;
            for (int i = 0; i < list.size(); i++) {
                temp = getYear(list.get(i).getTime());
                if (temp != year) {
                    objectList.add(temp);
                    objectList.add(list.get(i));
                } else {
                    objectList.add(list.get(i));
                }
                year = temp;
            }
        }
    }

    public void append(ArrayList<Item> apList) {
        int temp = -1;
        if (Util.isNoteEmpty(apList)) {
            if (Util.isNoteEmpty(list)) {
                int index = list.size();
                int year = getYear(list.get(index - 1).getTime());
                list.addAll(apList);
                for (int i = index; i < list.size(); i++) {
                    temp = getYear(list.get(i).getTime());
                    if (temp != year) {
                        objectList.add(temp);
                        objectList.add(list.get(i));
                    } else {
                        objectList.add(list.get(i));
                    }
                    year = temp;
                }

            } else {
                list = apList;
                revert();

            }
        }
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView;
        if (getItemViewType(position) == 0) {

            TextView yearTv = (TextView) view.findViewById(R.id.auction_year);
            yearTv.setText(objectList.get(position).toString() + mContext.getString(R.string.year));

        } else if (getItemViewType(position) == 1) {
            Item item = (Item) objectList.get(position);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(item.getTitle());
            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
            img.setImageURI(Uri.parse(item.getImgAddress()));
            TextView currentPrice = (TextView) view.findViewById(R.id.current_price);
            int start = 0;
            int end = 0;
            SpannableStringBuilder builder = new SpannableStringBuilder(mContext.getString(R.string.current_price_));
            start = builder.length();
            builder.append("$123.00");
            // builder.setSpan(new AbsoluteSizeSpan(16,true),start,builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.main_red)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            currentPrice.setText(builder);

            TextView fixedPrice = (TextView) view.findViewById(R.id.fixed_price);
            builder.replace(0, builder.length(), "");
            builder.append(mContext.getString(R.string.fixed_price_));
            start = builder.length();
            builder.append("$34334.00");
            //builder.setSpan(new AbsoluteSizeSpan(16,true),start,builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.main_red)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            fixedPrice.setText(builder);

            TextView bidNum = (TextView) view.findViewById(R.id.bid_number);
            builder.replace(0, builder.length(), "");
            builder.append(mContext.getString(R.string.bid_number_));
            start = builder.length();
            builder.append("23");
            builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.main_red)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            bidNum.setText(builder);

            TextView leftTime = (TextView) view.findViewById(R.id.left_time);
            builder.replace(0, builder.length(), "");
            builder.append(" 15 ");
            builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.white)), 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new BackgroundColorSpan(mContext.getResources().getColor(R.color.black)), 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append("：");
            start = builder.length();
            builder.append(" 12 ");
            builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.white)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new BackgroundColorSpan(mContext.getResources().getColor(R.color.black)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.append("：");
            start = builder.length();
            builder.append(" 00 ");
            builder.setSpan(new ForegroundColorSpan(mContext.getResources().getColor(R.color.white)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            builder.setSpan(new BackgroundColorSpan(mContext.getResources().getColor(R.color.black)), start, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            leftTime.setText(builder);

        } else if (getItemViewType(position) == 2) {
            Item item = (Item) objectList.get(position);
            TextView title = (TextView) view.findViewById(R.id.title);
            title.setText(item.getTitle());
            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
            img.setImageURI(Uri.parse(item.getImgAddress()));
        }

    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(objectList)) {

            return objectList.size();
        }

        return 0;
    }


    @Override
    public int getItemViewType(int position) {
        //拍卖年份
        if (objectList.get(position) instanceof Integer) {
            return 0;
        } //正在拍卖
        else {
            Item item = (Item) objectList.get(position);
            if (item.getState() == ACUTION_ING) {
                return 1;
            }//拍卖结束
            else if (item.getState() == AUCTION_OVER) {
                return 2;
            }
        }
        return 2;
    }


    private int getYear(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }


}
