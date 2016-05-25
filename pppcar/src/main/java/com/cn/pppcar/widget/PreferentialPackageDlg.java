package com.cn.pppcar.widget;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.cn.commans.NetUtil;
import com.cn.entity.ResGroupApp;
import com.cn.entity.ResProductGroupApp;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;
import com.cn.util.Util;
import com.cn.widget.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONObject;

import java.util.HashSet;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 优惠套餐对话框
 * Created by nurmemet on 2016/5/9.
 */
public class PreferentialPackageDlg extends BaseDialog implements TagGroupLayout.OnItemChecked {

    private List<ResGroupApp> list;
    @Bind(R.id.cancel_btn)
    protected ImageButton mCancelButton;
    private Context mContext;
    @Bind(R.id.container)
    protected ViewGroup container;
    /**
     * 默认第一个被选中
     */
    private int selectedIndex = 0;
    @Bind(R.id.preferential_price)
    protected CustomTextView preferentialPrice;
    @Bind(R.id.original_price)
    protected CustomTextView originalPrice;
    @Bind(R.id.tag_container)
    protected TagGroupLayout mTagGroup;

    public PreferentialPackageDlg(Context context, List<ResGroupApp> list) {
        super(context, R.style.preferential_package_dlg);
        this.mContext = context;
        this.list = list;
        setContentView(R.layout.dlg_preferential_package);
        ButterKnife.bind(this);
        set2FullWidth(Gravity.TOP);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        bindData();

    }


    @Override
    public void dismiss() {
        super.dismiss();
    }

    public void bindData() {
        mTagGroup.setOnItemChecked(this);
        mTagGroup.setPadding(10, 4);
        mTagGroup.setSelected(0);
        mTagGroup.setTags(list, new TagGroupLayout.BindProperty() {
            @Override
            public void OnBindProperty(TextView view) {
                view.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.tag_view_sl));
                view.setTextColor(ContextCompat.getColorStateList(getContext(), R.color.main_text_color_to_white));
            }
        });
        setPrice();
        addItem();


    }
    private void setPrice(){
        ResGroupApp r = list.get(selectedIndex);
        preferentialPrice.setText(spanHelper.priceSpan(R.string.preferential_price_, r.getTotalRetailPrice()));
        builder.clear();
        originalPrice.setText(builder.append(getContext().getString(R.string.original_price_)).append(r.getTotalTradePrice()).toString());
        originalPrice.setLine(true);
    }

    private void addItem() {
        container.removeAllViews();
        if (Util.isNoteEmpty(list)) {
            HashSet<ResProductGroupApp> productList = (HashSet<ResProductGroupApp>) list.get(selectedIndex).getBsProduct();
            for (ResProductGroupApp product : productList) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_dlg_preferential_package, null);
                SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
                TextView title = (TextView) view.findViewById(R.id.title);
                TextView num = (TextView) view.findViewById(R.id.product_num);
                img.setImageURI(Uri.parse(product.getImgs()));
                title.setText(product.getName());
                num.setText(product.getProductNum()+"");
                container.addView(view);
            }
        }
    }


    @Override
    public void show() {
        super.show();
    }

    @Override
    public void onItemClick(int position, Object data) {
        selectedIndex = position;
        addItem();
        setPrice();
    }

    @OnClick(R.id.put_into_cart)
    public void add2Cart(View view){
        ResGroupApp r = list.get(selectedIndex);
        apiHandler.add2Cart(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)){
                    UIHelper.showToast(getContext(),"加入购物车成功", Toast.LENGTH_SHORT);
                }else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        },r.getId(),-1,2);
    }
}
