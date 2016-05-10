package com.cn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.AllOrderFrag;
import com.cn.fragment.SearchListFrag;
import com.cn.fragment.WaitDeliverFrag;
import com.cn.fragment.WaitEximinateFrag;
import com.cn.fragment.WaitPayFrag;
import com.cn.fragment.WaitReceiveFrag;
import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/8.
 */
public class SearchClassificationViewPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;

    public SearchClassificationViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = SearchListFrag.getInstance();
                break;
            case 1:
                frag = SearchListFrag.getInstance();
                break;
            case 2:
                frag = SearchListFrag.getInstance();
                break;

            default:
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String str = "";
        switch (position) {
            case 0:
                str = mContext.getString(R.string.universal);
                break;
            case 1:
                str = mContext.getString(R.string.most_new);
                break;
            case 2:
                str = mContext.getString(R.string.price);
                break;


            default:
                break;
        }
        return str;
    }
}
