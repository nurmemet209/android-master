package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.cn.localutils.EventBusEv;
import com.cn.pppcar.widget.SelectecableLinearLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 发票类型
 * Created by nurmemet on 2016/4/23.
 */
public class InvoiceTypeSelectAct extends BaseAct {

    @Bind(R.id.selector_container)
    protected SelectecableLinearLayout selectorContainer;
    @Bind(R.id.view_flipper)
    protected ViewFlipper viewFlipper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_invoice_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        selectorContainer.setCanReverseSelect(false);
        selectorContainer.setSelectedPosition(0);
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
        int invoiceType = selectorContainer.getSelectedPosition();
        EventBus.getDefault().post(new EventBusEv("setInvoiceType_", invoiceType));
        super.OnBack(view);

    }
}
