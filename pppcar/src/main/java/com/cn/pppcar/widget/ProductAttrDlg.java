package com.cn.pppcar.widget;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.commans.SpanHelper;
import com.cn.entity.ProductAttrBean;
import com.cn.entity.ResProductApp;
import com.cn.entity.ReserveGoodsRuleResBean;
import com.cn.pppcar.PaySettlementAct;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/5/11.
 */
public class ProductAttrDlg extends BaseDialog {

    private Map<String, Map<String, ProductAttrBean>> map;
    @Bind(R.id.container)
    protected PropertyLayout properyContainer;
    @Bind(R.id.cancel_btn)
    protected ImageButton mCancelButton;
    @Bind(R.id.title_img)
    protected SimpleDraweeView titleImg;
    @Bind(R.id.title)
    protected TextView title;
    @Bind(R.id.put_into_cart)
    protected Button mActionBtn;
    @Bind(R.id.num_eidt)
    protected NumEditLayout numEdit;
    @Bind(R.id.retail_price)
    protected TextView retailPrice;
    /**
     * 预定规则
     */
    @Bind(R.id.checkable_layout)
    protected SelectableLayout preOrderRule;
    private ResProductApp productDetail;
    private ArrayList<String> keyList = new ArrayList<>();

    private Runnable refreshRun;
    private Context mContext;


    public ProductAttrDlg(Context context, ResProductApp productDetail, Runnable refreshRun) {
        super(context, R.style.dlg_product_attr_select);
        this.productDetail = productDetail;
        this.map = productDetail.getProductAttrs();
        this.refreshRun = refreshRun;
        this.mContext = context;
        setContentView(R.layout.dlg_product_attr);
        ButterKnife.bind(this);
        set2FullWidth(Gravity.BOTTOM);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        bindData();
    }

    private void setProductData() {
        titleImg.setImageURI(Uri.parse(productDetail.getImgs()));
        title.setText(productDetail.getName());
        SpanHelper spanHelper = new SpanHelper(getContext());
        retailPrice.setText(spanHelper.priceSpan(R.string.retail_price_, productDetail.getRetailPrice()));
    }

    private void bindData() {
        setProductData();
        setProductAttr();
        setPreOrder();

    }

    private void setProductAttr() {
        if (map == null || map.isEmpty()) {
            properyContainer.setVisibility(View.GONE);
            return;
        }
        properyContainer.init(productDetail.getId());
        int pd = getContext().getResources().getDimensionPixelSize(R.dimen.padding_normal);
        properyContainer.setPad(pd, pd);
        keyList.add("尺寸");
        keyList.add("宽度");
        keyList.add("ET值");
        keyList.add("颜色");
        keyList.add("圆心距");
        int index = 0;
        for (String key : keyList) {
            properyContainer.addItem(key, map.get(key), index);
            index++;
        }
        properyContainer.setItemClick(new PropertyLayout.ItemClick() {
                                          @Override
                                          public void onItemClick(final long id) {

                                              apiHandler.getProductDetail(new Response.Listener<JSONObject>() {
                                                  @Override
                                                  public void onResponse(JSONObject response) {
                                                      if (NetUtil.isSucced(response)) {
                                                          ResProductApp detail = apiHandler.toObject(NetUtil.getData(response), ResProductApp.class);
                                                          if (detail != null) {
                                                              productDetail = detail;
//                                                       productDetail.setId(detail.getId());
//                                                       productDetail.setImgs(detail.getImgs());
//                                                       productDetail.setName(detail.getName());
//                                                       String urls[] = productDetail.getImgs().split(",");
//                                                       titleImg.setImageURI(Uri.parse(urls[0]));
//                                                       title.setText(productDetail.getName());

                                                              if (refreshRun != null) {
                                                                  refreshRun.run();
                                                              }
                                                          }
                                                      } else {
                                                          showToast(NetUtil.getMessage(response));
                                                      }
                                                  }
                                              }, null, id);
                                          }
                                      }
        );
    }

    private void setPreOrder() {
        mActionBtn.setEnabled(true);
        preOrderRule.removeAllViews();
        if (productDetail.getIsFlagGoodsCycle()) {
            mActionBtn.setText("立即预定");
            properyContainer.setVisibility(View.VISIBLE);
            preOrderRule.setItems(productDetail.getReserveGoodsRuleResBeans(), new SelectableLayout.OnBindPropertyListener() {
                @Override
                public void OnBindProperty(TextView tv, ImageView img, int position) {
                    img.setImageResource(R.drawable.image_view_checkbox);
                    ViewGroup.LayoutParams params = img.getLayoutParams();
                    int size = getContext().getResources().getDimensionPixelSize(R.dimen.icon_width);
                    params.width = size;
                    params.height = size;
                    img.setLayoutParams(params);
                    img.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                }
            });
        } else {
            preOrderRule.setVisibility(View.GONE);
            mActionBtn.setText("加入购物车");
            properyContainer.setVisibility(View.GONE);
            if (!productDetail.hasStock()) {
                mActionBtn.setEnabled(false);
            }
        }
    }


    @OnClick(R.id.put_into_cart)
    public void putIntoCart(View view) {
        int num = numEdit.getNum();
        if (num == 0) {
            showToast("产品数量不能为零");
            return;
        }
        // has stock
        if (productDetail.hasStock()) {
            apiHandler.add2Cart(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    showToast(NetUtil.getMessage(response));
                    dismiss();
                }
            }, productDetail.getId(), num, 1);
        }
        //has order regular
        else if (productDetail.getIsFlagGoodsCycle()) {
            if (!preOrderRule.isItemSelected()) {
                showToast("请选择预定规则");
                return;
            } else {
                long ruleId = getOrderRuleId();
                ActivitySwitcher.toPaySettlementAct((Activity) mContext, productDetail.getId(), num, ruleId, PaySettlementAct.ORDER_TYPE_PREORDER);
                dismiss();
            }

        }

    }


    public void updateData() {
        setProductData();
        setPreOrder();
    }

    public boolean isProperySubmitable() {
        if (properyContainer.getVisibility() == View.VISIBLE) {
            if (!properyContainer.isProperySelected()) {
                return false;
            }
        }
        return true;
    }

    public boolean isNumSubmitable() {
        int num = numEdit.getNum();
        return num <= 0 ? false : true;
    }

    public boolean isOrderRegularSubmitable() {

        if (!productDetail.hasStock() && productDetail.getIsFlagGoodsCycle() && !preOrderRule.isItemSelected()) {
            return false;
        }
        return true;
    }

    public boolean submitable() {
        return isProperySubmitable() && isOrderRegularSubmitable() && isNumSubmitable();
    }

    public int getNum() {
        return numEdit.getNum();
    }

    public long getOrderRuleId() {
        int selectedPos = preOrderRule.getSelectedPosition();
        if (selectedPos != -1) {
            ReserveGoodsRuleResBean bean = productDetail.getReserveGoodsRuleResBeans().get(selectedPos);
            return bean.getRuleId();
        }
        return -1;
    }
}
