package com.cn.pppcar;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.Consignee;
import com.cn.entity.ReserveGoodsDetailResBean;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.widget.SelectableRelaytiveLayoutItem;
import com.cn.util.MyLogger;
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
    public final static int ORDER_TYPE_COMMON = 1;
    /**
     * 预订单
     */
    public final static int ORDER_TYPE_PREORDER = 2;
    @Bind(R.id.select_address)
    LinearLayout selectAddress;


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
    protected TextView mProductNumber;
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

    @Bind(R.id.pre_order_product_price)
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
    /**
     * 发票类型
     */
    private int invoiceType;
    /**
     * 选中的收获地址
     */
    private Consignee mConsignee;
    /**
     * 订单抵扣部分
     */
    @Bind(R.id.integral_part)
    protected RelativeLayout mIntegralPart;

    @Bind(R.id.buy_clause)
    protected SelectableRelaytiveLayoutItem preOrderClause;


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
                    dataLoaded(true);
                } else {
                    showToast(NetUtil.getMessage(response));
                    dataLoaded(false);
                }

            }
        }, String.valueOf(proId), String.valueOf(productNum
        ), String.valueOf(ruleId), new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataLoaded(false);
            }
        });
    }

    private void bindData() {

        setReceiver(reserveDetailResBean.getConsignee());
        mTitle.setText(reserveDetailResBean.getBsProduct().getName());
        mIitleImg.setImageURI(Uri.parse(reserveDetailResBean.getBsProduct().getImgs()));
        mProductNumber.setText(reserveDetailResBean.getNumber() + "");
        mPrice.setText(String.valueOf(reserveDetailResBean.getTotalPrice()));

        if (orderType == ORDER_TYPE_PREORDER) {
            //隐藏积分抵扣模块
            mIntegralPart.setVisibility(View.GONE);
            TextView stage1 = (TextView) findViewById(R.id.stage_1_price);
            TextView stage2 = (TextView) findViewById(R.id.stage_2_price);
            if (reserveDetailResBean.getLastPrice() != 0) {
                stage1.setText("阶段1 预付款        小计：￥" + reserveDetailResBean.getFirstPriceStr());
                stage2.setText("阶段2 尾款   优惠：" + reserveDetailResBean.getDiscountPrice() + "   小计：￥" + reserveDetailResBean.getLastPriceStr());
            } else {
                stage1.setText("阶段1 预付款     优惠：" + reserveDetailResBean.getDiscountPrice() + "    小计：￥" + reserveDetailResBean.getFirstPriceStr());
                stage2.setVisibility(View.GONE);
            }
            TextView clause = (TextView) findViewById(R.id.pre_order_cluase_content);
            clause.setText(spanHelper.toRed("我已同意预付款不退款等", "相关规则"));
        } else if (orderType == ORDER_TYPE_COMMON) {
            //隐藏预付款模块而
            preOrderContainer.setVisibility(View.GONE);
            //预付款不退款声明
            preOrderClause.setVisibility(View.GONE);

        }
        mTotalMoney.setText("实付款：￥" + reserveDetailResBean.getTotalPriceStr());


    }

    private void setReceiver(Consignee consignee) {
        //可能没有收获地址
        if (consignee != null) {
            mReceiver.setText(consignee.getConsignee());
            mPhoneNum.setText(consignee.getMobileNumber());
            mReceiveAddress.setText(consignee.getAddress());
            mConsignee = consignee;
        }

    }


    @OnClick(R.id.submit_order)
    public void submitOrder(View view) {
        if (!validate()) {
            return;
        }
        showProgressDlg();
        apiHandler.submitPreOrder(new Response.Listener<JSONObject>() {
                                      @Override
                                      public void onResponse(JSONObject response) {
                                          if (NetUtil.isSucced(response)) {
                                              ActivitySwitcher.toOrderSubmitSuccedAct(PaySettlementAct.this, -1);
                                          } else {
                                              showToast(NetUtil.getMessage(response));
                                          }
                                          dismissProgressDlg();

                                      }
                                  }, orderType, "no", String.valueOf(ruleId), String.valueOf(productNum), String.valueOf(mConsignee.getId()), String.valueOf(proId), "",
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dismissProgressDlg();
                        MyLogger.showError(error.getMessage());
                    }
                });

    }


    @OnClick(R.id.select_invoice_)
    public void selectInvoiceType(View view) {
        ActivitySwitcher.toInvoiceTypeSelectAct(this);
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

    /**
     *
     */
    @OnClick(R.id.select_address)
    public void onSelectAddress() {
        EventBus.getDefault().postSticky(new EventBusEv("PaySettlementAct_setConsignee", mConsignee));
        ActivitySwitcher.toReceiveAddressListAct(this, mConsignee);
    }

    /**
     * @param ev
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setConsignee(EventBusEv ev) {
        if (EventBusEv.is(ev, "ReceiveAddressListAct_setConsignee")) {
            setReceiver((Consignee) ev.getData());
        }
    }


    private boolean validate() {
        if (mConsignee == null) {
            showToast("请选择收获地址");
            return false;
        }
        if (invoiceType == -1) {
            showToast("请选择发票类型");
            return false;
        }
        if (orderType == ORDER_TYPE_PREORDER) {
            boolean isSelcted = preOrderClause.isItemSelected();
            if (!isSelcted) {
                showToast("你应该同意预定相关规则");
                return false;
            }
        }
        return true;
    }


}
