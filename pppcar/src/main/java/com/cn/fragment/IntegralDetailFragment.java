package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.IntegralDetailAdapter;
import com.cn.pppcar.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralDetailFragment extends BaseFrag {
    public static int ALL = 1;
    public static int OUT_COME = 2;
    public static int IN_COME = 3;
    private int type;

    private IntegralDetailAdapter adapter;

    @Bind(R.id.recycle_view)
    protected RecyclerView recycleView;

    public static IntegralDetailFragment getInstance(int type) {
        IntegralDetailFragment frag = new IntegralDetailFragment();
        Bundle bd = new Bundle();
        frag.setArguments(bd);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,mainView);
        init();
        return mainView;
    }

    private void init() {
        adapter=new IntegralDetailAdapter(getActivity(),getList());
        recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycleView.addItemDecoration(new CustomItemDecoration(getActivity(),getResources().getDimensionPixelSize(R.dimen.divider)));
        recycleView.setAdapter(adapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_integral_detail;
    }
}
