package com.cn.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.Nullable;
import android.support.v7.widget.ButtonBarLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.adapter.BannerAdapter;
import com.cn.commans.ActivitySwitcher;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

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
        setUpData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_integral_product_detail;
    }


    private void setUpData() {

        ArrayList<Item> list = getList();
        ArrayList viewList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {

            SimpleDraweeView img = new SimpleDraweeView(getActivity());
            Uri uri = Uri.parse(list.get(i).getImgAddress());
            img.setImageURI(uri);
            viewList.add(img);
        }
        BannerAdapter adapter = new BannerAdapter(getActivity(), viewList);
        banner.setAdapter(adapter);
        banner.setInterval(4000);
        // banner.setScrollDurationFactor(5);
        banner.setCycle(true);
        banner.setOffscreenPageLimit(list.size());
        banner.setBorderAnimation(true);
        banner.startAutoScroll();
        indicator.setViewPager(banner);
    }


    @OnClick(R.id.exchange_quickly)
    public void exchangeQuickly(View view) {
        ActivitySwitcher.toIntegralPaySettelmentAct(getActivity());
    }
}
