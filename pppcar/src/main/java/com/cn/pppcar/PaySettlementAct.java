package com.cn.pppcar;

import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.CartBean;
import com.cn.entity.CartProduct;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Bind(R.id.remark)
    EditText remark;


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


    @Bind(R.id.invoice_type)
    TextView invoiceTypeText;
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
     * 发票类型,如果
     */
    private InvoiceTypeSelectAct.InvoiceType invoiceType = new InvoiceTypeSelectAct.InvoiceType(InvoiceTypeSelectAct.INVOICE_NO);
    /**
     * 选中的收获地址
     */
    private Consignee mConsignee;
    /**
     * 订单抵扣部分
     */
    @Bind(R.id.integral_part)
    protected RelativeLayout mIntegralPart;
    @Bind(R.id.integral_num_edite)
    EditText integralInput;

    @Bind(R.id.available_integral)
    TextView availableIntegral;

    @Bind(R.id.integral_price)
    TextView deductedIntegral;
    @Bind(R.id.buy_clause)
    protected SelectableRelaytiveLayoutItem preOrderClause;

    @Bind(R.id.product_detail_container)
    protected LinearLayout productDetailContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pay_settlement);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        getIntentData();
        loadData();

    }

    private void getIntentData() {

        proId = getIntent().getLongExtra("proId", -1);
        productNum = getIntent().getIntExtra("number", -1);
        ruleId = getIntent().getLongExtra("ruleId", -1);
        orderType = getIntent().getIntExtra("orderType", -1);


    }


    private void loadData() {
        Map<String, String> param = new HashMap<>();
        if (orderType == ORDER_TYPE_PREORDER) {
            param.put("productId", String.valueOf(proId));
            param.put("number", String.valueOf(productNum));
            param.put("ruleId", String.valueOf(ruleId));
        }
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
        }, param, orderType, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLogger.showError(error.getMessage());
                dataLoaded(false);
            }
        });
    }

    private void setPreOrderProductDetail() {

        View view = LayoutInflater.from(this).inflate(R.layout.item_act_pay_settelment, null);
        SimpleDraweeView mIitleImg = (SimpleDraweeView) view.findViewById(R.id.title_img);
        TextView mTitle = (TextView) view.findViewById(R.id.title);
        TextView mPrice = (TextView) view.findViewById(R.id.price);
        TextView mProductNumber = (TextView) view.findViewById(R.id.product_num);

        mTitle.setText(reserveDetailResBean.getBsProduct().getName());
        mIitleImg.setImageURI(Uri.parse(reserveDetailResBean.getBsProduct().getImgs()));
        mProductNumber.setText("X" + reserveDetailResBean.getNumber());
        mPrice.setText(String.valueOf(reserveDetailResBean.getTotalPrice()));

        productDetailContainer.addView(view);
    }

    private void setCommonOrderProduct() {
        List<CartBean> list = reserveDetailResBean.getCartResBean().getContent();
        for (int i = 0; i < list.size(); i++) {
            CartProduct product = list.get(i).getBsProduct();

            View view = LayoutInflater.from(this).inflate(R.layout.item_act_pay_settelment, null);
            SimpleDraweeView mIitleImg = (SimpleDraweeView) view.findViewById(R.id.title_img);
            TextView mTitle = (TextView) view.findViewById(R.id.title);
            TextView mPrice = (TextView) view.findViewById(R.id.price);
            TextView mProductNumber = (TextView) view.findViewById(R.id.product_num);

            mTitle.setText(product.getName());
            mIitleImg.setImageURI(Uri.parse(product.getImgs()));
            mProductNumber.setText("X" + list.get(i).getNumber());
            mPrice.setText(String.valueOf(list.get(i).getTotalDiscountPrice()));

            productDetailContainer.addView(view);
        }
    }

    private void bindData() {

        setReceiver(reserveDetailResBean.getConsignee());

        if (orderType == ORDER_TYPE_PREORDER) {
            setPreOrderProductDetail();
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
            mOrderAmount.setText("￥" + reserveDetailResBean.getTotalPriceStr());
            mTotalMoney.setText("实付款：￥" + reserveDetailResBean.getDiscountPrice());
        } else if (orderType == ORDER_TYPE_COMMON) {
            setCommonOrderProduct();

            //隐藏预付款模块而
            preOrderContainer.setVisibility(View.GONE);
            //预付款不退款声明
            preOrderClause.setVisibility(View.GONE);

            integralInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String integralStr = s.toString();
                    int integral = Integer.valueOf(integralStr);
                    if (integral > reserveDetailResBean.getNormalIntegral()) {
                        integralInput.setText(String.valueOf(reserveDetailResBean.getNormalIntegral()));
                        mTotalMoney.setText("实付款：￥" + (reserveDetailResBean.getCartResBean().getTotalAllDiscountPrice() - reserveDetailResBean.getNormalIntegral()));
                    } else if (integral < 0) {
                        integralInput.setText("0");
                    } else {
                        mTotalMoney.setText("实付款：￥" + (reserveDetailResBean.getCartResBean().getTotalAllDiscountPrice()));
                    }
                    mTotalMoney.setText("实付款：￥" + (reserveDetailResBean.getCartResBean().getTotalAllDiscountPrice() - integral));
                }
            });
            availableIntegral.setText("可用积分" + reserveDetailResBean.getNormalIntegral() + "，使用");
            mOrderAmount.setText("￥" + reserveDetailResBean.getCartResBean().getTotalRetailPrice());
            mTotalMoney.setText("实付款：￥" + reserveDetailResBean.getCartResBean().getTotalAllDiscountPrice());
        }


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
        Map<String, String> param = new HashMap<>();
        ;
        if (orderType == ORDER_TYPE_PREORDER) {

                param.put("ruleId", String.valueOf(ruleId));
                param.put("invoiceType", getInvoiceWay());
                param.put("number", String.valueOf(productNum));
                param.put("consigneeId", String.valueOf(mConsignee.getId()));
                param.put("productId", String.valueOf(proId));
                param.put("remark", remark.getText().toString());


        }else if (orderType==ORDER_TYPE_COMMON){
            param.put("integral", integralInput.getText().toString());
            param.put("addressId ", String.valueOf(mConsignee.getId()));
            param.put("invoice", getInvoiceWay());
            param.put("consigneeId", String.valueOf(mConsignee.getId()));
            param.put("activityId ", "");
            param.put("remark", remark.getText().toString());
        }
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
                                  }, orderType, param,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dismissProgressDlg();
                        MyLogger.showError(error.getMessage());
                    }
                });

    }

    private String getInvoiceWay() {
        if (invoiceType.type == InvoiceTypeSelectAct.INVOICE_COMMON) {
            return "common";

        } else if (invoiceType.type == InvoiceTypeSelectAct.INVOICE_NO) {
            return "no";
        } else if (invoiceType.type == InvoiceTypeSelectAct.INVOICE_ADD_TAX) {
            return "vat";
        }
        return "";
    }


    @OnClick(R.id.select_invoice_)
    public void selectInvoiceType(View view) {
        ActivitySwitcher.toInvoiceTypeSelectAct(this, invoiceType);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setInvoiceType(EventBusEv ev) {
        if (EventBusEv.is(ev, "setInvoiceType_")) {
            invoiceType = (InvoiceTypeSelectAct.InvoiceType) ev.getData();
            if (invoiceType.type == InvoiceTypeSelectAct.INVOICE_COMMON) {
                invoiceTypeText.setText("普通发票");

            } else if (invoiceType.type == InvoiceTypeSelectAct.INVOICE_NO) {
                invoiceTypeText.setText("不开发票");
            } else if (invoiceType.type == InvoiceTypeSelectAct.INVOICE_ADD_TAX) {
                invoiceTypeText.setText("增值税发票");
            }
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
