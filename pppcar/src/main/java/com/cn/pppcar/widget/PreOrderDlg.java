package com.cn.pppcar.widget;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cn.pppcar.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/5/11.
 */
public class PreOrderDlg extends AppCompatDialog {

    private CheckableLayout container;
    private ImageButton mCancelButton;
    /**
     * 总价
     */
    @Bind(R.id.total_price)
    protected TextView totalPrice;
    /**
     * 本次应付
     */
    @Bind(R.id.shuld_pay)
    protected TextView shouldPay;
    /**
     * 预付款
     */
    @Bind(R.id.pre_pay)
    protected TextView prePay;
    /**
     * 优惠价
     */
    @Bind(R.id.preferential_price)
    protected TextView preferentialPrice;

    public PreOrderDlg(Context context) {
        super(context, R.style.dlg_pre_order);
        setContentView(R.layout.dlg_pre_order);
        ButterKnife.bind(this);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.BOTTOM);
        container = (CheckableLayout) findViewById(R.id.container);
        mCancelButton = (ImageButton) findViewById(com.cn.customlibrary.R.id.cancel_btn);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        bindData();
    }

    private void bindData() {

    }


}
