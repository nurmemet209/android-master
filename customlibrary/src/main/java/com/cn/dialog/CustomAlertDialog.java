package com.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cn.customlibrary.R;

/**
 * Created by nurmemet on 2015/12/20.
 */
public class CustomAlertDialog extends Dialog {
    private String mTitleStr;
    private TextView mTitleTv;
    private Button mCancelButton;

    public void setAlertContent(String str){
        mTitleStr=str;
    }

    public CustomAlertDialog(Context context) {
        super(context, R.style.confirm_dialog_style);
        setContentView(R.layout.custom_alert_dialog);
        mCancelButton=(Button)findViewById(R.id.cancel_btn);
        mTitleTv=(TextView)findViewById(R.id.alert_content);
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
