package com.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

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

    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return super.isViewFromObject(view, object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }
}
