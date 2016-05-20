package com.cn.pppcar.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.cn.commans.SpanHelper;
import com.cn.net.ApiHandler;
import com.cn.util.StringBuilderEx;
import com.cn.util.UIHelper;

/**
 * Created by nurmemet on 2016/5/16.
 */
public class BaseDialog extends AppCompatDialog {

    protected ApiHandler apiHandler;
    protected SpanHelper spanHelper;
    protected Handler mHandler;
    protected StringBuilderEx builder;

    public BaseDialog(Context context, int style) {
        super(context, style);
        apiHandler = ApiHandler.getInstance(context);
        mHandler = new Handler();
        builder = new StringBuilderEx();
        spanHelper=new SpanHelper(context);
    }


    protected void showToast(final String msg) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            UIHelper.showToast(getContext(), msg, Toast.LENGTH_LONG);

        } else {
            new android.os.Handler().post(new Runnable() {
                @Override
                public void run() {
                    UIHelper.showToast(getContext(), msg, Toast.LENGTH_LONG);
                }
            });
        }
    }

    protected void set2FullWidth(int gravity){
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(gravity);
    }
}
