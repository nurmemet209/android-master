package com.cn.pppcar.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.cn.adapter.BaseListAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.component.OnItemSelected;
import com.cn.pppcar.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/5/18.
 */
public class YearSelectDlg extends BaseDialog {


    private final OnItemSelected onItemSelected;
    @Bind(R.id.title)
    protected TextView title;

    @Bind(R.id.recycle_view)
    RecyclerView recyclerView;

    YearAdapter adapter;
    List<String> yearList;
    private int selectedYear = -1;

    public YearSelectDlg(Context context, OnItemSelected onItemSelected, int selectedYear) {
        super(context, R.style.year_select_dlg);
        this.onItemSelected = onItemSelected;
        this.selectedYear = selectedYear;
        setContentView(R.layout.dlg_year_select);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        WindowManager.LayoutParams attr = getWindow().getAttributes();
        attr.width = getContext().getResources().getDimensionPixelOffset(R.dimen.main_dlg_max_width);
        getWindow().setAttributes(attr);
        recyclerView.addItemDecoration(new CustomItemDecoration(getContext(), getContext().getResources().getDimensionPixelSize(R.dimen.divider_)));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        setUpList();
        adapter = new YearAdapter(getContext(), yearList, selectedYear==-1?"全部":String.valueOf(selectedYear));
        recyclerView.setAdapter(adapter);
    }

    private void setUpList() {
        Calendar calendar = Calendar.getInstance();
        int curYear = calendar.get(Calendar.YEAR);
        yearList = new ArrayList<>();
        yearList.add("全部");
        for (int i = 2016; i < curYear + 1; i++) {
            yearList.add(String.valueOf(i));
        }

    }

    private void onItemClicked(View view, int position) {

        if (onItemSelected != null) {
            onItemSelected.onItemSelected(view, position, yearList.get(position));
            this.dismiss();
        }
    }


    class YearAdapter extends BaseListAdapter<String> {
        private int padding;
        private int height;
        private int selectedPostion = 0;

        public YearAdapter(Context mContext, List<String> list, String selected) {
            super(mContext, list);
            padding = mContext.getResources().getDimensionPixelSize(R.dimen.padding_normal);
            height = mContext.getResources().getDimensionPixelSize(R.dimen.item_height);
            if (list != null) {
                selectedPostion = list.indexOf(selected);
            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_list_dlg_year_select, null);
            RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(view) {
            };
            return holder;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

            TextView title = (TextView) holder.itemView.findViewById(R.id.title);
            ImageView img = (ImageView) holder.itemView.findViewById(R.id.checkbox);
            title.setText(list.get(position));
            if (selectedPostion == position) {
                img.setSelected(true);
            } else {
                img.setSelected(false);
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (selectedPostion != position) {
                        int old = selectedPostion;
                        selectedPostion = position;
                        YearAdapter.this.notifyItemChanged(old);
                        YearAdapter.this.notifyItemChanged(selectedPostion);
                        onItemClicked(v, selectedPostion);
                    }

                }
            });
        }
    }
}
