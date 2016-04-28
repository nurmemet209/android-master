package com.cn.commans;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.cn.pppcar.AuctionAct;
import com.cn.pppcar.IntegralPaySettelmentAct;
import com.cn.pppcar.IntegralProductDetail;
import com.cn.pppcar.MyOrderAct;
import com.cn.pppcar.OrderSubmitSuccedAct;
import com.cn.pppcar.PaySettlement;
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
    static public void toMyOrderAct(Activity activity){
        Intent intent=new Intent(activity, MyOrderAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId,actStartAnimOutResId);
    }
    static public void toAuctionAct(Activity activity){
        Intent intent=new Intent(activity, AuctionAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId,actStartAnimOutResId);
    }

    static public void toIntegralProductDetailAct(Activity activity){
        Intent intent=new Intent(activity, IntegralProductDetail.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId,actStartAnimOutResId);
    }

    static public void toIntegralPaySettelmentAct(Activity activity){
        Intent intent=new Intent(activity, IntegralPaySettelmentAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId,actStartAnimOutResId);
    }

    static public void toOrderSubmitSuccedAct(Activity activity){
        Intent intent=new Intent(activity, OrderSubmitSuccedAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId,actStartAnimOutResId);
    }

}
