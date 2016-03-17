package com.cn.pppcar.com.cn.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class BannerAdapter extends PagerAdapter {

    private ArrayList<View> viewList;
    private Context mContext;

    public BannerAdapter(Context mContext, ArrayList<View> viewList) {
        this.mContext = mContext;
        this.viewList = viewList;
    }



    @Override
    public int getCount() {
        return viewList.size();//页卡数
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;//官方推荐写法
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(viewList.get(position));//添加页卡
        return viewList.get(position);
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));//删除页卡
    }



}
