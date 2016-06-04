package com.cn.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.entity.FavourableActivityBean;
import com.cn.pppcar.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 6/2/2016.
 */
public class PreferentialListAdapter extends BaseSelectableListAdapter<PreferentialListAdapter.ViewHolder, FavourableActivityBean> {


    public PreferentialListAdapter(Context mContext, List<FavourableActivityBean> list, int selectedPosition) {
        super(mContext, list, selectedPosition, null);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.act_item_list_preferential_list_act, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.title.setText(list.get(position).getActName());
        holder.time.setText("到期时间 " + list.get(position).getEndTime());
        holder.property.setText(list.get(position).getActRangeName());

    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.title)
        TextView title;
        @Bind(R.id.property)
        TextView property;
        @Bind(R.id.time)
        TextView time;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
