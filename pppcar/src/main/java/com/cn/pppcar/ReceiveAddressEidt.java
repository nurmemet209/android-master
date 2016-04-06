package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by nurmemet on 2016/4/6.
 */
public class ReceiveAddressEidt extends Activity {
    private boolean isDefaultAddress = false;
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_receive_address_edit);
    }


    public void selecteDetaultAddress(View view) {
        View img = view.findViewWithTag("selector");

        img.setSelected(!isDefaultAddress);
        isDefaultAddress=img.isSelected();

    }
}
