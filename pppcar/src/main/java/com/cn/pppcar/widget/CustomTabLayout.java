package com.cn.pppcar.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/5/20.
 */
public class CustomTabLayout extends LinearLayout {
    private PagerAdapter adapter;
    private ViewPager viewPager;
    private BindView bindView;
    private CustomOnItemClick itemClick;
    private int drawablePadding;
    ;

    public CustomTabLayout(Context context) {
        super(context);
    }

    public CustomTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        this.setOrientation(HORIZONTAL);
        setBackgroundColor(ContextCompat.getColor(getContext(), R.color.main_bg_gray));
    }

    public void setDrawablePadding(int padding){
        this.drawablePadding=padding;
    }

    public void setViewPager(ViewPager viewPager, BindView bindView, CustomOnItemClick onItemClick) {
        if (viewPager == null) {
            throw new IllegalStateException("viewpager=null");
        }
        this.viewPager = viewPager;
        this.adapter = viewPager.getAdapter();
        this.bindView = bindView;
        this.itemClick = onItemClick;

        for (int i = 0; i < adapter.getCount(); i++) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            RelativeLayout container = new RelativeLayout(getContext());
            container.setLayoutParams(params);
            container.setClickable(true);
            View v = getTab(i, adapter.getPageTitle(i).toString(),container);
            container.addView(v);
            addView(container);

        }

    }


    private View getTab(final int position, String title,View container) {
        LinearLayout tab = new LinearLayout(getContext());
        RelativeLayout.LayoutParams param = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        param.addRule(RelativeLayout.CENTER_IN_PARENT);
        tab.setLayoutParams(param);
        final TextView text = new TextView(getContext());
        text.setText(title);
        text.setGravity(Gravity.CENTER);
        tab.addView(text);
        final ImageView img = new ImageView(getContext());

        LinearLayout.LayoutParams  params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.leftMargin=drawablePadding;
        params.gravity= Gravity.CENTER;
        img.setLayoutParams(params);
        tab.addView(img);
        if (bindView != null) {
            bindView.OnBindView(text, img, position);
        }
        container.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int oldPosition = viewPager.getCurrentItem();
                if (viewPager.getCurrentItem() != position) {

                    viewPager.setCurrentItem(position);
                    if (itemClick != null) {
                        itemClick.OnItemClicked(text, img, position, oldPosition, true);
                    }
                } else {
                    if (itemClick != null) {
                        itemClick.OnItemClicked(text, img, position, oldPosition, false);
                    }
                }
            }
        });
        return tab;
    }

    public interface BindView {
        void OnBindView(TextView tv, ImageView img, int position);
    }

    public interface CustomOnItemClick {
        void OnItemClicked(TextView tv, ImageView img, int newPosition, int oldPosition, boolean state);
    }

}
