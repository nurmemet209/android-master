package com.cn.pppcar.widget;

import android.content.Context;
import android.net.Uri;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.cn.commans.NetUtil;
import com.cn.commans.SpanHelper;
import com.cn.entity.ProductAttrBean;
import com.cn.entity.ResProductApp;
import com.cn.pppcar.R;
import com.cn.util.MyLogger;
import com.cn.util.UIHelper;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;
import com.litesuits.android.async.SimpleTask;

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
    protected PropertyLayout container;
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
    @Bind(R.id.checkable_layout)
    protected CheckableLayout checkableLayout;
    private ResProductApp productDetail;
    private ArrayList<String> keyList = new ArrayList<>();

    private Runnable refreshRun;


    public ProductAttrDlg(Context context, ResProductApp productDetail, Runnable refreshRun) {
        super(context, R.style.dlg_product_attr_select);
        this.productDetail = productDetail;
        this.map = productDetail.getProductAttrs();
        this.refreshRun = refreshRun;
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
            container.setVisibility(View.GONE);
            return;
        }
        container.init(productDetail.getId());
        int pd = getContext().getResources().getDimensionPixelSize(R.dimen.padding_normal);
        container.setPad(pd, pd);
        keyList.add("尺寸");
        keyList.add("宽度");
        keyList.add("ET值");
        keyList.add("颜色");
        keyList.add("圆心距");
        int index = 0;
        for (String key : keyList) {
            container.addItem(key, map.get(key), index);
            index++;
        }
        container.setItemClick(new PropertyLayout.ItemClick() {
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
        checkableLayout.removeAllViews();
        if (productDetail.getIsFlagGoodsCycle()) {
            mActionBtn.setText("立即预定");
            container.setVisibility(View.VISIBLE);
            checkableLayout.setItems(productDetail.getReserveGoodsRuleResBeans(), new CheckableLayout.OnBindPropertyListener() {
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
            mActionBtn.setText("加入购物车");
            container.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.put_into_cart)
    public void putIntoCart(View view) {
        int num = numEdit.getNum();
        if (num == 0) {
            UIHelper.showToast(getContext(), "产品数量不能为零", Toast.LENGTH_SHORT);
            return;
        }
        apiHandler.add2Cart(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
//                if (NetUtil.isSucced(response)) {
//                    UIHelper.showToast(getContext(), "加入购物车成功", Toast.LENGTH_SHORT);
//                } else {
//                    showToast(NetUtil.getMessage(response));
//                }
                showToast(NetUtil.getMessage(response));
            }
        }, productDetail.getId(), num, 1);
    }


    public void updateData(){
        setProductData();
        setPreOrder();
    }


}
