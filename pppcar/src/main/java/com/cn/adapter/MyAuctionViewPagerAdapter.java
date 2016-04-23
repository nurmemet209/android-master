package com.cn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.AllOrderFrag;
import com.cn.fragment.AuctionAllFrag;
import com.cn.fragment.AuctionFailed;
import com.cn.fragment.AuctionIngFrag;
import com.cn.fragment.AuctionSuccess;
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
public class MyAuctionViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MyAuctionViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = AuctionAllFrag.getInstance();
                break;
            case 1:
                frag = AuctionIngFrag.getInstance();
                break;
            case 2:
                frag = AuctionSuccess.getInstance();
                break;
            case 3:
                frag = AuctionFailed.getInstance();
                break;

            default:
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String str = "";
        switch (position) {
            case 0:
                str = mContext.getString(R.string.all_auction);
                break;
            case 1:
                str = mContext.getString(R.string.auction_ing);
                break;
            case 2:
                str = mContext.getString(R.string.auction_success);
                break;
            case 3:
                str = mContext.getString(R.string.auction_failed);
                break;

            default:
                break;
        }
        return str;
    }
}
