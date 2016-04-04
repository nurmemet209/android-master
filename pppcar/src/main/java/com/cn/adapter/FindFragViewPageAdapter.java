package com.cn.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.Goods;
import com.cn.fragment.RefiteWork;
import com.cn.fragment.RelicateShop;
import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/2.
 */
public class FindFragViewPageAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public FindFragViewPageAdapter(FragmentManager fm,Context context) {
        super(fm);
        mContext=context;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
                frag = Goods.getInstance();
                break;
            case 1:
                frag = RelicateShop.getInstance();
                break;
            case 2:
                frag = RefiteWork.getInstance();
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
                str = mContext.getString(R.string.have_goods);
                break;
            case 1:
                str = mContext.getString(R.string.delicate_shop);
                break;
            case 2:
                str = mContext.getString(R.string.refit_work);
                break;
            default:
                break;
        }
        return str;
    }
}
