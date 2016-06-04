package com.cn.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cn.customlibrary.R;

/**
 * Created by nurmemet on 2015/12/20.
 */
public class ConfirmDialog extends Dialog {

    final public static   int SURE=1;
    final public static   int CANCEL=2;

    private Button mSureButton;
    private Button mCancelButton;
    private TextView mTitleTv;
    private String mTitleStr;


    private float translationX=100;


    private View.OnClickListener onClickListener;

    public ConfirmDialog(Context context,View.OnClickListener clickListener) {
        super(context, R.style.confirm_dialog_style);
        this.onClickListener = clickListener;
        this.setContentView(R.layout.confirm_dialog);
        mSureButton = (Button) findViewById(R.id.sure_btn);
        mSureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(SURE);
                onClickListener.onClick(v);
                dismiss();
            }
        });
        mCancelButton = (Button) findViewById(R.id.cancel_btn);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(CANCEL);
                onClickListener.onClick(v);
                dismiss();
            }
        });
        mTitleTv=(TextView)findViewById(R.id.title);
        if (mTitleStr!=null){
            mTitleTv.setText(mTitleStr);
        }
    }

    public void setTitleText(String title){
        mTitleStr=title;
        if (mTitleTv!=null){
            mTitleTv.setText(mTitleStr);
        }
    }
    public float getTranslationX() {
        return translationX;
    }

    public void setTranslationX(float translationX) {
        this.translationX = translationX;
        getWindow().getDecorView().setTranslationX(translationX);
        System.out.println("动画值："+translationX);
    }

}
