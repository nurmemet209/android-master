package com.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.pppcar.widget.SelectableLinearLayoutItem;

import java.util.List;

/**
 * Created by nurmemet on 5/30/2016.
 */
public abstract class BaseSelectableListAdapter<T> extends BaseListAdapter<T> {
    protected View selectedView;
    protected int selectedPostion = -1;
    protected OnSelectedListener onSelectedListener;

   public interface OnSelectedListener {
        void OnSelected(int position);
    }

    public BaseSelectableListAdapter(Context mContext, List<T> list) {
        super(mContext, list);
    }


    protected void setSelectableView(View view, boolean isSelected, final RecyclerView.ViewHolder holder) {
        view.setOnClickListener(new View.OnClickListener() {
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
        if (view instanceof SelectableLinearLayoutItem) {
            if (isSelected) {
                SelectableLinearLayoutItem item = (SelectableLinearLayoutItem) view;
                item.setSelectedState(true);
                selectedView = view;
            }

        }
    }

    protected int getSelectedPostion() {
        return selectedPostion;
    }

    protected void addOnSelectedListener(OnSelectedListener onSelectedListener) {
        this.onSelectedListener = onSelectedListener;
    }
}
