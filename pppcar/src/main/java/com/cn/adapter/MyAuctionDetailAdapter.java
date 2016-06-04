package com.cn.adapter;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.format.DateUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.cn.commans.AuctionHelper;
import com.cn.commans.Constants;
import com.cn.commans.DateUtil;
import com.cn.commans.SpanHelper;
import com.cn.entity.AuctionBidResBean;
import com.cn.entity.AuctionTimeResBean;
import com.cn.entity.CollectAuctionDetailResBean;
import com.cn.entity.Item;
import com.cn.entity.ResIndexBanner;
import com.cn.pppcar.AuctionBid;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.RaPageIndicator;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.Bind;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


/**
 * Created by nurmemet on 2016/4/20.
 */
public class MyAuctionDetailAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<AuctionTimeResBean> list;
    private ArrayList<Object> objectList = new ArrayList<>();
    private CollectAuctionDetailResBean bean;
    private SpanHelper spanHelper;
    private View headerView;
    private Handler mHandler;
    private AuctionHelper auctionHelper;

    public MyAuctionDetailAdapter(Context mContext, CollectAuctionDetailResBean bean) {
        this.mContext = mContext;
        if (bean != null) {
            this.list = bean.getAuctionTimeResBeans();
            if (Util.isNoteEmpty(list)) {
                revert();
            }
        }
        this.bean = bean;
        spanHelper = new SpanHelper(mContext);
        mHandler=new Handler();
        update();
    }

    private void revert() {
        for (int i = 0; i < list.size(); i++) {
            objectList.add(list.get(i).getDate());
            for (int j = 0; j < list.get(i).getAuctionBidResBean().size(); j++) {
                objectList.add(list.get(i).getAuctionBidResBean().get(j));
            }
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == 0) {
            headerView=view = LayoutInflater.from(mContext).inflate(R.layout.header_auction_product_frag, null);
        } else if (viewType == 1) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_bid_record_date, null);
        } else  if (viewType==2){
            view = LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_bid_record_main, null);
        }
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==0) {
            bindHeadData(holder.itemView);
        } else if (getItemViewType(position)==1) {
            String date = (String) objectList.get(position - 1);
            View view = holder.itemView;
            TextView dateTv = (TextView) view.findViewById(R.id.time);
            dateTv.setText(date);
        } else {
            AuctionBidResBean item = (AuctionBidResBean) objectList.get(position - 1);
            View view = holder.itemView;
            TextView price = (TextView) view.findViewById(R.id.price);
            price.setText(Double.toString(item.getBidPrice()));
            TextView date = (TextView) view.findViewById(R.id.time);
            date.setText(item.getBidDate());

            TextView telNum = (TextView) view.findViewById(R.id.tel_num);
            telNum.setText(item.getMobileNumber());
            //状态，出局，或领先
            TextView state = (TextView) view.findViewById(R.id.state);
            GradientDrawable dr = (GradientDrawable) mContext.getResources().getDrawable(R.drawable.round_rect);
            dr.setColor(mContext.getResources().getColor(R.color.main_red));
            state.setBackground(dr);
            state.setTextColor(mContext.getResources().getColor(R.color.white));


        }

    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (objectList.get(position-1) instanceof String) {
            return 1;

        } else if (objectList.get(position-1) instanceof AuctionBidResBean) {
            return 2;
        }
        return 0;


    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(objectList)) {
            return objectList.size()+1;
        }
        return 1;
    }




    private void bindHeadData(View view) {
        if (auctionHelper==null){
            auctionHelper=new AuctionHelper();
        }
//        AutoScrollViewPager banner = (AutoScrollViewPager) view.findViewById(R.id.banner);
//        CircleIndicator indicator = (CircleIndicator) view.findViewById(R.id.banner_indicator);
//        String[] list = bean.getImgUrl().split(",");
//        ArrayList viewList = new ArrayList();
//        if (list != null) {
//            for (int i = 0; i < list.length; i++) {
//                SimpleDraweeView img = new SimpleDraweeView(mContext);
//                Uri uri = Uri.parse(list[i]);
//                img.setImageURI(uri);
//                viewList.add(img);
//            }
//        }
//        BannerAdapter adapter = new BannerAdapter(mContext, viewList);
//        banner.setAdapter(adapter);
//        banner.setInterval(4000);
//        // banner.setScrollDurationFactor(5);
//        banner.setCycle(true);
//        banner.setOffscreenPageLimit(list.length);
//        banner.setBorderAnimation(true);
//        banner.startAutoScroll();
//        indicator.setViewPager(banner);
        String[] tempList = bean.getImgUrl().split(",");
        List<String> bannerList=new ArrayList<>();
        Collections.addAll(bannerList,tempList);
        ConvenientBanner<String> banner = (ConvenientBanner) view.findViewById(R.id.convenientBanner);
        final RaPageIndicator indicator = (RaPageIndicator) view.findViewById(R.id.banner_indicator);
        banner.startTurning(5000);
        banner.setScrollDuration(1000);
        banner.setCanLoop(true);
        banner.setPointViewVisible(false);
        banner.setManualPageable(true);
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {

                return new LocalImageHolderView();
            }
        },bannerList);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        indicator.setBackgroundDr( R.drawable.indicator_red_radius,R.drawable.indicator_gray_radius);
        indicator.init(bannerList.size(), R.dimen.padding_normal, null);
        indicator.setCurrentItem(banner.getCurrentItem());

        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(bean.getProductName());

        TextView bidCount = (TextView) view.findViewById(R.id.bid_number);
        bidCount.setText(spanHelper.priceSpan(R.string.bid_number_, bean.getBidNumber()));

        TextView curPrice = (TextView) view.findViewById(R.id.current_price);
        curPrice.setText(spanHelper.priceSpan(R.string.current_price_, bean.getCurrentPrice()));

        TextView fixedPrice = (TextView) view.findViewById(R.id.fixed_price);
        fixedPrice.setText(spanHelper.priceSpan(R.string.fixed_price_, bean.getaPrice()));

        TextView marketPrice = (TextView) view.findViewById(R.id.market_price);
        marketPrice.setText(spanHelper.priceSpan(R.string.market_price_, bean.getRetailPrice()));

        TextView term=(TextView)view.findViewById(R.id.term);
        term.setText("第"+bean.getTerm()+"期");

        TextView leftTime=(TextView)view.findViewById(R.id.left_time);

        long left=bean.getEndTime()-bean.getCurrentTime();
        leftTime.setText(spanHelper.auctionTime(left));

        TextView history=(TextView)view.findViewById(R.id.bid_history_time);
        history.setText("（自"+DateUtil.convertTime(bean.getStartTime())+"开始）");

    }

    private void update(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {

                TextView leftTime=(TextView) headerView.findViewById(R.id.left_time);
                leftTime.setText(spanHelper.auctionTime(bean.getCurrentTime()+auctionHelper.getDelta(), bean.getEndTime()));

                update();
            }
        },1000);
    }


    class LocalImageHolderView implements Holder<String> {
        private ImageView img;

        @Override
        public View createView(Context context) {
            img = new SimpleDraweeView(mContext);
            return img;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            Uri uri = Uri.parse(data);
            img.setImageURI(uri);
        }
    }


}
