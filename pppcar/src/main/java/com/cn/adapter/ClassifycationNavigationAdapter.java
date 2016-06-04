package com.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.cn.component.OnItemSelected;
import com.cn.entity.Child;
import com.cn.entity.Item;
import com.cn.pppcar.R;

import java.util.ArrayList;


/**
 * Created by nurmemet on 2016/5/3.
 */
public class ClassifycationNavigationAdapter extends BaseListAdapter<RecyclerView.ViewHolder,Child> {
    private int selectedIndex = 0;

    private OnItemSelected onItemSelected;


    public ClassifycationNavigationAdapter(Context mContext, ArrayList<Child> list, OnItemSelected itemClickListener) {
        super(mContext, list);
        this.onItemSelected = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = new TextView(mContext);
        tv.setBackground(mContext.getResources().getDrawable(R.drawable.white_gray_sl));
        RecyclerView.ViewHolder holder=new RecyclerView.ViewHolder(tv) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (selectedIndex == position) {
            holder.itemView.setSelected(true);
        } else {
            holder.itemView.setSelected(false);
        }

        TextView tv = (TextView) holder.itemView;
        tv.setText(list.get(position).getName());
        tv.setGravity(Gravity.CENTER);

        RecyclerView.LayoutParams params=new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(params);
        tv.setHeight(mContext.getResources().getDimensionPixelSize(R.dimen.item_height));
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedIndex != position) {
                    int oldSelected=selectedIndex;
                    selectedIndex=position;
                    ClassifycationNavigationAdapter.this.notifyItemChanged(oldSelected);
                    v.setSelected(true);
                    if (onItemSelected != null) {
                        onItemSelected.onItemSelected(v, selectedIndex,list.get(position));
                    }
                }

            }
        });


    }
}
