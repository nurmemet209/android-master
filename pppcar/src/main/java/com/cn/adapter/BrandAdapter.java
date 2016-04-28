package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/25.
 */
public class BrandAdapter extends BaseListAdapter {

    public BrandAdapter(Context mContext, ArrayList<Item> list) {
        super(mContext, list);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SimpleDraweeView img = new SimpleDraweeView(mContext);
        int h=mContext.getResources().getDimensionPixelOffset(R.dimen.list_item_img_height);
        RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, h);
        img.setLayoutParams(params);
        img.setBackgroundColor(mContext.getResources().getColor(R.color.red));
//        TextView tv=new TextView(mContext);
//        tv.setWidth(600);
//        tv.setHeight(600);
        //tv.setText("nurmemetfdsflsdfjldsjfksdl;fjkdsl;fjksdljfkdsljkfdsljfkdsljkfklskdjfdsklfjslskjdflkds");
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(img) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        SimpleDraweeView img = (SimpleDraweeView) holder.itemView;

        img.setImageURI(Uri.parse(list.get(position).getImgAddress()));

    }


}
