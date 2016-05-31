package com.cn.fragment;

import android.graphics.ImageFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.ResInvoiceInfo;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.InvoiceTypeSelectAct;
import com.cn.pppcar.R;
import com.cn.util.MyLogger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 普通发票
 * Created by nurmemet on 2016/4/24.
 */
public class InvoiceCommonFrag extends BaseFrag {

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

    ResInvoiceInfo selctedInvoiceInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        EventBus.getDefault().register(this);
        getIntentData();
        return mainView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_invoice_common;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);

    }

    private void getIntentData() {
        InvoiceTypeSelectAct.InvoiceType invoiceType = (InvoiceTypeSelectAct.InvoiceType) getActivity().getIntent().getSerializableExtra("invoiceType");
        if (invoiceType.type == InvoiceTypeSelectAct.INVOICE_COMMON) {
            long id = invoiceType.id;
            loadData(id);
        }
    }

    private void loadData(long id) {

        Map<String, String> param = new HashMap<>();
        param.put("id", String.valueOf(id));
        apiHandler.getInvoiceCommon(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    selctedInvoiceInfo = apiHandler.toObject(NetUtil.getData(response), ResInvoiceInfo.class);
                    if (selctedInvoiceInfo != null) {
                        bindData();
                    }
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, param);

    }


    @OnClick({R.id.select_invoice, R.id.sure_btn})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.select_invoice:
                ActivitySwitcher.toInvoiceAct(getActivity(), selctedInvoiceInfo);
                break;
            case R.id.sure_btn:

                if (validate()) {
                    Map<String, String> param = new HashMap<>();
                    if (selctedInvoiceInfo != null) {
                        param.put("id", String.valueOf(selctedInvoiceInfo.getId()));
                    }
                    param.put("invoiceTitle", mInvoiceHeader.getText().toString());
                    param.put("takerName", mInvoiceReceiverName.getText().toString());
                    param.put("takerPhone", mInvoiceReceiverAddr.getText().toString());
                    param.put("takerAddress", mInvoiceReceiverPhone.getText().toString());
                    showProgressDlg();
                    apiHandler.addModifyInvoiceCommon(new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dismissProgressDlg();
                            //showToast(NetUtil.getMessage(response));
                            if (NetUtil.isSucced(response)) {
                                InvoiceTypeSelectAct act = (InvoiceTypeSelectAct) getActivity();
                                act.selectInvoiceCommon(selctedInvoiceInfo.getId());
                            }


                        }
                    }, param, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dismissProgressDlg();
                            MyLogger.showError(error.getMessage());
                        }
                    });
                }


                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setSelectInvoice(EventBusEv ev) {
        if (EventBusEv.is(ev, "setSelectInvoice")) {
            selctedInvoiceInfo = (ResInvoiceInfo) ev.getData();
            bindData();
        }
    }

    private void bindData() {
        if (selctedInvoiceInfo == null) {
            return;
        }
        mInvoiceHeader.setText(selctedInvoiceInfo.getInvoiceTitle());
        mInvoiceReceiverName.setText(selctedInvoiceInfo.getTakerName());
        mInvoiceReceiverAddr.setText(selctedInvoiceInfo.getTakerAddress());
        mInvoiceReceiverPhone.setText(selctedInvoiceInfo.getTakerPhone());
    }


    private boolean validate() {

        if ("".equals(mInvoiceHeader.getText().toString())) {
            showToast("发票抬头不能为空");
            return false;
        }
        if ("".equals(mInvoiceReceiverName.getText().toString())) {
            showToast("收票人不能为空");
            return false;
        }
        if ("".equals(mInvoiceReceiverAddr.getText().toString())) {
            showToast("收票人地址不能为空");
            return false;
        }
        if ("".equals(mInvoiceReceiverPhone.getText().toString())) {
            showToast("收票人手机号不能为空");
            return false;
        }
        if ("".equals(mInvoiceHeader.getText().toString())) {
            showToast("发票抬头不能为空");
            return false;
        }
        return true;
    }

}
