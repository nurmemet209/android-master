package com.cn.pppcar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;


/**
 * Created by nurmemet on 5/25/2016.
 */
public class SelectableRelaytiveLayoutItem extends RelativeLayout {



    public SelectableRelaytiveLayoutItem(Context context) {
        super(context);
    }

    public SelectableRelaytiveLayoutItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectableRelaytiveLayoutItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        this.setClickable(true);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = (ImageView) findViewWithTag("selector_");
                boolean isSelected = imageView.isSelected();
                imageView.setSelected(!isSelected);
            }
        });
    }

    public boolean isItemSelected() {
        ImageView imageView = (ImageView) findViewWithTag("selector_");
        return imageView.isSelected();
    }


}
