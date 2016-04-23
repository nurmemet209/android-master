package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.commans.ActivitySwitcher;
import com.cn.entity.Item;
import com.cn.entity.MainPage;
import com.cn.pppcar.R;
import com.cn.pppcar.SearchAct;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by nurmemet on 2016/3/30.
 */
public class MainPageRecycleAdapter extends RecyclerView.Adapter<MainPageRecycleAdapter.CustomViewHolder> {
    private MainPage mainPage;
    private ArrayList<Item> recommonds;
    private Context mContext = null;


    public MainPageRecycleAdapter(MainPage mainPage, Context mContext) {
        this.mainPage = mainPage;
        this.mContext = mContext;
        recommonds=mainPage.getHotRecommand();
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0){
            return 0;
        }else{
            return 1;
        }

    }

    @Override
    public MainPageRecycleAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View headerView = LayoutInflater.from(mContext).inflate(R.layout.main_page_header, null);
            CustomViewHolder holder = new CustomViewHolder(headerView);
            return holder;
        } else {
            View recommondView = LayoutInflater.from(mContext).inflate(R.layout.main_page_recommond_list_item, null);
            CustomViewHolder holder = new CustomViewHolder(recommondView);
            return holder;
        }
    }

    @Override
    public void onBindViewHolder(MainPageRecycleAdapter.CustomViewHolder holder, int position) {

        if (position == 0) {
            setHeaderData(holder.itemView);
        } else {
            setRecommond(holder.itemView,position-1);
        }

    }

    @Override
    public int getItemCount() {
        if (recommonds != null) {
            int size=(int)Math.ceil(recommonds.size()/(float)2);
            return size;
        }
        return 0;
    }

    public static class CustomViewHolder extends RecyclerView.ViewHolder {

        public CustomViewHolder(View itemView) {
            super(itemView);
        }
    }

    private void setHeaderData(View view) {
        //search
        {
            TextView search=(TextView) view.findViewById(R.id.search_edit_text);
            search.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ActivitySwitcher.toSearchAct((Activity) mContext);
                }
            });

        }
        if (mainPage == null)
            return;
        //轮播
        if (Util.isNoteEmpty(mainPage.getBannerImages())) {
            AutoScrollViewPager banner = (AutoScrollViewPager) view.findViewById(R.id.banner);
            CircleIndicator indicator=(CircleIndicator)view.findViewById(R.id.banner_indicator);

            ArrayList viewList = new ArrayList();
            for (int i = 0; i < mainPage.getBannerImages().size(); i++) {
                Item item = mainPage.getBannerImages().get(i);
                SimpleDraweeView img = new SimpleDraweeView(mContext);
                Uri uri = Uri.parse(item.getImgAddress());
                img.setImageURI(uri);
                viewList.add(img);
                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
            BannerAdapter adapter = new BannerAdapter(mContext, viewList);
            banner.setAdapter(adapter);
            banner.setInterval(4000);
            // banner.setScrollDurationFactor(5);
            banner.setCycle(true);
            banner.setOffscreenPageLimit(mainPage.getBannerImages().size());
            banner.setBorderAnimation(true);
            banner.startAutoScroll();
            indicator.setViewPager(banner);

        }
        //品牌中心
        View myOrder=view.findViewById(R.id.my_order_l);
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.toMyOrderAct((Activity)mContext);
            }
        });
        //拍卖中心
        View auctionCenter=view.findViewById(R.id.auction_center_l);
        auctionCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.toAuctionAct((Activity)mContext);
            }
        });

        //趴趴头条
        if (true/*Util.isNoteEmpty(mainPage.getPapaHeadLines())*/) {
//            ArrayList<Item> papaHeadLine = mainPage.getPapaHeadLines();
//            TextView tv = (TextView) view.findViewById(R.id.papa_headline);
//            tv.setText(papaHeadLine.get(0).getTitle());

            AdapterViewFlipper flipper=(AdapterViewFlipper)view.findViewById(R.id.headline_flipper);
            HeadLineFlipperAdapter headLineAdapter=new HeadLineFlipperAdapter(null,mContext);;
            flipper.setAdapter(headLineAdapter);

            flipper.setOutAnimation(mContext,R.anim.obj_bottom_out);
            flipper.setInAnimation(mContext,R.anim.obj_top_in);
            //flipper.startFlipping();
        }
        //综合
        if (Util.isNoteEmpty(mainPage.getUniversalItems())) {

            ArrayList<Item> universalItems = mainPage.getUniversalItems();
            int size = universalItems.size();
            SimpleDraweeView universal_1_big = (SimpleDraweeView) view.findViewById(R.id.universal_1_big);
            SimpleDraweeView universal_1_right = (SimpleDraweeView) view.findViewById(R.id.universal_1_right);
            SimpleDraweeView universal_2_1 = (SimpleDraweeView) view.findViewById(R.id.universal_2_1);
            SimpleDraweeView universal_2_2 = (SimpleDraweeView) view.findViewById(R.id.universal_2_2);
            SimpleDraweeView universal_2_3 = (SimpleDraweeView) view.findViewById(R.id.universal_2_3);
            universal_1_big.setImageURI(Uri.parse(universalItems.get(0).getImgAddress()));
            universal_1_right.setImageURI(Uri.parse(universalItems.get(getIndex(1, size)).getImgAddress()));
            universal_2_1.setImageURI(Uri.parse(universalItems.get(getIndex(2, size)).getImgAddress()));
            universal_2_2.setImageURI(Uri.parse(universalItems.get(getIndex(3, size)).getImgAddress()));
            universal_2_3.setImageURI(Uri.parse(universalItems.get(getIndex(4, size)).getImgAddress()));

        }
        //排气
        if (Util.isNoteEmpty(mainPage.getExhaustList())){
            ArrayList<Item> list = mainPage.getUniversalItems();
            int size = list.size();
            SimpleDraweeView  exhaust_left_big= (SimpleDraweeView) view.findViewById(R.id.exhaust_left_big);
            SimpleDraweeView exhaust_right_1_1 = (SimpleDraweeView) view.findViewById(R.id.exhaust_right_1_1);
            SimpleDraweeView exhaust_right_2_1 = (SimpleDraweeView) view.findViewById(R.id.exhaust_right_2_1);
            SimpleDraweeView exhaust_right_2_2 = (SimpleDraweeView) view.findViewById(R.id.exhaust_right_2_2);

            exhaust_left_big.setImageURI(Uri.parse(list.get(0).getImgAddress()));
            exhaust_right_1_1.setImageURI(Uri.parse(list.get(getIndex(1, size)).getImgAddress()));
            exhaust_right_2_1.setImageURI(Uri.parse(list.get(getIndex(2, size)).getImgAddress()));
            exhaust_right_2_2.setImageURI(Uri.parse(list.get(getIndex(3, size)).getImgAddress()));
        }
        //避震
        if(Util.isNoteEmpty(mainPage.getExhaustList())){
            ArrayList<Item> list = mainPage.getUniversalItems();
            int size = list.size();
            SimpleDraweeView  shock_left_big= (SimpleDraweeView) view.findViewById(R.id.shock_left_big);
            SimpleDraweeView shock_right_1_1 = (SimpleDraweeView) view.findViewById(R.id.shock_right_1_1);
            SimpleDraweeView shock_right_2_1 = (SimpleDraweeView) view.findViewById(R.id.shock_right_2_1);
            SimpleDraweeView shock_right_2_2 = (SimpleDraweeView) view.findViewById(R.id.shock_right_2_2);

            shock_left_big.setImageURI(Uri.parse(list.get(0).getImgAddress()));
            shock_right_1_1.setImageURI(Uri.parse(list.get(getIndex(1, size)).getImgAddress()));
            shock_right_2_1.setImageURI(Uri.parse(list.get(getIndex(2, size)).getImgAddress()));
            shock_right_2_2.setImageURI(Uri.parse(list.get(getIndex(3, size)).getImgAddress()));

        }
        //轮毂
        if(Util.isNoteEmpty(mainPage.getExhaustList())){
            ArrayList<Item> list = mainPage.getUniversalItems();
            int size = list.size();
            SimpleDraweeView  hob_left_big= (SimpleDraweeView) view.findViewById(R.id.hob_left_big);
            SimpleDraweeView hob_right_1_1 = (SimpleDraweeView) view.findViewById(R.id.hob_right_1_1);
            SimpleDraweeView hob_right_2_1 = (SimpleDraweeView) view.findViewById(R.id.hob_right_2_1);
            SimpleDraweeView hob_right_2_2 = (SimpleDraweeView) view.findViewById(R.id.hob_right_2_2);

            hob_left_big.setImageURI(Uri.parse(list.get(0).getImgAddress()));
            hob_right_1_1.setImageURI(Uri.parse(list.get(getIndex(1, size)).getImgAddress()));
            hob_right_2_1.setImageURI(Uri.parse(list.get(getIndex(2, size)).getImgAddress()));
            hob_right_2_2.setImageURI(Uri.parse(list.get(getIndex(3, size)).getImgAddress()));

        }
    }

    private int getIndex(int index, int size) {
        return size > index ? index : 0;
    }

    private void setRecommond(View view,int position){
        if (mainPage==null)
            return;;
        if (Util.isNoteEmpty(mainPage.getHotRecommand())){
            SimpleDraweeView leftImg=(SimpleDraweeView) view.findViewById(R.id.recommond_item_left);
            TextView titleLeft=(TextView)view.findViewById(R.id.item_title_left);
            TextView priceLeft=(TextView)view.findViewById(R.id.price_left);

            SimpleDraweeView rightImg=(SimpleDraweeView) view.findViewById(R.id.recommond_item_right);
            TextView titleRight=(TextView)view.findViewById(R.id.item_title_right);
            TextView priceRight=(TextView)view.findViewById(R.id.price_right);
            int realPos=position*2;
            Item left,right;
            left=mainPage.getHotRecommand().get(realPos);
            if (mainPage.getHotRecommand().size()>realPos+1)
            {
                right=mainPage.getHotRecommand().get(realPos+1);
                rightImg.setImageURI(Uri.parse(right.getImgAddress()));
                titleRight.setText(right.getTitle());
                String price=String.valueOf(right.getPrice());
                priceRight.setText(price);
            }

            leftImg.setImageURI(Uri.parse(left.getImgAddress()));
            titleLeft.setText(left.getTitle());
            String price=String.valueOf(left.getPrice());
            priceLeft.setText(price);
        }

    }
}
