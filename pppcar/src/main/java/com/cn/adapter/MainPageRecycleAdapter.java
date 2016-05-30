package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;

import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.cn.commans.ActivitySwitcher;
import com.cn.entity.ResIndexBanner;
import com.cn.entity.ResIndexBrandType;
import com.cn.entity.ResIndexPublicElement;
import com.cn.entity.ResIndexRecommendType;
import com.cn.entity.ResRemonmendPro;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by nurmemet on 2016/3/30.
 */
public class MainPageRecycleAdapter extends RecyclerView.Adapter<MainPageRecycleAdapter.CustomViewHolder> {
    private ResIndexPublicElement mainPage;
    private List<ResRemonmendPro> recommonds;
    private Context mContext = null;


    public MainPageRecycleAdapter(ResIndexPublicElement mainPage, Context mContext) {
        this.mainPage = mainPage;
        this.mContext = mContext;
        this.recommonds = mainPage.getPageResRemonmendPro().getResRemonmendPros();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else {
            return 1;
        }

    }

    @Override
    public MainPageRecycleAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View headerView = LayoutInflater.from(mContext).inflate(R.layout.header_main_page, null);
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
            setRecommond(holder.itemView, position - 1);
        }

    }

    @Override
    public int getItemCount() {
        if (recommonds != null) {
            int size = (int) Math.ceil(recommonds.size() + 1);
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
            TextView search = (TextView) view.findViewById(R.id.search_edit_text);
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
        if (Util.isNoteEmpty(mainPage.getResIndexBanners())) {
            ConvenientBanner<ResIndexBanner> banner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
            final CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.banner_indicator);

//            ArrayList viewList = new ArrayList();
//            for (int i = 0; i < mainPage.getResIndexBanners().size(); i++) {
//                ResIndexBanner item = mainPage.getResIndexBanners().get(i);
//                SimpleDraweeView img = new SimpleDraweeView(mContext);
//                Uri uri = Uri.parse(item.getImgUrl());
//                img.setImageURI(uri);
//                viewList.add(img);
//                img.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//            }
            banner.setPageIndicator(new int[]{R.drawable.indicator_red_radius, R.drawable.indicator_gray_radius});
            banner.startTurning(5000);

            banner.setScrollDuration(1000);
            banner.setCanLoop(true);
            banner.setManualPageable(true);

            banner.setPages(new CBViewHolderCreator() {
                @Override
                public Object createHolder() {

                    return new LocalImageHolderView();
                }
            }, mainPage.getResIndexBanners());
            banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    //  indicator.se
                }

                @Override
                public void onPageSelected(int position) {

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
//            BannerAdapter adapter = new BannerAdapter(mContext, viewList);
//            banner.setAdapter(adapter);
//            banner.setInterval(4000);
//            // banner.setScrollDurationFactor(5);
//            banner.setCycle(true);
//            banner.setOffscreenPageLimit(mainPage.getResIndexBanners().size());
//            banner.setBorderAnimation(true);
//            banner.startAutoScroll();
//            indicator.setViewPager(banner);

        }
        //品牌中心
        View brandCenter = view.findViewById(R.id.brand_center_l);
        brandCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.toBrandCenterAct((Activity) mContext);
            }
        });
        //我的订单
        View myOrder = view.findViewById(R.id.my_order_l);
        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.toMyOrderAct((Activity) mContext);
            }
        });
        //拍卖中心
        View auctionCenter = view.findViewById(R.id.auction_center_l);
        auctionCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.toAuctionAct((Activity) mContext);
            }
        });

        //趴趴头条
        if (true/*Util.isNoteEmpty(mainPage.getPapaHeadLines())*/) {
//            ArrayList<Item> papaHeadLine = mainPage.getPapaHeadLines();
//            TextView tv = (TextView) view.findViewById(R.id.papa_headline);
//            tv.setText(papaHeadLine.get(0).getName());

            AdapterViewFlipper flipper = (AdapterViewFlipper) view.findViewById(R.id.headline_flipper);
            HeadLineFlipperAdapter headLineAdapter = new HeadLineFlipperAdapter(null, mContext);
            ;
            flipper.setAdapter(headLineAdapter);

            flipper.setOutAnimation(mContext, R.anim.obj_bottom_out);
            flipper.setInAnimation(mContext, R.anim.obj_top_in);
            //flipper.startFlipping();
        }
        //综合
        if (Util.isNoteEmpty(mainPage.getResIndexRecommendTypes())) {

            List<ResIndexRecommendType> universalItems = mainPage.getResIndexRecommendTypes();
            int m = 1;
            for (int i = 0; i < universalItems.size(); i++) {
                ResIndexRecommendType item = universalItems.get(i);
                SimpleDraweeView img = null;
                if (!item.getIsLarge()) {
                    img = (SimpleDraweeView) view.findViewWithTag("universal_big");
                } else {
                    img = (SimpleDraweeView) view.findViewWithTag("universal_small_" + m);
                    m++;
                }
                img.setImageURI(Uri.parse(item.getImgUrl()));
            }
        }

        ViewGroup container = (ViewGroup) view.findViewById(R.id.container);
        for (int i = 0; i < mainPage.getResIndexBrandTypes().size(); i++) {
            ResIndexBrandType item = mainPage.getResIndexBrandTypes().get(i);
            View v = LayoutInflater.from(mContext).inflate(R.layout.item_main_page, null);
            TextView title = (TextView) v.findViewById(R.id.title);
            TextView price = (TextView) v.findViewById(R.id.price);
            TextView productName = (TextView) v.findViewById(R.id.product_name);
            SimpleDraweeView leftBig = (SimpleDraweeView) v.findViewById(R.id.left_big);
            SimpleDraweeView rightBig = (SimpleDraweeView) v.findViewById(R.id.right_big);
            SimpleDraweeView leftSmall = (SimpleDraweeView) v.findViewById(R.id.left_small);
            SimpleDraweeView rightSmall = (SimpleDraweeView) v.findViewById(R.id.right_small);

            leftBig.setImageURI(Uri.parse(item.getImgUrl()));
            rightBig.setImageURI(Uri.parse(item.getBsProduct().getImgs()));
            leftSmall.setImageURI(Uri.parse(item.getIndexBrandLists().get(0).getBrandImg()));
            rightSmall.setImageURI(Uri.parse(item.getIndexBrandLists().get(1).getBrandImg()));

            title.setText(item.getTitle());
            price.setText(String.valueOf(item.getBsProduct().getRetailPrice()));
            productName.setText(item.getBsProduct().getName());

            container.addView(v);

        }
    }

    private int getIndex(int index, int size) {
        return size > index ? index : 0;
    }

    private void setRecommond(View view, final int position) {
        if (mainPage == null)
            return;
        ;
        if (Util.isNoteEmpty(mainPage.getPageResRemonmendPro().getResRemonmendPros())) {

            SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
            TextView title = (TextView) view.findViewById(R.id.title);
            TextView price = (TextView) view.findViewById(R.id.price);


            ResRemonmendPro item = mainPage.getPageResRemonmendPro().getResRemonmendPros().get(position);
            img.setImageURI(Uri.parse(item.getImgs()));
            title.setText(item.getName());
            String priceStr = String.valueOf(item.getRetailPrice());
            price.setText(priceStr);

        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.toProductDetailAct((Activity) mContext, recommonds.get(position).getId());
            }
        });

    }

    class LocalImageHolderView implements Holder<ResIndexBanner> {
        private ImageView img;

        @Override
        public View createView(Context context) {
            img = new SimpleDraweeView(mContext);
            return img;
        }

        @Override
        public void UpdateUI(Context context, final int position, ResIndexBanner data) {
            Uri uri = Uri.parse(data.getImgUrl());
            img.setImageURI(uri);
        }
    }

    ;


}