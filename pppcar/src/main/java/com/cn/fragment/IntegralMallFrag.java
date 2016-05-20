package com.cn.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Response;
import com.cn.adapter.GridItemDecoration;
import com.cn.adapter.IntegralMallFragAdapter;
import com.cn.commans.NetUtil;
import com.cn.entity.ResPageIntegral;
import com.cn.pppcar.IntegralMallAct;
import com.cn.pppcar.R;
import com.cn.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralMallFrag extends BaseFrag  {

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    IntegralMallFragAdapter adapter;
    private ResPageIntegral resPageIntegral;

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
        EventBus.getDefault().register(this);

        new Thread(new Runnable() {
            @Override
            public void run() {

                apiHandler.getIntegralProduct(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (NetUtil.isSucced(response)) {
                            resPageIntegral = apiHandler.toObject(NetUtil.getData(response), ResPageIntegral.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    if (resPageIntegral != null && Util.isNoteEmpty(resPageIntegral.getResIntegralProducts())) {
                                        adapter = new IntegralMallFragAdapter(getActivity(), resPageIntegral.getResIntegralProducts());
                                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
                                        recyclerView.addItemDecoration(new GridItemDecoration(getActivity(),getResources().getDimensionPixelSize(R.dimen.main_big_divider_height)/2,2));
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

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_integral_mall;
    }
    private Handler mHandler=new Handler();
    @Subscribe
    public void onEventMainThread(String event) {
        if ("refresh".equals(event)){
            Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();
        }


    }
}
