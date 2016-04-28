package com.cn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cn.fragment.AllOrderFrag;
import com.cn.fragment.BrandFrag;
import com.cn.fragment.WaitDeliverFrag;
import com.cn.fragment.WaitEximinateFrag;
import com.cn.fragment.WaitPayFrag;
import com.cn.fragment.WaitReceiveFrag;
import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/25.
 */
public class BrandViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public BrandViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = BrandFrag.getInstance(1);
                break;
            case 1:
                frag = BrandFrag.getInstance(1);
                break;

            default:
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String str = "";
        switch (position) {
            case 0:
                str = mContext.getString(R.string.all);
                break;
            case 1:
                str = mContext.getString(R.string.most_new);
                break;
            default:
                break;
        }
        return str;
    }
}
