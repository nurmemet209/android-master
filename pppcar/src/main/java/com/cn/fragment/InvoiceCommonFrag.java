package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.cn.pppcar.R;
import com.cn.pppcar.widget.SelectableLinearLayoutItem;
import com.cn.pppcar.widget.SelectecableLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/24.
 */
public class InvoiceCommonFrag extends Fragment {

    @Bind(R.id.invoice_receiver_name)
    EditText invoiceReceiverName;
    @Bind(R.id.invoice_receiver_phone)
    EditText invoiceReceiverPhone;
    @Bind(R.id.order_addr_same)
    SelectableLinearLayoutItem orderAddrSame;
    @Bind(R.id.product_detail)
    SelectableLinearLayoutItem productDetail;
    private View mainView;

    @Bind(R.id.deliver_addr)
    protected EditText deliverAddr;
    @Bind(R.id.org_name)
    protected EditText orgName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.frag_invoice_common, null);
        ButterKnife.bind(this, mainView);
        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        init();
    }

    private void init() {

    }


//    @OnClick(R.id.order_addr_same)
//    public void SameAsOrderAddr(View view){
//        View img=view.findViewWithTag("selected");
//        img.setSelected(!sameAsOrderAddr);
//        sameAsOrderAddr=!sameAsOrderAddr;
//    }
//
//    @OnClick(R.id.product_detail)
//    public void OrderDetail(View view){
//        View img=view.findViewWithTag("selected");
//        img.setSelected(!productDetail);
//        productDetail=!productDetail;
//    }
}
