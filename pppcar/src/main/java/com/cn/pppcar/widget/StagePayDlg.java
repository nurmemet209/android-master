package com.cn.pppcar.widget;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/5/9.
 */
public class StagePayDlg extends AppCompatDialog {


    private Button mCancelButton;


    public StagePayDlg(Context context) {
        super(context, R.style.dlg_pay_stage);

        setContentView(R.layout.dlg_stage_pay);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.BOTTOM);
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

    @Override
    public void show() {
        super.show();
    }
}
