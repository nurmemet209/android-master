package com.cn.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.cn.adapter.GridItemDecoration;
import com.cn.adapter.IntegralMallFragAdapter;
import com.cn.adapter.SearchListFragAdapter;
import com.cn.commans.NetUtil;
import com.cn.entity.Item;
import com.cn.entity.PageProductBean;
import com.cn.entity.ResPageIntegral;
import com.cn.pppcar.IntegralMallAct;
import com.cn.pppcar.R;
import com.cn.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/8.
 */
public class SearchListFrag extends BaseFrag {

    private int searchType = 1;

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    SearchListFragAdapter adapter;
    private PageProductBean pageProductBean;

    public static SearchListFrag getInstance() {
        SearchListFrag frag = new SearchListFrag();
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

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_search_list;
    }

    private void init() {




        EventBus.getDefault().register(this);

        new Thread(new Runnable() {
            @Override
            public void run() {

                apiHandler.getProductList(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (NetUtil.isSucced(response)) {
                            pageProductBean = apiHandler.toObject_(NetUtil.getData(response), PageProductBean.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (pageProductBean != null && Util.isNoteEmpty(pageProductBean.getProductBean())) {
                                        adapter = new SearchListFragAdapter(getActivity(), pageProductBean.getProductBean());
                                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                                        recyclerView.setLayoutManager(manager);
                                        //suggestRecyclerView.setBackgroundColor(getResources().getColor(R.color.main_bg_gray));
                                        GridItemDecoration decoration = new GridItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.main_big_divider_height) / 2, 2);
                                        recyclerView.addItemDecoration(decoration);
                                        recyclerView.setAdapter(adapter);
                                    }
                                }
                            });

                        } else {

                            showToast(NetUtil.getError(response));
                        }
                    }
                }, null);
            }
        }).start();






    }

    private Handler mHandler=new Handler();

    @Subscribe
    public void onEventMainThread(String event) {
        if ("refresh".equals(event)){
            Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();
        }


    }
}
