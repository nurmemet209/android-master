package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.commans.ActivitySwitcher;
import com.cn.commans.SpanHelper;
import com.cn.entity.Consignee;
import com.cn.entity.Item;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/4/28.
 */
public class ReceiveAddressListAdapter extends BaseListAdapter<Consignee> {
    private int selectedPosition = -1;
    private View selectedView=null;

    public ReceiveAddressListAdapter(Context mContext, List<Consignee> list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_act_receive_address_list, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };


        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        TextView name = (TextView) holder.itemView.findViewById(R.id.name);
        TextView address = (TextView) holder.itemView.findViewById(R.id.address);
        name.setText(list.get(position).getConsignee() + "   " + list.get(position).getMobileNumber());
        if (list.get(position).getIsDefault()) {
            address.setText(getFormattedAddress(list.get(position).getAddress()));
            if (selectedPosition==-1){
                selectedPosition=position;
            }
        } else {
            address.setText(list.get(position).getAddress());
        }

        if (selectedPosition == position) {
            selectedView=holder.itemView;
            holder.itemView.setSelected(true);
        } else {
            holder.itemView.setSelected(false);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int position = holder.getAdapterPosition();
                if (position == selectedPosition) {
                    return;
                }
                if (selectedView!=null){
                    selectedView.setSelected(false);
                }
                selectedView=v;
                selectedPosition = position;
                boolean isSelected = v.isSelected();
                v.setSelected(!isSelected);
            }
        });

        holder.itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = holder.getAdapterPosition();
                EventBus.getDefault().post(new EventBusEv("deleteConsignee", holder));
//                list.remove(adapterPosition);
//                ReceiveAddressListAdapter.this.notifyItemRemoved(adapterPosition);
            }
        });
        holder.itemView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Consignee consignee = list.get(position);
                EventBus.getDefault().postSticky(new EventBusEv("consignee", consignee));
                ActivitySwitcher.toReceiveAddressEditAct((Activity) mContext);
            }
        });
//        if (selectedPosition==position){
//            holder.itemView.findViewById(R.id.)
//        }

    }

    private SpannableStringBuilder getFormattedAddress(String addr) {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(" 默认 ");
        builder.setSpan(new ForegroundColorSpan(ContextCompat.getColor(mContext, R.color.white)), 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.setSpan(new BackgroundColorSpan(ContextCompat.getColor(mContext, R.color.main_red)), 0, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(" ").append(addr);
        return builder;
    }

    public int getSelectedPosition(){
        return selectedPosition;
    }
    public void setSelectedPosition(int position){
        this.selectedPosition=position;
    }
}
