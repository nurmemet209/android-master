package com.cn.pppcar.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.commans.SpanHelper;
import com.cn.entity.BaseEntity;
import com.cn.entity.ResProperty;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.cn.widget.TimeLine;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.Dictionary;

import me.gujun.android.taggroup.TagGroup;

/**
 * 优惠套餐对话框
 * Created by nurmemet on 2016/5/9.
 */
public class PreferentialPackageDlg extends AppCompatDialog implements TagGroupLayout.OnItemClick {

    private ArrayList<ResProperty> list;

    private ImageButton mCancelButton;
    private Context mContext;
    private ViewGroup container;


    public PreferentialPackageDlg(Context context, ArrayList<ResProperty> list) {
        super(context,R.style.preferential_package_dlg);
        this.mContext = context;
        this.list = list;


        setContentView(R.layout.dlg_preferential_package);
        Window dialogWindow = this.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width=WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialogWindow.setGravity(Gravity.TOP);
        container = (ViewGroup) findViewById(R.id.container);
        mCancelButton=(ImageButton)findViewById(com.cn.customlibrary.R.id.cancel_btn);
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

        addItem();
        TagGroupLayout mTagGroup = (TagGroupLayout) findViewById(R.id.tag_container);
        mTagGroup.setOnItemClick(this);
        mTagGroup.setPadding(10, 4);
        ArrayList<String> list = new ArrayList<>();
        list.add("套餐1");
        list.add("套餐2");
        list.add("套餐3er");
        list.add("套餐2erer");
        list.add("套餐3erer");
        list.add("套餐2er");
        list.add("套餐ererer3");
        list.add("套餐");
        list.add("套餐er3");
        list.add("套餐1");
        list.add("套餐2");
        list.add("套餐3er");
        list.add("套餐");

        mTagGroup.setTags(list);

        SpanHelper spanHelper = new SpanHelper(getContext());
        TextView preferentialPrice = (TextView) findViewById(R.id.preferential_price);
        preferentialPrice.setText(spanHelper.priceSpan(R.string.preferential_price_, 232.99F));
        TextView originalPrice = (TextView) findViewById(R.id.original_price);
        originalPrice.setText(mContext.getString(R.string.original_price_) + "12.3443");

    }

    private void addItem() {
        container.removeAllViews();
        if (Util.isNoteEmpty(list)) {
            for (int i = 0; i < list.size(); i++) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_dlg_preferential_package, null);
                SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
                TextView title = (TextView) view.findViewById(R.id.title);
                TextView num = (TextView) view.findViewById(R.id.product_num);
                img.setImageURI(Uri.parse(list.get(i).getImageAddress()));
                title.setText(list.get(i).getName());
                num.setText("12");
                container.addView(view);
            }
        }
    }


    @Override
    public void onItemClick(int position, Object data) {

    }

    @Override
    public void show() {
        super.show();
    }
}
