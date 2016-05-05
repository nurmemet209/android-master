package com.cn.pppcar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cn.adapter.BaseListAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.ReceiveAddressListAdapter;
import com.cn.commans.ActivitySwitcher;
import com.cn.entity.Item;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收货地址选择
 * Created by nurmemet on 2016/4/7.
 */
public class ReceiveAddressListAct extends BaseAct {

    private int selectedAddressId;


    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    ReceiveAddressListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_receive_address_list);
        ButterKnife.bind(this);
        init();
    }


    private void init() {
        adapter = new ReceiveAddressListAdapter(this, getList());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new CustomItemDecoration(this, getResources().getDimensionPixelSize(R.dimen.main_big_divider_height)));
        recyclerView.setAdapter(adapter);
    }

    @OnClick(R.id.add_reveive_addr)
    public void addNewReceiveAddr(View view) {
        ActivitySwitcher.toReceiveAddressEditAct(this);
    }

}
