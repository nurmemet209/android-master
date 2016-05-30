package com.cn.pppcar;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.commans.SpanHelper;
import com.cn.entity.Item;
import com.cn.net.ApiHandler;
import com.cn.pppcar.widget.ProgressDlg;
import com.cn.util.MyLogger;
import com.cn.util.StringBuilderEx;
import com.cn.util.UIHelper;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/3/27.
 */
public abstract class BaseAct extends FragmentActivity implements Response.ErrorListener {

    protected int actFinishAnimInResId = R.anim.activity_exchange_left_in;
    protected int actFinishAnimOutResId = R.anim.activity_exchange_right_out;
    protected StringBuilderEx builderEx;
    protected SpanHelper spanHelper;
    ApiHandler apiHandler;
    ProgressDlg mProgressDlg;
    protected boolean isFirstShow = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spanHelper = new SpanHelper(this);
        apiHandler = ApiHandler.getInstance(this);
        builderEx = new StringBuilderEx();
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(actFinishAnimInResId, actFinishAnimOutResId);
    }

    public void OnBack(View view) {
        finish();
    }

    protected ArrayList<Item> getList() {
        ArrayList<Item> imageUrlList = new ArrayList<>();
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));
        imageUrlList
                .add(getItem("http://g.hiphotos.baidu.com/image/pic/item/6159252dd42a2834da6660c459b5c9ea14cebf39.jpg"));
        imageUrlList
                .add(getItem("http://d.hiphotos.baidu.com/image/pic/item/adaf2edda3cc7cd976427f6c3901213fb80e911c.jpg"));
        imageUrlList
                .add(getItem("http://g.hiphotos.baidu.com/image/pic/item/b3119313b07eca80131de3e6932397dda1448393.jpg"));
        imageUrlList
                .add(getItem("http://b.hiphotos.baidu.com/image/pic/item/d01373f082025aaf95bdf7e4f8edab64034f1a15.jpg"));

        return imageUrlList;
    }

    protected Item getItem(String str) {
        Item item = new Item();
        item.setImg(str);
        item.setName("D Forged 轮毂 S11系列 规格:18*8.5J  PCD:5*120 表面处理:中银拉丝（宝马3 宝马4 君威 迈锐宝）ET值:35 中心孔:72.5");
        item.setPrice(123);
        item.setCollectNum(230565);
        return item;
    }


    protected void showToast(String msg) {
        UIHelper.showToast(this, msg, Toast.LENGTH_LONG);
    }


    protected void showProgressDlg() {
        if (mProgressDlg == null) {
            mProgressDlg = new ProgressDlg();
        }
        mProgressDlg.show(getSupportFragmentManager(), "progressDlg_");
    }

    ;

    protected void dismissProgressDlg() {
        if (mProgressDlg != null) {
            mProgressDlg.dismiss();
        }
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        MyLogger.showError(error.getMessage());
        dismissProgressDlg();
    }


    protected void showWithAnimation(View view) {
        if (view != null) {
            view.setAlpha(0);
            view.animate().alpha(1).setDuration(1000).start();
        }

    }

    /**
     * 数据加载完成
     */
    protected void dataLoaded(boolean isSucceed) {
        if (isFirstShow) {
            View contentView = findViewById(R.id.content);
            if (isSucceed) {
                contentView.setVisibility(View.VISIBLE);
                findViewById(R.id.loading_view).setVisibility(View.GONE);
                showWithAnimation(contentView);

            } else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        TextView msg = (TextView) findViewById(R.id.message);
                        msg.setText("网络错误，请检查你的网络");
                        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);
                        progressBar.setVisibility(View.GONE);
                    }
                }, 5000);


            }


        }
    }


}
