package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.entity.CartBean;
import com.cn.entity.Item;
import com.cn.pppcar.R;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nurmemet on 2016/4/3.
 */
public class CarFragAdapter extends BaseListAdapter<CartBean> {



    public CarFragAdapter(List<CartBean> list, Context mContext) {
        super(mContext,list);
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(mContext).inflate(R.layout.item_list_cart, null);
        ViewHolder holder = new ViewHolder(mainView){
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final View view=holder.itemView;
        TextView title=(TextView)view.findViewById(R.id.item_title);
        TextView price=(TextView)view.findViewById(R.id.item_price);
        TextView size=(TextView)view.findViewById(R.id.item_item_size);
        SimpleDraweeView img=(SimpleDraweeView)view.findViewById(R.id.title_img);
        title.setText(list.get(position).getBsProduct().getName());
        price.setText(String.valueOf(1234));
        img.setImageURI(Uri.parse(list.get(position).getBsProduct().getImgs()));
        size.setText("黑色");

        TextView minus=(TextView)view.findViewById(R.id.minus);
        TextView plus=(TextView)view.findViewById(R.id.plus);

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText itemNum=(EditText)view.findViewById(R.id.item_num_et);
                if (!StringUtils.isEmpty(itemNum.getText())){
                    String itemNumStr=itemNum.getText().toString();
                    int num=Integer.valueOf(itemNumStr);
                    if (num>0){
                        num--;
                        itemNum.setText(String.valueOf(num));
                    }
                }
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText itemNum=(EditText)view.findViewById(R.id.item_num_et);
                if (!StringUtils.isEmpty(itemNum.getText())){
                    String itemNumStr=itemNum.getText().toString();
                    int num=Integer.valueOf(itemNumStr);
                        num++;
                        itemNum.setText(String.valueOf(num));

                }
            }
        });



    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)){
            return list.size();
        }
        return 0;
    }

}
