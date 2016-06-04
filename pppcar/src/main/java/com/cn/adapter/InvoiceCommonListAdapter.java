package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.commans.ActivitySwitcher;
import com.cn.entity.ResInvoiceInfo;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.SelectableLinearLayoutItem;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by nurmemet on 5/30/2016.
 */
public class InvoiceCommonListAdapter extends BaseSelectableListAdapter<RecyclerView.ViewHolder,ResInvoiceInfo> {


    public InvoiceCommonListAdapter(Context mContext, List list, int selectedPosotion, OnSelectedListener onSelectedListener) {
        super(mContext, list,selectedPosotion,onSelectedListener);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_invoice_common_list, null);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(params);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        super.onBindViewHolder(holder,position);
        TextView companyName = (TextView) holder.itemView.findViewById(R.id.company_name);
        TextView lawPerson = (TextView) holder.itemView.findViewById(R.id.law_person);
        TextView phoneNum = (TextView) holder.itemView.findViewById(R.id.phone_num);
        TextView address = (TextView) holder.itemView.findViewById(R.id.address);
        TextView isDefault = (TextView) holder.itemView.findViewById(R.id.is_default_);
        if (list.get(position).getIsDefault()) {
            isDefault.setVisibility(View.VISIBLE);
        } else {
            isDefault.setVisibility(View.INVISIBLE);
        }
        SelectableLinearLayoutItem item = (SelectableLinearLayoutItem) holder.itemView.findViewById(R.id.selectable_item);
        //setSelectableView(item, position == selectedPostion, holder);

        companyName.setText(list.get(position).getInvoiceTitle());
        lawPerson.setText(list.get(position).getTakerName());
        phoneNum.setText(list.get(position).getTakerPhone());
        address.setText(list.get(position).getTakerAddress());

        View delete = holder.itemView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventBusEv("deleteInvoice", holder));
            }
        });

        View edit = holder.itemView.findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = holder.getAdapterPosition();
                ActivitySwitcher.toInvoiceCommonEditAct((Activity) mContext, list.get(pos));

            }
        });

    }


}
