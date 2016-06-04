package com.cn.pppcar;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/4/28.
 * 积分订单结算
 */
public class IntegralPaySettelmentAct extends BaseAct {

    @Bind(R.id.title_img)
    protected SimpleDraweeView titleImg;

    private long proId = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_integral_pay_settlement);
        ButterKnife.bind(this);
        getIntentData();
        loadData();
    }

    private void getIntentData() {
        proId = getIntent().getLongExtra("proId", -1);
    }

    private void loadData() {

        apiHandler.getIntegralProductPaySettlementPage(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            if (NetUtil.isSucced(response)){

            }
            }
        }, String.valueOf(proId), null);
        titleImg.setImageURI(Uri.parse("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
    }



    @OnClick(R.id.submit_order)
    public void submitOrder(View view) {
        ActivitySwitcher.toOrderSubmitSuccedAct(this, -1);
    }

    public void selectAddress(View view) {
        ActivitySwitcher.toReceiveAddressListAct(this, null);
    }
}
