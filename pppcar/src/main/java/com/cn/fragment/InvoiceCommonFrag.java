package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cn.commans.ActivitySwitcher;
import com.cn.entity.ResInvoiceInfo;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 普通发票
 * Created by nurmemet on 2016/4/24.
 */
public class InvoiceCommonFrag extends Fragment {

    @Bind(R.id.invoice_header)
    EditText mInvoiceHeader;
    @Bind(R.id.invoice_receiver_name)
    EditText mInvoiceReceiverName;
    @Bind(R.id.invoice_receiver_addr)
    EditText mInvoiceReceiverAddr;
    @Bind(R.id.invoice_receiver_phone)
    EditText mInvoiceReceiverPhone;
    @Bind(R.id.select_invoice)
    Button mSelectInvoice;
    @Bind(R.id.sure_btn)
    Button mSureBtn;
    private View mainView;

    ResInvoiceInfo selctedInvoiceInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.frag_invoice_common, null);
        ButterKnife.bind(this, mainView);
        EventBus.getDefault().register(this);
        return mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.select_invoice, R.id.sure_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_invoice:
                ActivitySwitcher.toInvoiceAct(getActivity(), selctedInvoiceInfo);
                break;
            case R.id.sure_btn:
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setSelectInvoice(EventBusEv ev) {
        if (EventBusEv.is(ev, "setSelectInvoice")) {
            selctedInvoiceInfo = (ResInvoiceInfo) ev.getData();
        }
    }

}
