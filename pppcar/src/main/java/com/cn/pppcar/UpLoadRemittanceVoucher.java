package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/23.
 */
public class UpLoadRemittanceVoucher extends BaseAct {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_upload_remittance_voucher);
        ButterKnife.bind(this);

    }
}
