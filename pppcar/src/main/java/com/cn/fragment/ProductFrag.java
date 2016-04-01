package com.cn.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.adapter.BannerAdapter;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class ProductFrag extends Fragment {

    private View mainView;

    @Bind(R.id.banner)
    protected AutoScrollViewPager banner;
    @Bind(R.id.banner_indicator)
    protected CircleIndicator indicator;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView=inflater.inflate(R.layout.frag_product,null);
        ButterKnife.bind(this,mainView);
        init();
        return mainView;
    }

    private void init(){
        setUpData();
    }

    private void setUpData(){

        ArrayList<String> list=getList();
        ArrayList viewList = new ArrayList();
        for (int i = 0; i <list.size(); i++) {

            SimpleDraweeView img = new SimpleDraweeView(getActivity());
            Uri uri = Uri.parse(list.get(i));
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
