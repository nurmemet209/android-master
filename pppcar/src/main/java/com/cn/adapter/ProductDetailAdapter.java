package com.cn.adapter;










import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.EvaluateFrag;
import com.cn.fragment.ProductDetailFrag;
import com.cn.fragment.ProductFrag;
import com.cn.fragment.WorkFrag;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class ProductDetailAdapter extends FragmentPagerAdapter {


    public ProductDetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        switch (position) {
            case 0:
                fragment=new ProductFrag();
                break;
            case 1:
                fragment=new ProductDetailFrag();
                break;
            case 2:
                fragment=new EvaluateFrag();
                break;
            case 3:
                fragment=new WorkFrag();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title="";
        switch (position){
            case 0:
                title="产品";
                break;
            case 1:
                title="产品详情";
                break;
            case 2:
                title="评价";
                break;
            case 3:
                title="作业";
                break;
            default:
                break;

        }
        return title;
    }
}
