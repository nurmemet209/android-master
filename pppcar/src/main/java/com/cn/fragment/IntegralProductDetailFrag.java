package com.cn.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.cn.adapter.BannerAdapter;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.ResIntegralProductDetail;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by nurmemet on 2016/4/28.
 */
public class IntegralProductDetailFrag extends BaseFrag {

    @Bind(R.id.banner)
    protected AutoScrollViewPager banner;
    @Bind(R.id.banner_indicator)
    protected CircleIndicator indicator;
    //商品名称
    @Bind(R.id.title)
    protected TextView mTitle;
    //详情
    @Bind(R.id.sub_title)
    protected TextView mSubTitle;
    //积分价格
    @Bind(R.id.integral_price)
    protected TextView mIntegralPrice;
    //零售价
    @Bind(R.id.retail_price)
    protected TextView mRetailPrice;
    //出厂编号
    @Bind(R.id.factory_number)
    protected TextView mFactoryNumber;
    //品牌
    @Bind(R.id.brand)
    protected TextView mBrand;
    //库存
    @Bind(R.id.stock_num)
    protected TextView mStockNum;
    //运费
    @Bind(R.id.freight)
    protected TextView mFreight;


    private ResIntegralProductDetail integralProductDetail;

    static public IntegralProductDetailFrag getInstance() {
        IntegralProductDetailFrag frag = new IntegralProductDetailFrag();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        init();
        return mainView;
    }

    private void init() {


        new Thread(new Runnable() {
            @Override
            public void run() {

                apiHandler.getIntegralProductDetail(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (NetUtil.isSucced(response)) {
                            integralProductDetail = apiHandler.toObject(NetUtil.getData(response), ResIntegralProductDetail.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    ArrayList viewList = new ArrayList();
                                    String[] imgList = integralProductDetail.getDetailImg().split(",");
                                    for (int i = 0; i < imgList.length; i++) {

                                        SimpleDraweeView img = new SimpleDraweeView(getActivity());
                                        Uri uri = Uri.parse(imgList[i]);
                                        img.setImageURI(uri);
                                        viewList.add(img);
                                    }
                                    if (integralProductDetail != null) {


                                        BannerAdapter adapter = new BannerAdapter(getActivity(), viewList);
                                        banner.setAdapter(adapter);
                                        banner.setInterval(4000);
                                        // banner.setScrollDurationFactor(5);
                                        banner.setCycle(true);
                                        banner.setOffscreenPageLimit(viewList.size());
                                        banner.setBorderAnimation(true);
                                        banner.startAutoScroll();
                                        indicator.setViewPager(banner);


                                        mTitle.setText(integralProductDetail.getName());
                                        mSubTitle.setText(integralProductDetail.getBriefDescripe());
                                        mIntegralPrice.setText(String.valueOf(integralProductDetail.getIntegralPrice()));
                                        //mRetailPrice.setText(integralProductDetail.get);
                                        mFactoryNumber.setText(integralProductDetail.getProductNumber());
                                        mBrand.setText(integralProductDetail.getTypeName());
                                        mStockNum.setText(String.valueOf(integralProductDetail.getStockNumber()));
                                        mFreight.setText("到付");

                                    }
                                }
                            });

                        } else {

                            showToast(NetUtil.getMessage(response));
                        }
                    }
                }, null);
            }
        }).start();

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_integral_product_detail;
    }




    @OnClick(R.id.exchange_quickly)
    public void exchangeQuickly(View view) {
        ActivitySwitcher.toIntegralPaySettelmentAct(getActivity());
    }

    private Handler mHandler = new Handler();
}
