package com.cn.adapter;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.cn.commans.SpanHelper;
import com.cn.entity.BaseEntity;
import com.cn.entity.Item;
import com.cn.pppcar.widget.SelectableLinearLayoutItem;
import com.cn.util.StringBuilderEx;
import com.cn.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/4/25.
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected List<T> list;
    protected StringBuilderEx builder;
    protected SpanHelper spanHelper;



    public BaseListAdapter(Context mContext, List<T> list) {
        this.mContext = mContext;
        this.list = list;
        builder = new StringBuilderEx();
        spanHelper = new SpanHelper(mContext);
    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)) {
            return list.size();
        }
        return 0;
    }

    public void setList(List<T> list) {
        this.list = list;
    }



}
