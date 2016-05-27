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
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.cn.entity.ProductAttrBean;
import com.cn.pppcar.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by nurmemet on 2016/5/11.
 */
public class PropertyLayout extends TableLayout {
    private int mColumnNum = 4;
    private int mItemPaddingLeftRight;
    private int mItemPaddingTopBottom;
    private List<View> viewList;
    private List<ProductAttr> parentList;
    private long productId = -1;

    interface ItemClick {
        void onItemClick(long id);
    }

    ItemClick onItemClick;

    public void setItemClick(ItemClick itemClick) {
        this.onItemClick = itemClick;
    }

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

    public void init(long id) {
        setShowDividers(SHOW_DIVIDER_MIDDLE);
        setDividerDrawable(getCustomDividerDrawable());
        viewList = new ArrayList<>();
        parentList = new ArrayList<>();
        productId = id;
    }

    public void addItem(String parent, Map<String, ProductAttrBean> map, int index) {
        boolean isFirst = true;
        Map<String, ProductAttrBean> subMap = new HashMap<>();
        ProductAttr attr = new ProductAttr();
        attr.parent = parent;
        attr.start = viewList.size();
        attr.size = map.size();
        for (String key : map.keySet()) {
            if (subMap.size() == mColumnNum) {
                add(parent, subMap, isFirst, index, attr);
                subMap = new HashMap<>();
                isFirst = false;
            }
            subMap.put(key, map.get(key));
        }
        add(parent, subMap, isFirst, index, attr);
        parentList.add(attr);

    }

    private void add(final String parent, final Map<String, ProductAttrBean> map, boolean isFirst, int index, final ProductAttr attr) {
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
        for (final String key : map.keySet()) {
            TextView item = new TextView(getContext());
            item.setTag(R.id.parent_index, index);
            item.setTag(R.id.parent_name, parent);
            item.setTag(R.id.state, map.get(key));
            viewList.add(item);
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
            if ("selected".equals(map.get(key).getState())) {
                item.setSelected(true);
                attr.view = item;
            } else if ("no_selected".equals(map.get(key).getState())) {
                item.setEnabled(false);
            } else {
                item.setEnabled(true);
            }
            item.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (v.isSelected()) {
                        return;
                    }
                    v.setSelected(true);
                    ProductAttrBean bean;
                    Set<Long> seletedSet = new HashSet<>();
                    int index;
                    boolean isFirst = true;
                    index = (Integer) v.getTag(R.id.parent_index);
                    if (parentList.get(index).view != null) {
                        parentList.get(index).view.setSelected(false);
                    }
                    //先求出已选中的属性的交集
                    parentList.get(index).view = v;
                    for (int i = 0; i < index; i++) {
                        ProductAttr attr = parentList.get(i);
                        for (int j = attr.start; j < attr.start + attr.size; j++) {
                            if (viewList.get(j).isSelected()) {
                                bean = (ProductAttrBean) viewList.get(j).getTag(R.id.state);
                                if (isFirst) {
                                    seletedSet.addAll(bean.getProductId());
                                    isFirst = false;
                                } else {
                                    seletedSet.retainAll(bean.getProductId());
                                }
                            }
                        }
                    }
                    for (int i = index; i < parentList.size(); i++) {
                        ProductAttr attr = parentList.get(i);
                        for (int j = attr.start; j < attr.start + attr.size; j++) {
                            bean = (ProductAttrBean) viewList.get(j).getTag(R.id.state);
                            //如果点击的不是第一个属性，即index!=0
                            if (!seletedSet.isEmpty()) {
                                if (isSelectable(bean.getProductId(), seletedSet)) {
                                    viewList.get(j).setEnabled(true);
                                    if (viewList.get(j).isSelected()) {
                                        bean.setState("selected");
                                    } else {
                                        bean.setState("can_selected");
                                    }

                                } else {
                                    if (viewList.get(j).isSelected()) {
                                        attr.view = null;
                                    }
                                    viewList.get(j).setEnabled(false);
                                    viewList.get(j).setSelected(false);
                                    bean.setState("no_selected");
                                }
                            }
                        }
                        //如果点击的是第一个属性，即index=0
                        if (seletedSet.isEmpty()) {
                            bean = (ProductAttrBean) attr.view.getTag(R.id.state);
                            seletedSet.addAll(bean.getProductId());
                        }
                        //该属性存在已选中的属性值，取交集
                        if (attr.view != null) {
                            bean = (ProductAttrBean) attr.view.getTag(R.id.state);
                            seletedSet.retainAll(bean.getProductId());
                        }
                    }

                    boolean isAllSelected = true;
                    for (int i = 0; i < parentList.size(); i++) {
                        if (parentList.get(i).view == null) {
                            isAllSelected = false;
                            break;
                        }
                    }
                    if (isAllSelected) {
                        bean = (ProductAttrBean) v.getTag(R.id.state);
                        seletedSet.retainAll(bean.getProductId());
                        if (!seletedSet.isEmpty() && seletedSet.size() == 1) {
                            Object[] id = seletedSet.toArray();
                            Long l = (Long) id[0];
                            System.out.println(l);

                            if (onItemClick != null) {
                                onItemClick.onItemClick(l);
                            }
                        }
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

    private boolean isSelectable(Set<Long> parent, Set<Long> child) {

        for (Long key : parent) {
            if (child.contains(key)) {
                return true;
            }
        }
        return false;
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


    class ProductAttr {
        String parent;
        int start;
        int size;
        View view;

        @Override
        public boolean equals(Object o) {
            if (o != null) {
                ProductAttr attr = (ProductAttr) o;
                if (parent != null) {
                    if (parent.equals(attr.parent)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }


    public boolean isProperySelected() {
        boolean isAllSelected = true;
        for (int i = 0; i < parentList.size(); i++) {
            if (parentList.get(i).view == null) {
                isAllSelected = false;
                break;
            }
        }
        return isAllSelected;
    }
}
