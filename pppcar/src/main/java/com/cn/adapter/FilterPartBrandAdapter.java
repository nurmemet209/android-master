package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.component.OnItemSelected;
import com.cn.component.OnListItemWidgetClickedListener;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 6/8/2016.
 */
public class FilterPartBrandAdapter extends BaseListAdapter<RecyclerView.ViewHolder, Item> {
    private OnListItemWidgetClickedListener onItemClicked;

    public FilterPartBrandAdapter(Context mContext, ArrayList<Item> list, OnListItemWidgetClickedListener onItemClicked) {
        super(mContext, list);
        this.list = list;
        this.onItemClicked = onItemClicked;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_filter_part_brand, null);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        SimpleDraweeView img = (SimpleDraweeView) holder.itemView.findViewById(R.id.title_img);
        img.setScaleType(ImageView.ScaleType.CENTER);
        img.setImageURI(Uri.parse(list.get(position).getImg()));
        TextView title = (TextView) holder.itemView.findViewById(R.id.title);
        title.setText(list.get(position).getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClicked.OnItemClicke(-1, holder, null);
            }
        });
    }
}
