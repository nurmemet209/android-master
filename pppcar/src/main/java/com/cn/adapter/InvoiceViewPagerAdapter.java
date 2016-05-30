package com.cn.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cn.fragment.InvoiceCommonListFrag;
import com.cn.fragment.InvoiceAddTaxFrag;

/**
 * Created by nurmemet on 5/30/2016.
 */
public class InvoiceViewPagerAdapter extends FragmentPagerAdapter {


    public InvoiceViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                //普通发票
                fragment = InvoiceCommonListFrag.getInstance();
                break;
            case 1:
                //增值税发票
                fragment = InvoiceAddTaxFrag.getInstance();
                break;
            default:
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "普通发票";
                break;
            case 1:
                title = "增值税发票";
                break;
            default:
                break;
        }
        return title;
    }
}
