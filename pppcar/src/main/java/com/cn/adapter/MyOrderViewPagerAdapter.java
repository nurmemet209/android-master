package com.cn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.AllOrderFrag;
import com.cn.fragment.RefiteWork;
import com.cn.fragment.RelicateShop;
import com.cn.fragment.WaitDeliverFrag;
import com.cn.fragment.WaitEximinateFrag;
import com.cn.fragment.WaitPayFrag;
import com.cn.fragment.WaitReceiveFrag;
import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class MyOrderViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MyOrderViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = AllOrderFrag.getInstance(1);
                break;
            case 1:
                frag = AllOrderFrag.getInstance(2);
                break;
            case 2:
                frag = AllOrderFrag.getInstance(3);
                break;
            case 3:
                frag = AllOrderFrag.getInstance(4);
                break;
            case 4:
                frag = AllOrderFrag.getInstance(5);
                break;
            default:
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String str = "";
        switch (position) {
            case 0:
                str = mContext.getString(R.string.all);
                break;
            case 1:
                str = mContext.getString(R.string.waiting_examine);
                break;
            case 2:
                str = mContext.getString(R.string.waiting_pay);
                break;
            case 3:
                str = mContext.getString(R.string.waiting_diliver);
                break;
            case 4:
                str = mContext.getString(R.string.waiting_receive);
                break;
            default:
                break;
        }
        return str;
    }
}
