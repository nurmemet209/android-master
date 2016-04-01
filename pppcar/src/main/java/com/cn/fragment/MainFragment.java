package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cn.adapter.BannerAdapter;
import com.cn.adapter.MainPageRecycleAdapter;
import com.cn.entity.Item;
import com.cn.entity.MainPage;
import com.cn.pppcar.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class MainFragment extends Fragment {

    private View mainView;
    private MainPageRecycleAdapter adapter;
    @Bind(R.id.main_page_recycle_view)
    protected RecyclerView recyclerView;

    public static MainFragment getInstance() {
        MainFragment frag = new MainFragment();
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.main_frag, null);
        ButterKnife.bind(this, mainView);
        initBanner();
        return mainView;
    }

    private void initBanner() {


        MainPage mainPage = new MainPage();
        mainPage.setBannerImages(getList());
        mainPage.setExhaustList(getList());
        mainPage.setHobList(getList());
        mainPage.setUniversalItems(getList());
        mainPage.setHotRecommand(getList());
        mainPage.setShockAbsorber(getList());
        adapter = new MainPageRecycleAdapter(mainPage, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

    }

    private ArrayList<Item> getList() {
        ArrayList<Item> imageUrlList = new ArrayList<>();
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg"));
        imageUrlList
                .add(getItem("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg"));
        imageUrlList
                .add(getItem("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));

        return imageUrlList;
    }

    private Item getItem(String str) {
        Item item = new Item();
        item.setImgAddress(str);
        item.setTitle("nurmemet");
        return item;
    }
}
