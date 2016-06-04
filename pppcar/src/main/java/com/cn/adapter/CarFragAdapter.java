package com.cn.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.cn.component.OnListItemWidgetClickedListener;
import com.cn.entity.CartBean;
import com.cn.entity.Item;
import com.cn.fragment.CartFrag;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.NumEditLayout;
import com.cn.util.Util;
import com.facebook.drawee.view.SimpleDraweeView;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnCheckedChanged;

/**
 * Created by nurmemet on 2016/4/3.
 */
public class CarFragAdapter extends BaseListAdapter<RecyclerView.ViewHolder,CartBean> {


    private OnListItemWidgetClickedListener onListItemWidgetClickedListener;

    private boolean isEditMode;
    private List<CartBean> editCartIdList = new ArrayList<>();

    public List<CartBean> getEditCartList() {
        return editCartIdList;
    }

    public void setEditMode(boolean isEditMode) {
        this.isEditMode = isEditMode;
    }

    public CarFragAdapter(List<CartBean> list, Context mContext, OnListItemWidgetClickedListener onListItemWidgetClickedListener) {
        super(mContext, list);
        this.list = list;
        this.onListItemWidgetClickedListener = onListItemWidgetClickedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mainView = LayoutInflater.from(mContext).inflate(R.layout.item_list_cart, null);
        NumEditLayout numEditLayout = (NumEditLayout) mainView.findViewById(R.id.num_eidt);
        numEditLayout.setIsInputEnabled(false);
        ViewHolder holder = new ViewHolder(mainView) {
        };
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final View view = holder.itemView;
        TextView title = (TextView) view.findViewById(R.id.item_title);
        TextView price = (TextView) view.findViewById(R.id.item_price);
        TextView size = (TextView) view.findViewById(R.id.item_item_size);
        SimpleDraweeView img = (SimpleDraweeView) view.findViewById(R.id.title_img);
        title.setText(list.get(position).getBsProduct().getName());
        price.setText("￥"+String.valueOf(list.get(position).getTotalDiscountPrice()));
        img.setImageURI(Uri.parse(list.get(position).getBsProduct().getImgs()));


        CheckBox checkBox = (CheckBox) view.findViewById(R.id.cart_item);
        if (!isEditMode) {
            if (list.get(position).getChecked()) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        } else {
            if (editCartIdList.contains(list.get(position))) {
                checkBox.setChecked(true);
            } else {
                checkBox.setChecked(false);
            }
        }
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ps = holder.getAdapterPosition();
                CheckBox ch = (CheckBox) v;
                boolean isChecked = ch.isChecked();
                if (!isEditMode) {
                    list.get(ps).setChecked(isChecked);
                    onListItemWidgetClickedListener.OnItemClicke(CartFrag.COMMOND_CHECK_STATE_CHANGED, holder, isChecked);
                } else {
                    if (isChecked) {
                        editCartIdList.add(list.get(ps));
                    } else {
                        editCartIdList.remove(list.get(ps));
                    }
                }
            }
        });


        NumEditLayout numEditLayout = (NumEditLayout) view.findViewById(R.id.num_eidt);
        numEditLayout.setIsInputEnabled(false);
        numEditLayout.setOnNumChanged(new NumEditLayout.NumChange() {
            @Override
            public void OnNumChange(int num, Object data) {
                int ps = holder.getAdapterPosition();
                boolean isChecked = list.get(ps).getChecked();
                list.get(ps).setNumber(num);
                onListItemWidgetClickedListener.OnItemClicke(CartFrag.COMMOND_NUM_CHANGED, holder, num);
            }
        });
        numEditLayout.setNum(list.get(position).getNumber());


    }

    @Override
    public int getItemCount() {
        if (Util.isNoteEmpty(list)) {
            return list.size();
        }
        return 0;
    }

    public void setCheckedState(ViewHolder holder, boolean isChecked) {
        CheckBox checkBox = (CheckBox) holder.itemView.findViewById(R.id.cart_item);
        checkBox.setChecked(isChecked);
    }


}
