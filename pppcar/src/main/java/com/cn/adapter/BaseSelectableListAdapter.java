package com.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.commans.SpanHelper;
import com.cn.pppcar.widget.SelectableLinearLayoutItem;
import com.cn.util.StringBuilderEx;

import java.util.List;

/**
 *
 *
 * Created by nurmemet on 5/30/2016.
 */
public abstract class BaseSelectableListAdapter<M extends RecyclerView.ViewHolder, T> extends BaseListAdapter<M,T> {



    protected View selectedView;
    protected int selectedPostion = -1;
    protected OnSelectedListener onSelectedListener;

    public interface OnSelectedListener {
        void OnSelected(int position);
    }

    public BaseSelectableListAdapter(Context mContext, List<T> list,int selectedPostion,OnSelectedListener onSelectedListener) {
        super(mContext,list);
        this.selectedPostion=selectedPostion;
        this.onSelectedListener=onSelectedListener;
    }

    @Override
     public void onBindViewHolder(final M holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedView == v) {
                    return;
                }
                if (selectedView instanceof SelectableLinearLayoutItem) {
                    SelectableLinearLayoutItem item = (SelectableLinearLayoutItem) selectedView;
                    item.setSelectedState(false);
                }
                if (v instanceof SelectableLinearLayoutItem) {
                    SelectableLinearLayoutItem item = (SelectableLinearLayoutItem) v;
                    item.setSelectedState(true);
                    selectedView = v;
                }

                selectedPostion = holder.getAdapterPosition();
                if (onSelectedListener != null) {
                    onSelectedListener.OnSelected(selectedPostion);
                }
            }
        });
        if (holder.itemView instanceof SelectableLinearLayoutItem) {
            int pos=holder.getAdapterPosition();
            if (pos==selectedPostion) {
                SelectableLinearLayoutItem item = (SelectableLinearLayoutItem) holder.itemView;
                item.setSelectedState(true);
                selectedView = holder.itemView;
            }else{
                SelectableLinearLayoutItem item = (SelectableLinearLayoutItem) holder.itemView;
                item.setSelectedState(false);
            }

        }else{
            throw  new IllegalStateException("item 最外层的布局应该是 SelectableLinearLayoutItem");
        }
    }



    public int getSelectedPostion() {
        return selectedPostion;
    }



}
