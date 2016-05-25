package com.cn.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.OrderAdapter;
import com.cn.commans.NetUtil;
import com.cn.entity.PageResPersonalCenterOrder;
import com.cn.pppcar.R;
import com.cn.util.Util;

import org.json.JSONObject;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class AllOrderFrag extends BaseFrag {

    private int orderType = 1;


    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    private OrderAdapter adapter;
    PageResPersonalCenterOrder allOrder;

    public static AllOrderFrag getInstance(int orderType) {
        AllOrderFrag frag = new AllOrderFrag();
        Bundle bd = new Bundle();
        bd.putInt("orderType", orderType);
        frag.setArguments(bd);
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        orderType=getArguments().getInt("orderType");
        ButterKnife.bind(this, mainView);
        init();
        return mainView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_all_order;
    }


    private void init() {


        new Thread(new Runnable() {
            @Override
            public void run() {


                apiHandler.getMyOrder(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (NetUtil.isSucced(response)) {
                            allOrder = apiHandler.toObject(NetUtil.getArrayData(response), PageResPersonalCenterOrder.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (allOrder != null && Util.isNoteEmpty(allOrder.getResOrders())) {
                                        adapter = new OrderAdapter(allOrder.getResOrders(), getActivity(), orderType);
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                        CustomItemDecoration decoration = new CustomItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.main_big_divider_height));
                                        recyclerView.addItemDecoration(decoration);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }
                            });

                        } else {

                            showToast(NetUtil.getMessage(response));
                        }


                    }
                }, null);
            }
        }).start();


    }

    private Handler mHandler = new android.os.Handler();


}
