package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.adapter.FindFragViewPageAdapter;
import com.cn.adapter.MainPageRecycleAdapter;
import com.cn.pppcar.R;
import com.cn.viewpager.CustomViewPager;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/2.
 */
public class FindFrag extends Fragment {
    private View mainView;

    @Bind(R.id.view_pager)
    protected CustomViewPager viewPager;
    @Bind(R.id.tab_container)
    protected TabLayout tabLayout;;

    private FindFragViewPageAdapter adapter;
    public static FindFrag getInstance(){
        FindFrag frag=new FindFrag();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView=inflater.inflate(R.layout.frag_find,null);
        ButterKnife.bind(this,mainView);
        init();
        return mainView;

    }

    private void init(){
        adapter=new FindFragViewPageAdapter(getFragmentManager(),getActivity());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
