package com.cn.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.commans.ActivitySwitcher;
import com.cn.entity.Item;
import com.cn.entity.ResIntegralProduct;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralMallFragAdapter extends BaseListAdapter<RecyclerView.ViewHolder,ResIntegralProduct> {

    public IntegralMallFragAdapter(Context mContext, ArrayList<ResIntegralProduct> list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_integra_mall, null);
        view.setClickable(true);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        View view = holder.itemView;
        SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView price = (TextView) view.findViewById(R.id.price);
        TextView retailPrice= (TextView) view.findViewById(R.id.retail_price);
        img.setImageURI(Uri.parse(list.get(position).getShowImg()));
        title.setText(list.get(position).getName());
        price.setText(String.valueOf(list.get(position).getIntegralPrice()));
       // retailPrice.setText(list.get(position).get);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivitySwitcher.toIntegralProductDetailAct((Activity) mContext,list.get(position).getId());
            }
        });
    }
}
