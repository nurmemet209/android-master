package com.cn.pppcar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by nurmemet on 5/27/2016.
 */
public class SelectableLinearLayoutItem extends LinearLayout {
    public SelectableLinearLayoutItem(Context context) {
        super(context);
        init();
    }

    public SelectableLinearLayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectableLinearLayoutItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        this.setClickable(true);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImageView imageView = (ImageView) findViewWithTag("selector_");
                boolean isSelected = v.isSelected();
                v.setSelected(!isSelected);
            }
        });
    }
}
