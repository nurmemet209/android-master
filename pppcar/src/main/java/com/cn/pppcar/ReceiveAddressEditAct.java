package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/4/6.
 */
public class ReceiveAddressEditAct extends BaseAct {

    private boolean isDefaultAddress = false;
//    @Bind(R.id.city_select)
//    protected TextView selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_receive_address_edit);
        ButterKnife.bind(this);
    }


    public void selecteDetaultAddress(View view) {
        View img = view.findViewWithTag("selector");
        img.setSelected(!isDefaultAddress);
        isDefaultAddress=img.isSelected();

    }

//    @OnClick(R.id.city_select)
//    public void selectCity(View view){
//
//    }
}
