package com.cn.adapter;

import android.content.ClipData;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cn.entity.BaseEntity;
import com.cn.entity.Item;
import com.cn.util.Util;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/25.
 */
public abstract class BaseListAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected ArrayList<T> list;


    public BaseListAdapter(Context mContext, ArrayList<T> list) {
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

    public void setList(ArrayList<T> list){
        this.list=list;
    }
}
