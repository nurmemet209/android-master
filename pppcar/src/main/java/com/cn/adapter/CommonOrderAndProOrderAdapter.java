package com.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.AllOrderFrag;
import com.cn.pppcar.MyOrderFrag;

/**
 * Created by nurmemet on 2016/5/5.
 */
public class CommonOrderAndProOrderAdapter extends FragmentPagerAdapter {



    public CommonOrderAndProOrderAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position) {
            case 0:
                fragment= MyOrderFrag.getInstance();
                break;
            case 1:
                fragment= AllOrderFrag.getInstance();
                break;

            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="普通订单";
                break;
            case 1:
                title="预订单";
                break;

            default:
                break;

        }
        return title;
    }
}
