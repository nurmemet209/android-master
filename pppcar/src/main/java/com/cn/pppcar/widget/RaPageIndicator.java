package com.cn.pppcar.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 6/3/2016.
 */
public class RaPageIndicator extends RelativeLayout {

    private int itemCount;
    private int selectedPosition = -1;

    private int selectedResId;
    private int unSelectedResId;

    LinearLayout container;

    public interface OnBindViewListener {
        void OnBindView(ImageView imageView, int position);
    }

    public RaPageIndicator(Context context) {
        super(context);
    }

    public RaPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RaPageIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(int itemCount, int marginResId, OnBindViewListener onBindViewListener) {
        container = new LinearLayout(getContext());
        container.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.transparent));
        RelativeLayout.LayoutParams containerLayoutParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        containerLayoutParam.addRule(CENTER_IN_PARENT);
        container.setLayoutParams(containerLayoutParam);
        this.itemCount = itemCount;
        for (int i = 0; i < itemCount; i++) {
            ImageView point = new ImageView(getContext());
            if (onBindViewListener == null) {
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (marginResId != -1) {
                    param.leftMargin = getResources().getDimensionPixelSize(marginResId);
                }
                point.setLayoutParams(param);
                point.setImageDrawable(getBackgroudnDr());
                point.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.transparent));
            }
            if (onBindViewListener != null) {
                onBindViewListener.OnBindView(point, i);
            }
            container.addView(point);
        }
        addView(container);
    }

    public void setCurrentItem(int position) {
        if (position >= 0 && position < itemCount) {
            if (selectedPosition != -1) {
                container.getChildAt(selectedPosition).setSelected(false);
            }
            container.getChildAt(position).setSelected(true);
            selectedPosition = position;

        }
    }


    public void setBackgroundDr(int selectedResId, int unSelectedResId) {
        this.selectedResId = selectedResId;
        this.unSelectedResId = unSelectedResId;
    }

    private StateListDrawable getBackgroudnDr() {
        if (selectedResId == -1 || unSelectedResId == -1) {
            throw new IllegalStateException("selectedResId || unSelectedResId is null");
        }
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{android.R.attr.state_selected}, ContextCompat.getDrawable(getContext(), selectedResId));
        listDrawable.addState(new int[]{-android.R.attr.state_selected}, ContextCompat.getDrawable(getContext(), unSelectedResId));
        listDrawable.addState(new int[]{android.R.attr.enabled}, ContextCompat.getDrawable(getContext(), unSelectedResId));
        return listDrawable;
    }
}
