package com.cn.adapter;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.entity.BaseEntity;
import com.cn.entity.SectionHeader;
import com.cn.pppcar.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 6/12/2016.
 */
public class StateOneAdapterEx extends SectionableAdapter<RecyclerView.ViewHolder> {

    public StateOneAdapterEx(Context mContext, List list, int selectedPostion, OnSelectedListener onSelectedListener) {
        super(mContext, list, selectedPostion, onSelectedListener);
    }

    @Override
    RecyclerView.ViewHolder onCreateSectionHeader(ViewGroup parent) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_filter_section_header,parent, false);
        return new SectionHeaderViewHolder(view);
    }

    @Override
    RecyclerView.ViewHolder onCreateSectionItem(ViewGroup parent) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_filter_section_item,parent,false);

        return new SectionItemViewHolder(view);
    }

    @Override
    void onBindSectionHeader(RecyclerView.ViewHolder holder, int position) {
        SectionHeaderViewHolder headHolder = (SectionHeaderViewHolder) holder;
        SectionHeader header = (SectionHeader) list.get(position);
        headHolder.title.setText(header.getName());
    }

    @Override
    void onBindSectionItem(RecyclerView.ViewHolder holder, int position) {
        BaseEntity entity = (BaseEntity) list.get(position);
        SectionItemViewHolder itemHolder = (SectionItemViewHolder) holder;
        itemHolder.title.setText(entity.getName());


    }

    static class SectionItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.is_selected)
        ImageView isSelected;

        SectionItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;

        SectionHeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
