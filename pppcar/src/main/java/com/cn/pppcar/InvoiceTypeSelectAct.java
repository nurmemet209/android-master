package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.cn.localutils.EventBusEv;
import com.cn.pppcar.widget.SelectecableLinearLayout;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发票类型
 * Created by nurmemet on 2016/4/23.
 */
public class InvoiceTypeSelectAct extends BaseAct {
    public static int INVOICE_NO = 1;
    public static int INVOICE_COMMON = 2;
    public static int INVOICE_ADD_TAX = 3;

    private InvoiceType invoiceType;

    @Bind(R.id.selector_container)
    protected SelectecableLinearLayout selectorContainer;
    @Bind(R.id.view_flipper)
    protected ViewFlipper viewFlipper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_invoice_info);
        getIntentData();
        ButterKnife.bind(this);
        init();
    }

    private void getIntentData() {
        invoiceType = (InvoiceType) getIntent().getSerializableExtra("invoiceType");

    }

    private void init() {
        selectorContainer.setCanReverseSelect(false);
        selectorContainer.setSelectedPosition(invoiceType.type-1);
        viewFlipper.setDisplayedChild(invoiceType.type-1);
        selectorContainer.init();
        selectorContainer.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.isSelected()) {
                    switch (v.getId()) {
                        case R.id.value_add_tax_invoice:
                            viewFlipper.setDisplayedChild(2);
                            break;
                        case R.id.common_invoice:
                            viewFlipper.setDisplayedChild(1);
                            break;
                        case R.id.no_invoice:
                            viewFlipper.setDisplayedChild(0);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
    }

    @Override
    public void OnBack(View view) {
//        int invoiceType = selectorContainer.getSelectedPosition();
//        EventBus.getDefault().post(new EventBusEv("setInvoiceType_", invoiceType));
        super.OnBack(view);

    }

    public void selectAddTaxInvoice() {
        invoiceType.type = INVOICE_ADD_TAX;
        EventBus.getDefault().post(new EventBusEv("setInvoiceType_", invoiceType));
        finish();
    }

    public void selectInvoiceCommon(long id) {
        invoiceType.type = INVOICE_COMMON;
        invoiceType.id = id;
        EventBus.getDefault().post(new EventBusEv("setInvoiceType_", invoiceType));
        finish();
    }

    @OnClick(R.id.selecte_no_invoice)
    public void selectInoiceNo(View view) {
        invoiceType.type = INVOICE_NO;
        EventBus.getDefault().post(new EventBusEv("setInvoiceType_", invoiceType));
        finish();
    }


    public static class InvoiceType implements Serializable {
        public InvoiceType(int type) {
            this.type = type;
        }
        public int type;
        public long id;
    }

}
