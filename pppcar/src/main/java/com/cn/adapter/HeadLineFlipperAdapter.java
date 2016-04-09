package com.cn.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.pppcar.R;
import com.cn.util.Util;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/4/9.
 */
public class HeadLineFlipperAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> list;

    public HeadLineFlipperAdapter(ArrayList<String> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        this.list=new ArrayList<>();
        this.list.add("习近平为何要求突出问题导向");
        this.list.add("来看黑科技！正直播GIC虚拟现实峰会");
        this.list.add("SpaceX成功实现海上回收火箭(图)");
        this.list.add("中纪委机关报：回头看是回马枪要枪枪见血");
        this.list.add("陕西省委书记：西安交大要出钱学森也要出马云");
        this.list.add("媒体：刚刚用上中国抗旱水 越南就对中国捅刀子");
    }

    @Override
    public int getCount() {
        if (Util.isNoteEmpty(list)) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if (Util.isNoteEmpty(list)) {
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView tv = null;
        if (convertView == null) {
            tv = new TextView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            tv.setLayoutParams(params);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            int padding = mContext.getResources().getDimensionPixelOffset(R.dimen.padding_normal);
            tv.setPadding(padding, padding, padding, padding);
            tv.setMaxLines(1);
            tv.setEllipsize(TextUtils.TruncateAt.END);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            convertView = tv;
        } else {
            tv = (TextView) convertView;
        }
        tv.setText(list.get(position));
        return convertView;
    }
}
