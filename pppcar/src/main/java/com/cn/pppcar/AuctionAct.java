package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cn.adapter.AuctionCenterAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.entity.Item;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/17.
 */
public class AuctionAct extends BaseAct {


    @Bind(R.id.recycle_view)
   protected RecyclerView recyclerView;

    private AuctionCenterAdapter adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auction_list);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ArrayList<Item> l=getList();
        l.get(0).setState(1);
        adapter=new AuctionCenterAdapter(this,l);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CustomItemDecoration decoration=new CustomItemDecoration(this,getResources().getDimensionPixelSize(R.dimen.main_big_divider_height));
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(adapter);
    }


    protected ArrayList<Item> getList() {
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

    protected Item getItem(String str) {
        Item item = new Item();
        item.setImg(str);
        item.setName("D Forged 轮毂 S11系列 规格:18*8.5J  PCD:5*120 表面处理:中银拉丝（宝马3 宝马4 君威 迈锐宝）ET值:35 中心孔:72.5");
        item.setPrice(123);
        item.setCollectNum(230565);
        return item;
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(actFinishAnimInResId,actFinishAnimOutResId);
    }
}
