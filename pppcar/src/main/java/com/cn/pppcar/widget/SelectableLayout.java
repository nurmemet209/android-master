package com.cn.pppcar.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/5/11.
 */
public class SelectableLayout extends LinearLayout {

    private View selectedView = null;
    private int selectedPosition = -1;
    private List<Object> mList;
    private OnBindPropertyListener onBindPropertyListener;

    public interface OnBindPropertyListener {
        void OnBindProperty(TextView tv, ImageView img, int position);
    }


    public SelectableLayout(Context context) {
        super(context);
        init();
    }

    public SelectableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SelectableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setSelectedPosition(int position) {
        this.selectedPosition = position;
    }

    private void init() {
        this.setOrientation(VERTICAL);
    }

    public void setItems(List<? extends Object> list, OnBindPropertyListener onBindPropertyListener) {
        this.onBindPropertyListener = onBindPropertyListener;
        if (mList == null) {
            mList = new ArrayList<>();
        }
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                View item = getSelectableItem(list.get(i).toString(), selectedPosition == i ? true : false, i);
                ViewGroup.LayoutParams params = new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                this.addView(item, params);
                final int index = i;
                item.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (selectedView != null) {
                            View view = selectedView.findViewWithTag("selectable");
                            if (view == null) {
                                throw new IllegalStateException("必须提供tag为selectable的View");
                            }
                            view.setSelected(false);
                        }
                        selectedView = v;
                        View view = selectedView.findViewWithTag("selectable");
                        if (view == null) {
                            throw new IllegalStateException("必须提供tag为selectable的View");
                        }
                        view.setSelected(true);
                        selectedPosition = index;
                    }
                });
            }
        }
    }


    public int getSelectedPosition() {
        return selectedPosition;
    }


    private View getSelectableItem(String value, boolean selected, int position) {
        RelativeLayout rl = new RelativeLayout(getContext());
        TextView title = new TextView(getContext());
        title.setText(value);
        title.setSelected(selected);
        ImageView img = new ImageView(getContext());
        RelativeLayout.LayoutParams paramTitle = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramTitle.addRule(RelativeLayout.CENTER_VERTICAL);
        rl.addView(title, paramTitle);
        RelativeLayout.LayoutParams paramImg = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        paramImg.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        img.setTag("selectable");
        rl.addView(img, paramImg);
        rl.setClickable(true);

        if (onBindPropertyListener != null) {
            onBindPropertyListener.OnBindProperty(title, img, position);
        }
        return rl;
    }

    public Object getSelectedItem() {
        if (selectedPosition != -1 && mList != null) {

            return mList.get(selectedPosition);
        }
        return null;
    }

    public boolean isItemSelected() {
        if (selectedPosition == -1) {
            return false;
        }
        return true;
    }
}
