package com.cn.pppcar;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;

/**
 * Created by nurmemet on 2016/3/27.
 */
public abstract class BaseAct extends FragmentActivity {

    protected  int actFinishAnimInResId=R.anim.activity_exchange_left_in;
    protected  int actFinishAnimOutResId=R.anim.activity_exchange_right_out;




    public void OnBack(View view){
        finish();
    }
}
