package com.cn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.IntegralMallFrag;
import com.cn.fragment.IntegralProductDetailFrag;
import com.cn.fragment.WebViewFrag;
import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/28.
 */
public class IntegralProductDetailViewPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public IntegralProductDetailViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = IntegralProductDetailFrag.getInstance();
                break;
            case 1:
                frag = WebViewFrag.getInstance();
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
                str = mContext.getString(R.string.integral_product);
                break;
            case 1:
                str = mContext.getString(R.string.image_letter_introduce);
                break;
            default:
                break;
        }
        return str;
    }
}
