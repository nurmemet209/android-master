package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.cn.adapter.GridItemDecoration;
import com.cn.adapter.IntegralMallFragAdapter;
import com.cn.commans.NetUtil;
import com.cn.entity.ResPageIntegral;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.IntegralMallAct;
import com.cn.pppcar.R;
import com.cn.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralMallFrag extends BaseFrag {

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;
    private int type = -1;
    private String sortType = "down";

    IntegralMallFragAdapter adapter;
    private ResPageIntegral resPageIntegral;

    public static IntegralMallFrag getInstance(int type) {
        IntegralMallFrag frag = new IntegralMallFrag();
        Bundle bd = new Bundle();
        bd.putInt("type", type);
        frag.setArguments(bd);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        type = getArguments().getInt("type");
        loadData();
        return mainView;
    }

    private void loadData() {
        Map<String, String> param = new HashMap<>();
        if (type != 0) {
            param.put("sortType", getSortType());
        }
        if (resPageIntegral != null) {
            param.put("page", String.valueOf(resPageIntegral.getPage() + 1));
        }
        apiHandler.getIntegralProduct(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    resPageIntegral = apiHandler.toObject(NetUtil.getData(response), ResPageIntegral.class);
                    if (resPageIntegral != null && Util.isNoteEmpty(resPageIntegral.getResIntegralProducts())) {
                        bindData();
                    }
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, param);
    }

    private String getSortType() {
        //积分大小
        if (type == 1) {
            if (sortType.equals("up")) {
                return "1";
            } else if (sortType.equals("down")) {
                return "2";
            }
        } else if (type == 2) {
            if (sortType.equals("up")) {
                return "4";
            } else if (sortType.equals("down")) {
                return "3";
            }
        }
        return "";
    }


    private void bindData() {
        if (adapter == null) {
            adapter = new IntegralMallFragAdapter(getActivity(), resPageIntegral.getResIntegralProducts());
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerView.addItemDecoration(new GridItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.main_big_divider_height) / 2, 2));
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setList(resPageIntegral.getResIntegralProducts());
            adapter.notifyDataSetChanged();
        }
        IntegralMallAct act = (IntegralMallAct) getActivity();
        act.setIntegral(resPageIntegral.getIntegral());

    }


    @Override
    protected int getLayoutResId() {
        return R.layout.frag_integral_mall;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void sort(EventBusEv ev) {
        if (EventBusEv.is(ev, "integral_mall_sort")) {
            Map<String, String> data = (Map<String, String>) ev.getData();
            String sortType = data.get("sortType");
            int fragType = Integer.parseInt(data.get("fragType"));
            if (type == fragType) {
                this.sortType = sortType;
                loadData();
            }
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
