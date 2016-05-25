package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cn.component.OnItemClicked;
import com.cn.entity.ResFavorites;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by nurmemet on 2016/5/23.
 */
public class CollectProductAdapter extends BaseListAdapter<ResFavorites> {

    private OnItemClicked onAddToCartBtnClicked;
    private OnItemClicked onUnCollectBtnClicked;

    public CollectProductAdapter(Context mContext, List<ResFavorites> list, OnItemClicked onAddToCartBtnClicked, OnItemClicked onUnCollectBtnClicked) {
        super(mContext, list);
        this.onAddToCartBtnClicked = onAddToCartBtnClicked;
        this.onUnCollectBtnClicked = onUnCollectBtnClicked;
    }

    public CollectProductAdapter(Context mContext, List<ResFavorites> list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_frag_collect_product, null);
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        SimpleDraweeView img = (SimpleDraweeView) holder.itemView.findViewById(R.id.title_img);
        TextView title = (TextView) holder.itemView.findViewById(R.id.title);
        TextView price = (TextView) holder.itemView.findViewById(R.id.price);
        TextView unCollect = (TextView) holder.itemView.findViewById(R.id.uncollect);
        ImageButton addToCart = (ImageButton) holder.itemView.findViewById(R.id.add_to_cart);

        img.setImageURI(Uri.parse(list.get(position).getImgs()));
        title.setText(list.get(position).getName());
        price.setText("$" + list.get(position).getRetailPrice());
        unCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onUnCollectBtnClicked != null) {
                    onUnCollectBtnClicked.OnClick(v, holder.getAdapterPosition(), list.get(holder.getAdapterPosition()));
                }
            }
        });
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onAddToCartBtnClicked != null) {
                    onAddToCartBtnClicked.OnClick(v, holder.getAdapterPosition(), list.get(holder.getAdapterPosition()));
                }
            }
        });
    }
}
