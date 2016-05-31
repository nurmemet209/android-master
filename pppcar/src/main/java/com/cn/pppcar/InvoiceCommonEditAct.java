package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.commans.NetUtil;
import com.cn.entity.ResInvoiceInfo;
import com.cn.localutils.EventBusEv;
import com.cn.util.MyLogger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 5/30/2016.
 */
public class InvoiceCommonEditAct extends BaseAct {

    private final static int EDIT = 1;
    private final static int ADD = 2;
    private int type = -1;

    @Bind(R.id.invoice_header)
    EditText invoiceHeader;
    @Bind(R.id.invoice_receiver_name)
    EditText invoiceReceiverName;
    @Bind(R.id.invoice_receiver_addr)
    EditText invoiceReceiverAddr;
    @Bind(R.id.invoice_receiver_phone)
    EditText invoiceReceiverPhone;
    @Bind(R.id.sure_btn)
    Button sureBtn;
    private ResInvoiceInfo resInvoiceInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_invoice_common_edit);
        ButterKnife.bind(this);
        getIntentData();
        bindData();
    }

    private void getIntentData() {
        resInvoiceInfo = (ResInvoiceInfo) getIntent().getSerializableExtra("invoice_common");
        if (resInvoiceInfo == null) {
            type = ADD;
        } else {
            type = EDIT;
        }
    }

    private void bindData() {
        if (type == EDIT) {
            invoiceHeader.setText(resInvoiceInfo.getInvoiceTitle());
            invoiceReceiverName.setText(resInvoiceInfo.getTakerName());
            invoiceReceiverPhone.setText(resInvoiceInfo.getTakerPhone());
            invoiceReceiverAddr.setText(resInvoiceInfo.getTakerAddress());
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @OnClick(R.id.sure_btn)
    public void onClick() {


        if (!validate()) {
            return;
        }
        Map<String, String> param = new HashMap<>();
        if (resInvoiceInfo != null) {
            param.put("id", String.valueOf(resInvoiceInfo.getId()));
        }
        param.put("invoiceTitle", invoiceHeader.getText().toString());
        param.put("takerName", invoiceReceiverName.getText().toString());
        param.put("takerPhone", invoiceReceiverPhone.getText().toString());
        param.put("takerAddress", invoiceReceiverAddr.getText().toString());
        showProgressDlg();
        apiHandler.addModifyInvoiceCommon(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dismissProgressDlg();
                if (NetUtil.isSucced(response)) {
                    EventBus.getDefault().post(new EventBusEv("refreshInoiceList", null));
                    finish();
                }
                showToast(NetUtil.getMessage(response));
            }
        }, param, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLogger.showError(error.getMessage());
                dismissProgressDlg();
            }
        });
    }

    private boolean validate() {
        if ("".equals(invoiceHeader.getText().toString())) {
            showToast("发票抬头不能为空");
            return false;
        }
        if ("".equals(invoiceReceiverName.getText().toString())) {
            showToast("收票人不能为空");
            return false;
        }
        if ("".equals(invoiceReceiverAddr.getText().toString())) {
            showToast("收票地址不能为空");
            return false;
        }
        if ("".equals(invoiceReceiverPhone.getText().toString())) {
            showToast("发票人电话不能为空");
            return false;
        }
        return true;
    }
}
