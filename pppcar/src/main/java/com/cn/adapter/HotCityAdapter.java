package com.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.entity.BaseEntity;
import com.cn.pppcar.R;
import com.cn.util.Util;

import java.util.List;

/**
 * Created by nurmemet on 2016/3/29.
 */
public class HotCityAdapter extends RecyclerView.Adapter<HotCityAdapter.CustomViewHolder> {
    private List<BaseEntity> list;
    private Context context;

    public HotCityAdapter(Context context,List<BaseEntity> list) {
        this.context=context;
        this.list = list;
    }

    @Override
    public HotCityAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView= LayoutInflater.from(context).inflate(R.layout.list_item_hot_city,null);

        CustomViewHolder holder=new CustomViewHolder(mainView);
        return holder;
    }

    @Override
    public void onBindViewHolder(HotCityAdapter.CustomViewHolder holder, int position) {
        holder.contentView.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)){
            return  list.size();
        }
        return 0;
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder{
        public TextView contentView;
        public CustomViewHolder(View itemView) {
            super(itemView);
            contentView=(TextView) itemView.findViewById(R.id.hot_city_name);
        }
    }
}
