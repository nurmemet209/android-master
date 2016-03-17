package com.cn.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cn.dialog.ConfirmDialog;
import com.cn.dialog.CustomAlertDialog;
import com.cn.dialog.PictureSelecctDialog;
import com.cn.customlibrary.R;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class TestAct extends Activity{
    Button mPhotoSelect;
    Button mConfirmDialog;
    Button mCustomAlertDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_test);
        mPhotoSelect=(Button)findViewById(R.id.photo_select);
        mPhotoSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PictureSelecctDialog dialog=new PictureSelecctDialog(TestAct.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (v.getTag()!=null){
                            int code=(Integer)v.getTag();
                            switch (code){
                                case PictureSelecctDialog.FROM_ALBUM:
                                    break;
                                case PictureSelecctDialog.TAKE_PICTURE:
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                });
                dialog.show();
            }
        });
        mConfirmDialog=(Button)findViewById(R.id.confirm_dialog);
        mConfirmDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmDialog dialog=new ConfirmDialog(TestAct.this, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                dialog.show();
            }
        });

        mCustomAlertDialog=(Button)findViewById(R.id.custom_alert_dialog);
        mCustomAlertDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomAlertDialog dialog=new CustomAlertDialog(TestAct.this);
                dialog.show();

            }
        });

    }
}
