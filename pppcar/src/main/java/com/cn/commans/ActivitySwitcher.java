package com.cn.commans;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cn.entity.Consignee;
import com.cn.entity.FavourableActivityBean;
import com.cn.entity.ResInvoiceInfo;
import com.cn.pppcar.AuctionAct;
import com.cn.pppcar.AuctionBid;
import com.cn.pppcar.AuctionDetailAct;
import com.cn.pppcar.BrandAct;
import com.cn.pppcar.CollectActTemp;
import com.cn.pppcar.IntegralMallAct;
import com.cn.pppcar.IntegralPaySettelmentAct;
import com.cn.pppcar.IntegralProductDetailAct;
import com.cn.pppcar.InvoiceAct;
import com.cn.pppcar.InvoiceCommonEditAct;
import com.cn.pppcar.InvoiceTypeSelectAct;
import com.cn.pppcar.LoginAct;
import com.cn.pppcar.MainPageAct;
import com.cn.pppcar.MyOrderAct;
import com.cn.pppcar.OrderSubmitSuccedAct;
import com.cn.pppcar.PayCenterAct;
import com.cn.pppcar.PaySettlementAct;
import com.cn.pppcar.ProductDetailAct;
import com.cn.pppcar.R;
import com.cn.pppcar.ReceiveAddressEditAct;
import com.cn.pppcar.ReceiveAddressListAct;
import com.cn.pppcar.SearchAct;
import com.cn.pppcar.SelectPreferentialAct;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/9.
 */
public class ActivitySwitcher {
    static private int actStartAnimInResId = R.anim.activity_exchange_right_in;
    static private int actStartAnimOutResId = R.anim.activity_exchange_left_out;

    static public void toSearchAct(Activity activity) {
        Intent intent = new Intent(activity, SearchAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    /**
     * 我的订单
     *
     * @param activity
     */
    static public void toMyOrderAct(Activity activity) {
        Intent intent = new Intent(activity, MyOrderAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toAuctionAct(Activity activity) {
        Intent intent = new Intent(activity, AuctionAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toIntegralProductDetailAct(Activity activity,long proId) {
        Intent intent = new Intent(activity, IntegralProductDetailAct.class);
        intent.putExtra("proId",proId);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toIntegralPaySettelmentAct(Activity activity,long proId) {
        Intent intent = new Intent(activity, IntegralPaySettelmentAct.class);
        intent.putExtra("proId",proId);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    /**
     * @param activity
     * @param id       订单id
     */
    static public void toOrderSubmitSuccedAct(Activity activity, long id) {
        Intent intent = new Intent(activity, OrderSubmitSuccedAct.class);
        intent.putExtra("orderId", id);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toReceiveAddressListAct(Activity activity, Consignee mConsignee) {
        Intent intent = new Intent(activity, ReceiveAddressListAct.class);
        intent.putExtra("Consignee", mConsignee);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toReceiveAddressEditAct(Activity activity, Consignee consignee) {
        Intent intent = new Intent(activity, ReceiveAddressEditAct.class);
        intent.putExtra("Consignee", consignee);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toAuctionDetailAct(Activity activity) {
        Intent intent = new Intent(activity, AuctionDetailAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toBrandCenterAct(Activity activity) {
        Intent intent = new Intent(activity, BrandAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toIntegralMallAct(Activity activity) {
        Intent intent = new Intent(activity, IntegralMallAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toProductDetailAct(Activity activity, long id) {
        Intent intent = new Intent(activity, ProductDetailAct.class);
        Bundle bd = new Bundle();
        bd.putLong("proId", id);
        intent.putExtras(bd);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    /**
     * 我要出家
     *
     * @param activity
     */
    static public void toAuctionBid(Activity activity) {
        Intent intent = new Intent(activity, AuctionBid.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toLoginAct(Activity activity) {
        Intent intent = new Intent(activity, LoginAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toCollectTemp(Activity activity) {
        Intent intent = new Intent(activity, CollectActTemp.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toPaySettlementAct(Activity activity, long proId, int num, long ruleId, int orderType) {
        Intent intent = new Intent(activity, PaySettlementAct.class);
        intent.putExtra("proId", proId);
        intent.putExtra("number", num);
        intent.putExtra("ruleId", ruleId);
        intent.putExtra("orderType", orderType);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toPaySettlementAct(Activity activity, int orderType) {
        Intent intent = new Intent(activity, PaySettlementAct.class);
        intent.putExtra("orderType", orderType);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toPayCenterAct(Activity activity) {
        Intent intent = new Intent(activity, PayCenterAct.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toInvoiceTypeSelectAct(Activity activity, InvoiceTypeSelectAct.InvoiceType invoiceType) {
        Intent intent = new Intent(activity, InvoiceTypeSelectAct.class);
        intent.putExtra("invoiceType", invoiceType);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toInvoiceAct(Activity activity, ResInvoiceInfo resInvoiceInfo) {
        Intent intent = new Intent(activity, InvoiceAct.class);
        if (resInvoiceInfo != null) {
            intent.putExtra("invoice_common", resInvoiceInfo);
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    static public void toInvoiceCommonEditAct(Activity activity, ResInvoiceInfo resInvoiceInfo) {
        Intent intent = new Intent(activity, InvoiceCommonEditAct.class);
        if (resInvoiceInfo != null) {
            intent.putExtra("invoice_common", resInvoiceInfo);
        }
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    public static void toSelectPreferentialAct(Activity activity, ArrayList<FavourableActivityBean> list, int selectedPos) {
        Intent intent = new Intent(activity, SelectPreferentialAct.class);
        intent.putParcelableArrayListExtra("list", list);
        intent.putExtra("selected", selectedPos);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }

    public static void toMainPageAct(Activity activity, int position) {
        Intent intent = new Intent(activity, MainPageAct.class);
        intent.putExtra("position", position);
        activity.startActivity(intent);
        activity.overridePendingTransition(actStartAnimInResId, actStartAnimOutResId);
    }


}
