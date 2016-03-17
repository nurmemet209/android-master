package com.cn.pppcar.com.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.CarBrandFragment;
import com.cn.fragment.MainFragment;
import com.cn.fragment.MeFragment;
import com.cn.fragment.PartsBrandFragment;

/**
 * Created by nurmemet on 2015/12/17.
 */
public class MainPageAdapter extends FragmentPagerAdapter {

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag= MainFragment.getInstance();
                break;
            case 1:
                frag= CarBrandFragment.getInstance();
                break;
            case 2:
                frag= PartsBrandFragment.getInstance();
                break;
            case 3:
                frag= MeFragment.getInstance();
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
}
