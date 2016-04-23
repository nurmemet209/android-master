package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cn.adapter.MyAuctionDetailAdapter;
import com.cn.entity.Item;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/20.
 */
public class MyAuctionDetail extends BaseAct {

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    private MyAuctionDetailAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_my_auction_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Item head=new Item();

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

        head.setBannerAddressList(imageUrlList);
        head.setTitle("D Forged 轮毂 S11系列 规格:18*8.5J  PCD:5*120 表面处理:中银拉丝（宝马3 宝马4 君威 迈锐宝）ET值:35 中心孔:72.5");
        head.setCurrentPrice(4800.00F);
        //期数
        head.setNum(2);
        //出价次数
        head.setBidCount(23);
        head.setPhoneNum("13072182811");


        ArrayList<Item> list=new ArrayList<>();

        for (int i=0;i<5;i++){
            Item item=new Item();
            item.setPhoneNum("13072182811");
            item.setPrice(123);
            item.setTime(Calendar.getInstance().getTimeInMillis());
            list.add(item);
        }


        adapter=new MyAuctionDetailAdapter(this,list,head);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
