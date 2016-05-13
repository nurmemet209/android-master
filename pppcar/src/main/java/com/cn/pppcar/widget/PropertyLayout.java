package com.cn.pppcar.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cn.entity.ProductAttrBean;
import com.cn.pppcar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nurmemet on 2016/5/11.
 */
public class PropertyLayout extends TableLayout {
    private int mColumnNum = 4;
    private int mItemPaddingLeftRight;
    private int mItemPaddingTopBottom;
    private Map<String, View> mSelectedViewList;
    /**
     * 以dp为单位
     */
    private int mRowMargin = 5;

    public void setPad(int rl, int tb) {
        mItemPaddingLeftRight = rl;
        mItemPaddingTopBottom = tb;
    }

    public PropertyLayout(Context context) {
        super(context);
    }

    public PropertyLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init() {
        setShowDividers(SHOW_DIVIDER_MIDDLE);
        setDividerDrawable(getCustomDividerDrawable());
        mSelectedViewList = new HashMap<>();
    }

    public void addItem(String parent, Map<String, ProductAttrBean> map) {
        boolean isFirst = true;
        Map<String, ProductAttrBean> subMap = new HashMap<>();
        ArrayList<View> viewList = new ArrayList<>();
        mSelectedViewList.put(parent, null);
        for (String key : map.keySet()) {
            if (subMap.size() == mColumnNum) {
                add(parent, subMap, isFirst, viewList);
                subMap = new HashMap<>();
                isFirst = false;
            }
            subMap.put(key, map.get(key));
        }
        add(parent, subMap, isFirst, viewList);


    }

    private void add(final String parent, final Map<String, ProductAttrBean> map, boolean isFirst, final ArrayList<View> viewList) {
        TableRow row = new TableRow(getContext());
        TextView title = new TextView(getContext());
        title.setGravity(Gravity.RIGHT);
        title.setPadding(mItemPaddingLeftRight, mItemPaddingTopBottom, mItemPaddingLeftRight, mItemPaddingTopBottom);
        row.addView(title);
        row.setShowDividers(SHOW_DIVIDER_MIDDLE);
        row.setDividerDrawable(getCustomDividerDrawable());
        if (isFirst) {
            title.setText(parent);
        }
        for (String key : map.keySet()) {
            TextView item = new TextView(getContext());
            item.setText(key);
            item.setTag(parent);
            item.setGravity(Gravity.CENTER);
            item.setBackground(getBg());
            item.setTextColor(getColorStateList());
            item.setClickable(true);
            TableRow.LayoutParams param = new TableRow.LayoutParams();
            param.height = TableRow.LayoutParams.MATCH_PARENT;
            param.width = 0;
            param.weight = 1;
            row.addView(item, param);
            viewList.add(item);
            if ("selected".equals(map.get(key).getState())) {
                item.setSelected(true);
                mSelectedViewList.put(parent, item);
            } else if ("no_selected".equals(map.get(key).getState())) {
                item.setEnabled(false);
            } else {
                item.setEnabled(true);
            }
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (View view : viewList) {
                        if (view.isSelected()) {
                            view.setSelected(false);
                        }
                        v.setSelected(true);
                        mSelectedViewList.put(parent, v);
                    }
                }
            });

        }
        for (int i = map.size(); i < mColumnNum; i++) {
            TextView item = new TextView(getContext());
            item.setGravity(Gravity.CENTER);
            TableRow.LayoutParams param = new TableRow.LayoutParams();
            param.height = TableRow.LayoutParams.MATCH_PARENT;
            param.width = 0;
            param.weight = 1;
            row.addView(item, param);

        }
        this.addView(row);

    }

    public int dip2px(float dpValue) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    private Drawable getCustomDividerDrawable() {
        RectShape rc = new RectShape();
        ShapeDrawable drawable = new ShapeDrawable(rc);
        drawable.setIntrinsicWidth(dip2px(mRowMargin));
        drawable.setIntrinsicHeight(dip2px(mRowMargin));
        drawable.getPaint().setColor(Color.WHITE);
        return drawable;
    }

    private Drawable getDisenableDrawable() {
        RectShape rc = new RectShape();
        ShapeDrawable drawable = new ShapeDrawable(rc);
        drawable.getPaint().setAntiAlias(true);
        drawable.getPaint().setStyle(Paint.Style.STROKE);
        drawable.getPaint().setColor(ContextCompat.getColor(getContext(), R.color.main_sub_text_color));
        return drawable;
    }

    private Drawable getEnableDrawable() {
        RectShape rc = new RectShape();
        ShapeDrawable drawable = new ShapeDrawable(rc);
        drawable.getPaint().setAntiAlias(true);
        drawable.getPaint().setStyle(Paint.Style.STROKE);
        drawable.getPaint().setColor(ContextCompat.getColor(getContext(), R.color.main_text_color));
        return drawable;
    }

    private Drawable getSelectedDrawable() {
        RectShape rc = new RectShape();
        ShapeDrawable drawable = new ShapeDrawable(rc);
        drawable.getPaint().setAntiAlias(true);
        drawable.getPaint().setStyle(Paint.Style.STROKE);
        drawable.getPaint().setColor(ContextCompat.getColor(getContext(), R.color.main_red));
        return drawable;
    }

    private StateListDrawable getBg() {
        StateListDrawable listDrawable = new StateListDrawable();
        listDrawable.addState(new int[]{-android.R.attr.state_enabled}, getDisenableDrawable());
        listDrawable.addState(new int[]{android.R.attr.state_selected}, getSelectedDrawable());
        listDrawable.addState(new int[]{android.R.attr.state_enabled}, getEnableDrawable());
        return listDrawable;
    }

    private ColorStateList getColorStateList() {
        int[][] stateList = new int[3][];
        stateList[0] = new int[]{-android.R.attr.state_enabled};
        stateList[1] = new int[]{android.R.attr.state_selected, android.R.attr.state_enabled};
        stateList[2] = new int[]{android.R.attr.state_enabled};
        int[] colorList = new int[]{ContextCompat.getColor(getContext(), R.color.main_sub_text_color), ContextCompat.getColor(getContext(), R.color.main_red), ContextCompat.getColor(getContext(), R.color.main_text_color)};
        ColorStateList colorStateList = new ColorStateList(stateList, colorList);
        return colorStateList;
    }
}
