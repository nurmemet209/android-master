package com.cn.pppcar;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;

import com.cn.adapter.ProductDetailAdapter;
import com.cn.viewpager.CustomViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class ProductDetailAct extends FragmentActivity {

    @Bind(R.id.tab_container)
    protected TabLayout tabContainer;
    @Bind(R.id.product_detail_view_pager)
    protected CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_product_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init(){

        ProductDetailAdapter adapter=new ProductDetailAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
       tabContainer.setupWithViewPager(viewPager);
        //tabContainer.setTabMode(TabLayout.MODE_FIXED);






    }



}
