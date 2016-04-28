package com.cn.adapter;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cn.entity.Item;
import com.cn.util.Util;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/25.
 */
public abstract class BaseListAdapter extends RecyclerView.Adapter {
    protected Context mContext;
    protected ArrayList<Item> list;


    public BaseListAdapter(Context mContext, ArrayList<Item> list) {
        this.mContext = mContext;
        this.list = list;
    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)){
            return list.size();
        }
        return 0;
    }
}
