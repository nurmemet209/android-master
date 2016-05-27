package com.cn.pppcar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by nurmemet on 5/26/2016.
 */
public class SelectecableLinearLayout extends LinearLayout {
    private View selectedView;
    private View selectedChild;
    private boolean canReverseSelect = true;
    private OnClickListener onClickListener;

    private int selectedPosition = -1;

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
    }

    public SelectecableLinearLayout(Context context) {
        super(context);

    }

    public SelectecableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public SelectecableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public void setCanReverseSelect(boolean canReverseSelect) {
        this.canReverseSelect = canReverseSelect;
    }

    public void setItemClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public int getSelectedPosition(){
        return selectedPosition;
    }

    public void init() {
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            if (selectedPosition == i) {
                child.setSelected(true);
                selectedChild = child;
                selectedView = child.findViewWithTag("selector_");
            }
            final int pos = i;
            child.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //是否可以反选
                    if (!canReverseSelect && v == selectedChild) {
                        return;
                    }
                    View selectableItem = v.findViewWithTag("selector_");
                    boolean isSelected = selectableItem.isSelected();
                    selectableItem.setSelected(!isSelected);
                    if (selectedView != null) {
                        selectedView.setSelected(false);
                    }
                    if (!isSelected) {
                        selectedView = selectableItem;
                        selectedChild = child;
                        selectedPosition = pos;
                    } else {
                        selectedPosition = -1;
                        selectedView = null;
                        selectedChild = null;
                    }

                    if (onClickListener != null) {
                        onClickListener.onClick(v);
                    }

                }
            });
        }
    }
}
