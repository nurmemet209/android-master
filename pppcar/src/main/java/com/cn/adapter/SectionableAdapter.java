package com.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cn.entity.BaseEntity;
import com.cn.entity.SectionHeader;

import java.util.List;

/**
 * Created by nurmemet on 6/12/2016.
 */
public abstract class SectionableAdapter<M extends RecyclerView.ViewHolder> extends BaseSelectableListAdapter<M, Object> {

    protected final int SECTION_HEADER = 1;
    protected final int SECTION_ITEM = 0;

    public SectionableAdapter(Context mContext, List<Object> list, int selectedPostion, OnSelectedListener onSelectedListener) {
        super(mContext, list, selectedPostion, onSelectedListener);
    }


    @Override
    public int getItemViewType(int position) {
        if (list.get(position) instanceof SectionHeader) {
            return SECTION_HEADER;
        }
        return SECTION_ITEM;
    }

    @Override
    public M onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == SECTION_HEADER) {
            return onCreateSectionHeader(parent);
        }
        return onCreateSectionItem(parent);
    }

    abstract M onCreateSectionHeader(ViewGroup parent);

    abstract M onCreateSectionItem(ViewGroup parent);

    abstract void onBindSectionHeader(M holder, int position);

    abstract void onBindSectionItem(M holder, int position);

    @Override
    public void onBindViewHolder(M holder, int position) {
        if (getItemViewType(position) == SECTION_HEADER) {
            onBindSectionHeader(holder, position);
            return;
        }
        super.onBindViewHolder(holder,position);
        onBindSectionItem(holder, position);
        return;
    }

}
