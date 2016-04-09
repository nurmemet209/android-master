package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by nurmemet on 2016/4/7.
 */
public class AddressListAct extends Activity {

    private int selectedAddressId;
    private int selectedImgId=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_receive_address_list);
    }


    public void selectAddress(View view){
        if (selectedImgId!=-1){
            View img=findViewById(selectedImgId);
            img.setSelected(false);
        }
        View selected=view.findViewWithTag("selector");
        selected.setSelected(true);

    }
}
