package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.pppcar.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/5/23.
 */
public class BusineseCollectFrag extends BaseFrag{

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,mainView);
        return mainView;
    }

    public static BusineseCollectFrag getInstance(){
        BusineseCollectFrag frag=new BusineseCollectFrag();
        return frag;
    }
    @Override
    protected int getLayoutResId() {
        return R.layout.frag_collect_businese;
    }
}
