package com.cn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.IntegralDetailFragment;
import com.cn.fragment.IntegralMallFrag;
import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralMallViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public IntegralMallViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = IntegralMallFrag.getInstance(1);
                break;
            case 1:
                frag = IntegralMallFrag.getInstance(1);
                break;
            case 2:
                frag = IntegralMallFrag.getInstance(1);
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
                str = mContext.getString(R.string.all);
                break;
            case 1:
                str = mContext.getString(R.string.integral);
                break;
            case 2:
                str = mContext.getString(R.string.on_shelves_time);
                break;
            default:
                break;
        }
        return str;
    }
}
