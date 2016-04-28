package com.cn.widget.recycleview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by nurmemet on 2016/4/26.
 */
public class HeaderSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {
    private final GridLayoutManager layoutManager;

    public HeaderSpanSizeLookup(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public int getSpanSize(int position) {
        return position == 0 ? layoutManager.getSpanCount() : 1;
    }

}
