package com.cn.util;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class UIHelper {
    private final static int SHOW_TOAST = 0;

    public static void showToast(Context ctx, String text, int duration) {
        Message msg = new Message();
        ToastObjectHolder objHolder = new ToastObjectHolder();
        objHolder.context = ctx;
        objHolder.text = text;
        objHolder.duration = duration;
        msg.obj = objHolder;
        handler.sendMessage(msg);

    }

    public static void showToast(Context ctx, int resId, int duration) {
        Message msg = new Message();
        ToastObjectHolder objHolder = new ToastObjectHolder();
        objHolder.context = ctx;
        objHolder.text = ctx.getString(resId);
        objHolder.duration = duration;
        msg.obj = objHolder;
        handler.sendMessage(msg);

    }

    private static Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SHOW_TOAST:
                    if (msg.obj != null) {
                        ToastObjectHolder objHolder = (ToastObjectHolder) msg.obj;
                        CustomToast.makeText(objHolder.context, objHolder.text,
                                objHolder.duration).show();
                    }
                    break;
            }
        }

        ;
    };

    private static class ToastObjectHolder {
        public Context context;
        public String text;
        public int duration;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    public static final int sp2px(Context context, float sp)
    {

        return (int) (sp * context.getResources().getDisplayMetrics().scaledDensity + 0.5f);

    }

}
