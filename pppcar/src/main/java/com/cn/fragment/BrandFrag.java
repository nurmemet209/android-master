package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cn.adapter.BrandAdapter;
import com.cn.adapter.GridItemDecoration;
import com.cn.widget.recycleview.GridSpacingItemDecoration;
import com.cn.pppcar.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/25.
 */
public class BrandFrag extends BaseFrag {
    public static int ALL = 1;
    public static int MOST_NEW = 2;
    private int type = -1;
    private BrandAdapter adapter;

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    public static BrandFrag getInstance(int type) {
        BrandFrag frag = new BrandFrag();
        Bundle bd = new Bundle();
        frag.setArguments(bd);
        return frag;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.frag_brand;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, mainView);
        init();
    }

    private void init() {
        adapter=new BrandAdapter(getActivity(),getList());
        GridLayoutManager manager=new GridLayoutManager(getActivity(),4);



        int w=getResources().getDimensionPixelSize(R.dimen.main_big_divider_height);


        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new GridItemDecoration(getActivity(),w,4));
        recyclerView.setBackgroundColor(getResources().getColor(R.color.main_bg_gray));
        recyclerView.setAdapter(adapter );


    }



}
