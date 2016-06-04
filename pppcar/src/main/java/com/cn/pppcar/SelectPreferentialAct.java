package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.PreferentialListAdapter;
import com.cn.entity.FavourableActivityBean;
import com.cn.localutils.EventBusEv;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 6/2/2016.
 */
public class SelectPreferentialAct extends BaseAct {


    @Bind(R.id.recycle_view)
    RecyclerView recycleView;
    @Bind(R.id.preferential_valume_num)
    TextView preferentialValumeNum;
    ArrayList<FavourableActivityBean> list;
    int selectedPos = 0;
    PreferentialListAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_preferential);
        ButterKnife.bind(this);
        getIntentData();
        bindData();
    }

    private void bindData() {

        adapter = new PreferentialListAdapter(this, list, selectedPos);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        recycleView.addItemDecoration(new CustomItemDecoration(this, getResources().getDimensionPixelSize(R.dimen.main_big_divider_height)));
        recycleView.setAdapter(adapter);

        preferentialValumeNum.setText("有" + list.size() + "张优惠卷可用");
    }

    private void getIntentData() {
        list = getIntent().getParcelableArrayListExtra("list");
        selectedPos = getIntent().getIntExtra("selected", 0);
    }


    @OnClick(R.id.sure_btn)
    public void onClick() {
        selectedPos = adapter.getSelectedPostion();
        EventBus.getDefault().post(new EventBusEv("setPreferentional", list.get(selectedPos)));
        finish();
    }
}
