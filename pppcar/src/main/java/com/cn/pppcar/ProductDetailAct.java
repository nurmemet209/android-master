package com.cn.pppcar;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Response;
import com.cn.adapter.ProductDetailAdapter;
import com.cn.commans.NetUtil;
import com.cn.fragment.ProductFrag;
import com.cn.localutils.EventBusEv;
import com.cn.viewpager.CustomViewPager;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class ProductDetailAct extends BaseAct {


    @Bind(R.id.tab_container)
    protected AdvancedPagerSlidingTabStrip tabContainer;
    @Bind(R.id.product_detail_view_pager)
    protected CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_product_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ProductDetailAdapter adapter = new ProductDetailAdapter(this.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabContainer.setViewPager(viewPager);

    }

    @OnClick(R.id.collect)
    public void collect(View view) {
        EventBus.getDefault().post(new EventBusEv(ProductFrag.COLLECT, view));
    }


    @OnClick(R.id.put_into_cart)
    public void add2Cart(View view){
        EventBus.getDefault().post(new EventBusEv(ProductFrag.ADD_2_CART, null));
    }


}
