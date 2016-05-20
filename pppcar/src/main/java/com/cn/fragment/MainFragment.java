package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.cn.adapter.GridItemDecoration;
import com.cn.adapter.MainPageRecycleAdapter;
import com.cn.commans.NetUtil;
import com.cn.entity.CartResBean;
import com.cn.entity.Item;
import com.cn.entity.ResIndexPublicElement;
import com.cn.pppcar.R;
import com.cn.util.Util;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class MainFragment extends BaseFrag {

    private MainPageRecycleAdapter adapter;
    @Bind(R.id.main_page_recycle_view)
    protected RecyclerView recyclerView;
    ResIndexPublicElement resIndexPublicElement;

    public static MainFragment getInstance() {
        MainFragment frag = new MainFragment();
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
        return R.layout.main_frag;
    }

    private void init() {

        initBanner();
    }


    private void initBanner() {

        apiHandler.getMainPage(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    resIndexPublicElement = apiHandler.toObject(NetUtil.getData(response), ResIndexPublicElement.class);

                    adapter = new MainPageRecycleAdapter(resIndexPublicElement, getActivity());
                    GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                        @Override
                        public int getSpanSize(int position) {
                            if (position == 0) {
                                return 2;
                            }
                            return 1;
                        }
                    });
                    recyclerView.setLayoutManager(manager);
                    recyclerView.addItemDecoration(new GridItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.main_big_divider_height) / 2, 2, true));
                    recyclerView.setAdapter(adapter);
                } else {
                    showToast(NetUtil.getError(response));
                }
            }
        });
    }
}
