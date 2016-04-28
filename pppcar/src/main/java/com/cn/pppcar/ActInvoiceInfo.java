package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/23.
 */
public class ActInvoiceInfo extends BaseAct {

    TextView selectedInvoiceType;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_invoice_info);
        ButterKnife.bind(this);
    }

    public void InvoiceType(View view){
        if (selectedInvoiceType!=null){
            selectedInvoiceType.setSelected(false);
        }
        selectedInvoiceType=(TextView)view;
        selectedInvoiceType.setSelected(true);


    }


}
