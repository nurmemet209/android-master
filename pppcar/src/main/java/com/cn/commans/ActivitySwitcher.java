package com.cn.commans;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cn.pppcar.R;
import com.cn.pppcar.SearchAct;

/**
 * Created by nurmemet on 2016/4/9.
 */
public class ActivitySwitcher {
    static  private   int actStartAnimInResId=R.anim.activity_exchange_right_in;
    static private   int actStartAnimOutResId=R.anim.activity_exchange_left_out;

    static public void toSearchAct(Activity activity){
        Intent intent=new Intent(activity, SearchAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId,actStartAnimOutResId);
    }

}
