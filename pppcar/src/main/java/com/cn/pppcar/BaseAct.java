package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.cn.commans.SpanHelper;
import com.cn.util.StringBuilderEx;

/**
 * Created by nurmemet on 2016/3/27.
 */
public abstract class BaseAct extends FragmentActivity {

    protected  int actFinishAnimInResId=R.anim.activity_exchange_left_in;
    protected  int actFinishAnimOutResId=R.anim.activity_exchange_right_out;
    protected StringBuilderEx builderEx=new StringBuilderEx();
    protected SpanHelper spanHelper;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spanHelper=new SpanHelper(this);

    }

    public void OnBack(View view){
        finish();
    }
}
