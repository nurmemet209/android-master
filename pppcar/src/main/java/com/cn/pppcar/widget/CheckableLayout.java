package com.cn.pppcar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by nurmemet on 2016/5/11.
 */
public class CheckableLayout extends LinearLayout {

    private View selectedView = null;
    private int selectedPosition = -1;
    private int itemTextSize = -1;
    private int itemTextColor = -1;
    private int drawableResId = -1;
    private int imgViewWidth = -1;
    private int imgViewHeight = -1;

    public CheckableLayout(Context context) {
        super(context);
    }

    public CheckableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CheckableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void init() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            final int index = i;
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (selectedView != null) {
                        View view = selectedView.findViewWithTag("selectable");
                        if (view == null) {
                            throw new IllegalStateException("必须提供tag为selectable的Vew");
                        }
                        view.setSelected(false);
                    }
                    selectedView = v;
                    View view = selectedView.findViewWithTag("selectable");
                    if (view == null) {
                        throw new IllegalStateException("必须提供tag为selectable的Vew");
                    }
                    view.setSelected(true);
                    selectedPosition = index;
                }
            });
        }
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }


    public View getSelectableItem(String value, boolean selected) {
        RelativeLayout rl = new RelativeLayout(getContext());
        TextView title = new TextView(getContext());
        title.setText(value);
        title.setSelected(selected);
        if (itemTextColor != -1) {
            title.setTextColor(itemTextColor);
        }
        if (itemTextSize != -1) {
            title.setTextSize(itemTextSize);
        }
        ImageView img = new ImageView(getContext());
        RelativeLayout.LayoutParams paramTitle = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramTitle.addRule(RelativeLayout.CENTER_VERTICAL);
        rl.addView(title, paramTitle);
        RelativeLayout.LayoutParams paramImg = new RelativeLayout.LayoutParams(imgViewWidth == 0 ? RelativeLayout.LayoutParams.WRAP_CONTENT : imgViewWidth, imgViewHeight == 0 ? RelativeLayout.LayoutParams.WRAP_CONTENT : imgViewHeight);
        paramTitle.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        img.setTag("selectable");
        img.setBackgroundResource(drawableResId);
        rl.addView(img, paramImg);
        return rl;
    }
}
