package com.cn.pppcar.widget;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/5/9.
 */
public class StagePayDlg extends Dialog {


    private Button mCancelButton;


    public StagePayDlg(Context context) {
        super(context, R.style.pay_stage_dialog_style);
        setContentView(R.layout.dlg_stage_pay);
        getWindow().setLayout(WindowManager.LayoutParams.FILL_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        getWindow().setGravity(Gravity.BOTTOM);
        getWindow().setWindowAnimations(R.style.window_anim);
        mCancelButton=(Button)findViewById(com.cn.customlibrary.R.id.cancel_btn);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
