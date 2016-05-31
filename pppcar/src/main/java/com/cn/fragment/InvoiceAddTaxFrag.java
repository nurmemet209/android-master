package com.cn.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.commans.NetUtil;
import com.cn.entity.ResInvoiceInfo;
import com.cn.pppcar.InvoiceAct;
import com.cn.pppcar.InvoiceTypeSelectAct;
import com.cn.pppcar.R;
import com.cn.util.MyLogger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 增值税发票
 * Created by nurmemet on 2016/4/24.
 */
public class InvoiceAddTaxFrag extends BaseFrag {


    @Bind(R.id.orgination_name)
    EditText orginationName;
    /**
     * 纳税人识别码
     */
    @Bind(R.id.tax_payer)
    EditText taxPayer;
    @Bind(R.id.register_addr)
    EditText registerAddr;
    @Bind(R.id.register_phone)
    EditText registerPhone;
    @Bind(R.id.tax_bank)
    EditText taxBank;
    @Bind(R.id.tax_bank_num)
    EditText taxtBankNum;
    @Bind(R.id.receiver_name)
    EditText receiverName;
    @Bind(R.id.invoice_receiver_phone)
    EditText invoiceReceiverPhone;
    @Bind(R.id.receive_address)
    EditText receiveAddress;

    ResInvoiceInfo selctedInvoiceInfo;

    private int type = -1;

    static public Fragment getInstance() {
        InvoiceAddTaxFrag frag = new InvoiceAddTaxFrag();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        loadData();
        return mainView;
    }

    private void loadData() {
        apiHandler.getInvoiceAddTax(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    selctedInvoiceInfo = apiHandler.toObject(NetUtil.getData(response), ResInvoiceInfo.class);
                    if (selctedInvoiceInfo != null) {
                        bindData();
                    }

                }
            }
        });
    }

    private void bindData() {
        orginationName.setText(selctedInvoiceInfo.getCompanyName());
        taxPayer.setText(selctedInvoiceInfo.getCode());
        registerAddr.setText(selctedInvoiceInfo.getAddress());
        registerPhone.setText(selctedInvoiceInfo.getTel());
        taxBank.setText(selctedInvoiceInfo.getBank());
        taxtBankNum.setText(selctedInvoiceInfo.getBankAccount());
        receiverName.setText(selctedInvoiceInfo.getTakerName());
        invoiceReceiverPhone.setText(selctedInvoiceInfo.getTakerPhone());
        receiveAddress.setText(selctedInvoiceInfo.getTakerAddress());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_invoice_add_tax__company;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.submit)
    public void submite(View view) {
        if (!validate()) {
            return;
        }
        Map<String, String> param = new HashMap<>();
        if (selctedInvoiceInfo != null) {
            param.put("id", String.valueOf(selctedInvoiceInfo.getId()));
        }
        param.put("companyName", orginationName.getText().toString());
        param.put("code", taxPayer.getText().toString());
        param.put("address", registerAddr.getText().toString());
        param.put("tel", registerPhone.getText().toString());
        param.put("bank", taxBank.getText().toString());
        param.put("bankAccount", taxtBankNum.getText().toString());
        param.put("takerName", receiverName.getText().toString());
        param.put("takerPhone", invoiceReceiverPhone.getText().toString());
        param.put("takerAddress", receiveAddress.getText().toString());

        showProgressDlg();
        apiHandler.addModifyInvoiceAddTax(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    if (getActivity() instanceof InvoiceTypeSelectAct) {
                        InvoiceTypeSelectAct act = (InvoiceTypeSelectAct) getActivity();
                        act.selectAddTaxInvoice();
                    }
                }
                dismissProgressDlg();
            }
        }, param, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dismissProgressDlg();
                MyLogger.showError(error.getMessage());
            }
        });
    }

    private boolean validate() {
        if ("".equals(orginationName.getText().toString())) {
            showToast("单位名称不能为空");
            return false;
        }
        if ("".equals(taxPayer.getText().toString())) {
            showToast("纳税人识别码不能为空");
            return false;
        }
        if ("".equals(registerAddr.getText().toString())) {
            showToast("注册地址不能为空");
            return false;
        }
        if ("".equals(registerPhone.getText().toString())) {
            showToast("注册电话不能为空");
            return false;
        }
        if ("".equals(taxBank.getText().toString())) {
            showToast("开户银行不能为空");
            return false;
        }
        if ("".equals(taxtBankNum.getText().toString())) {
            showToast("收票人姓名不能为空");
            return false;
        }
        if ("".equals(receiverName.getText().toString())) {
            showToast("收票人手机号不能为空");
            return false;
        }
        if ("".equals(invoiceReceiverPhone.getText().toString())) {
            showToast("收票人地址不能为空");
            return false;
        }


        return true;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InvoiceAct) {
            type = 1;
        } else if (context instanceof InvoiceTypeSelectAct) {
            type = 2;
        }
    }
}
