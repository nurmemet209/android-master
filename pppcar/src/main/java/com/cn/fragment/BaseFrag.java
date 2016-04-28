package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.entity.Item;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/25.
 */
public abstract class BaseFrag extends Fragment {

    protected View mainView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(getLayoutResId(), null);
        return mainView;
    }

    protected abstract int getLayoutResId();


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
        item.setImgAddress(str);
        item.setTitle("D Forged 轮毂 S11系列 规格:18*8.5J  PCD:5*120 表面处理:中银拉丝（宝马3 宝马4 君威 迈锐宝）ET值:35 中心孔:72.5");
        item.setPrice(123);
        item.setCollectNum(230565);
        return item;
    }
}
