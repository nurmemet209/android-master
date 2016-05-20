package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Response;
import com.cn.adapter.MyAuctionDetailAdapter;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.CollectAuctionDetailResBean;
import com.cn.entity.Item;
import com.cn.entity.ReturnBean;
import com.cn.net.ApiHandler;
import com.cn.util.UIHelper;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/4/20.
 */
public class AuctionDetailAct extends BaseAct {

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;
    private MyAuctionDetailAdapter adapter;
    CollectAuctionDetailResBean auctionDetail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_my_auction_detail);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        apiHandler.getAuctionDetail("65", new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    auctionDetail = apiHandler.toObject(NetUtil.getData(response), CollectAuctionDetailResBean.class);
                    adapter = new MyAuctionDetailAdapter(AuctionDetailAct.this, auctionDetail);
                    recyclerView.setLayoutManager(new LinearLayoutManager(AuctionDetailAct.this));
                    recyclerView.setAdapter(adapter);
                } else {
                    showToast(NetUtil.getError(response));
                }
            }
        }, null);
    }

    @OnClick(R.id.auction_bid)
    public void auctionBid(View view) {
        ActivitySwitcher.toAuctionBid(this);
    }
}
