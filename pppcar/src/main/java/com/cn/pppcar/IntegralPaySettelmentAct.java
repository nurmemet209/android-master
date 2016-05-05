package com.cn.pppcar;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.cn.commans.ActivitySwitcher;
import com.cn.fragment.BaseFrag;
import com.facebook.drawee.view.SimpleDraweeView;

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_integral_pay_settlement);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        titleImg.setImageURI(Uri.parse("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
    }



    @OnClick(R.id.submit_order)
    public void submitOrder(View view) {
        ActivitySwitcher.toOrderSubmitSuccedAct(this);
    }

    public void selectAddress(View view){
        ActivitySwitcher.toReceiveAddressListAct(this);
    }
}
