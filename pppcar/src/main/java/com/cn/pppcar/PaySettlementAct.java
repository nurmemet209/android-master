package com.cn.pppcar;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.ReserveGoodsDetailResBean;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.widget.SelectableRelaytiveLayoutItem;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单结算
 * Created by nurmemet on 2016/4/4.
 */
public class PaySettlementAct extends BaseAct {
    /**
     * 普通订单
     */
    final static int ORDER_TYPE_COMMON = 1;
    /**
     * 预订单
     */
    final static int ORDER_TYPE_PREORDER = 2;
    @Bind(R.id.select_address)
    LinearLayout selectAddress;
//    @Bind(R.id.company_name)
//    TextView companyName;
//    @Bind(R.id.invoice_detail)
//    TextView invoiceDetail;
//    @Bind(R.id.select_invoice_)
//    LinearLayout selectInvoice;
//    @Bind(R.id.product_money_amount)
//    TextView productMoneyAmount;
//    @Bind(R.id.stage_1_price)
//    TextView stage1Price;
//    @Bind(R.id.stage_2_price)
//    TextView stage2Price;
//    @Bind(R.id.buy_clause)
//    SelectableRelaytiveLayoutItem buyClause;
//    @Bind(R.id.total_money)
//    TextView totalMoney;
//    @Bind(R.id.submit_order)
//    Button submitOrder;

    private long proId;
    private ReserveGoodsDetailResBean reserveDetailResBean;

    @Bind(R.id.receiver)
    protected TextView mReceiver;

    @Bind(R.id.receive_address)
    protected TextView mReceiveAddress;

    @Bind(R.id.phone_num)
    protected TextView mPhoneNum;

    @Bind(R.id.title_img)
    protected SimpleDraweeView mIitleImg;

    @Bind(R.id.title)
    protected TextView mTitle;

    @Bind(R.id.price)
    protected TextView mPrice;

    @Bind(R.id.product_num)
    protected TextView mProductNum;
    /**
     * 商品总额
     */
    @Bind(R.id.product_money_amount)
    protected TextView mOrderAmount;

    @Bind(R.id.reight)
    protected TextView mReight;
    /**
     * 总额*（运费+商品总额）
     */
    @Bind(R.id.total_money)
    protected TextView mTotalMoney;

    @Bind(R.id.order_product_price)
    protected LinearLayout preOrderContainer;

    /**
     * 订单类型
     */
    private int orderType;
    /**
     * 产品数量
     */
    private int productNum;
    /**
     * 订货规则id
     */
    private long ruleId;

    private int invoiceType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pay_settlement);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getArguments();
        loadData();
    }

    private void getArguments() {
        proId = getIntent().getLongExtra("proId", -1);
        productNum = getIntent().getIntExtra("number", -1);
        ruleId = getIntent().getLongExtra("ruleId", -1);
        orderType = getIntent().getIntExtra("orderType", -1);
    }


    private void loadData() {

        apiHandler.getPaySettlementPage(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (NetUtil.isSucced(response)) {
                    reserveDetailResBean = apiHandler.toObject(NetUtil.getData(response), ReserveGoodsDetailResBean.class);
                    if (reserveDetailResBean != null) {
                        bindData();
                    }
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, String.valueOf(proId), String.valueOf(productNum
        ), String.valueOf(ruleId));
    }

    private void bindData() {

        mReceiver.setText(reserveDetailResBean.getConsignee().getConsignee());
        mPhoneNum.setText(reserveDetailResBean.getConsignee().getMobileNumber());
        mReceiveAddress.setText(reserveDetailResBean.getConsignee().getAddress());

        mTitle.setText(reserveDetailResBean.getBsProduct().getName());
        mIitleImg.setImageURI(Uri.parse(reserveDetailResBean.getBsProduct().getImgs()));
        mProductNum.setText(reserveDetailResBean.getNumber() + "");
        mPrice.setText(String.valueOf(reserveDetailResBean.getTotalPrice()));

        TextView stage1 = (TextView) findViewById(R.id.stage_1_price);
        TextView stage2 = (TextView) findViewById(R.id.stage_2_price);


        if (reserveDetailResBean.getLastPrice() != 0) {
            stage1.setText("阶段1 预付款        小计：￥" + reserveDetailResBean.getFirstPriceStr());
            stage2.setText("阶段2 尾款   优惠：" + reserveDetailResBean.getDiscountPrice() + "   小计：￥" + reserveDetailResBean.getLastPriceStr());
        } else {
            stage1.setText("阶段1 预付款     优惠：" + reserveDetailResBean.getDiscountPrice() + "    小计：￥" + reserveDetailResBean.getFirstPriceStr());
            stage2.setVisibility(View.GONE);
        }

        mTotalMoney.setText("实付款：￥" + reserveDetailResBean.getTotalPriceStr());

    }


    @OnClick(R.id.submit_order)
    public void submitOrder(View view) {
//        apiHandler.submitPreOrder(new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//
//            }
//        });
        ActivitySwitcher.toOrderSubmitSuccedAct(this, -1);
    }


    @OnClick(R.id.select_invoice_)
    public void selectInvoiceType(View view) {
        ActivitySwitcher.toInvoiceInfoAct(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setInvoiceType(EventBusEv ev) {
        if (ev != null && "setInvoiceType_".equals(ev.getEvent())) {
            invoiceType = (int) ev.getData();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }


    @OnClick(R.id.select_address)
    public void onSelectAddress() {
        ActivitySwitcher.toReceiveAddressListAct(this);
    }
}
