package com.cn.component;

import android.support.v7.widget.RecyclerView;

/**
 * Created by nurmemet on 5/24/2016.
 */
public interface OnListItemWidgetClickedListener {
    void OnItemClicke(int commond, RecyclerView.ViewHolder holder,Object extra);
}
