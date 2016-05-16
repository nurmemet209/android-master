package com.cn.pppcar.widget;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cn.commans.SpanHelper;
import com.cn.entity.ProductAttrBean;
import com.cn.entity.ResProductApp;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/5/11.
 */
public class ProductAttrDlg extends AppCompatDialog {

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
    protected Button putIntoCart;
    @Bind(R.id.num_eidt)
    protected NumEditLayout numEdit;
    @Bind(R.id.retail_price)
    protected TextView retailPrice;
    private ResProductApp productDetail;
    private ArrayList<String> keyList=new ArrayList<>();

    public ProductAttrDlg(Context context, ResProductApp productDetail) {
        super(context, R.style.dlg_product_attr_select);
        this.productDetail = productDetail;
        this.map = productDetail.getProductAttrs();
        setContentView(R.layout.dlg_product_attr);
        ButterKnife.bind(this);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.BOTTOM);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        bindData();
    }

    private void bindData() {
        container.init(productDetail.getId());
        int pd = getContext().getResources().getDimensionPixelSize(R.dimen.padding_normal);
        container.setPad(pd, pd);

//        List<String> keyList = new ArrayList<String>(map.keySet());
//        //对key键值按字典升序排序
//        Collections.sort(keyList);
        keyList.add("尺寸");
        keyList.add("宽度");
        keyList.add("ET值");
        keyList.add("颜色");
        keyList.add("圆心距");
        int index=0;
        for (String key : keyList) {
            container.addItem(key, map.get(key),index);
            index++;
        }
        titleImg.setImageURI(Uri.parse(productDetail.getImgs()));
        title.setText(productDetail.getName());
        SpanHelper spanHelper=new SpanHelper(getContext());
        retailPrice.setText(spanHelper.priceSpan(R.string.retail_price_,productDetail.getRetailPrice()));

    }



    @OnClick(R.id.put_into_cart)
    public void putIntoCart(View view) {

    }
}
