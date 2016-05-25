package com.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.BusineseCollectFrag;
import com.cn.fragment.ContentCollectFrag;
import com.cn.fragment.ProductCollectFrag;

/**
 * Created by nurmemet on 2016/5/23.
 */
public class CollectViewPagerAdapter extends FragmentPagerAdapter {


    public CollectViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position) {
            case 0:
                fragment= ProductCollectFrag.getInstance();
                break;
            case 1:
                fragment= BusineseCollectFrag.getInstance();
                break;
            case 2:
                fragment= ContentCollectFrag.getInstance();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="宝贝";
                break;
            case 1:
                title="商家";
                break;
            case 2:
                title="内容";
                break;

            default:
                break;

        }
        return title;
    }
}
