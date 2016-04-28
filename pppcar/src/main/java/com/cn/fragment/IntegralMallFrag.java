package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.GridItemDecoration;
import com.cn.adapter.IntegralMallFragAdapter;
import com.cn.pppcar.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralMallFrag extends BaseFrag {

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    IntegralMallFragAdapter adapter;

    public static IntegralMallFrag getInstance(int type) {
        IntegralMallFrag frag = new IntegralMallFrag();
        Bundle bd = new Bundle();
        frag.setArguments(bd);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        init();
        return mainView;
    }

    private void init() {
        adapter = new IntegralMallFragAdapter(getActivity(), getList());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        recyclerView.addItemDecoration(new GridItemDecoration(getActivity(),getResources().getDimensionPixelSize(R.dimen.main_big_divider_height)/2,2));
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_integral_mall;
    }
}
