package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.cn.adapter.CommonOrderAndProOrderAdapter;
import com.cn.adapter.InvoiceViewPagerAdapter;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 5/30/2016.
 */
public class InvoiceAct extends BaseViewPagerAct {


    private InvoiceViewPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_invoice_information);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        adapter = new InvoiceViewPagerAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        setUpViewPager(adapter);
    }
}
