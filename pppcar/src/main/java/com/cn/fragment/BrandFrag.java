package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Response;
import com.cn.adapter.BrandAdapter;
import com.cn.adapter.ClassifycationAdapter;
import com.cn.adapter.ClassifycationNavigationAdapter;
import com.cn.adapter.GridItemDecoration;
import com.cn.commans.NetUtil;
import com.cn.entity.Child;
import com.cn.entity.Item;
import com.cn.util.Util;
import com.cn.widget.recycleview.GridSpacingItemDecoration;
import com.cn.pppcar.R;

import org.json.JSONObject;

import java.util.ArrayList;

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
        new Thread(new Runnable() {
            @Override
            public void run() {


                apiHandler.getAllBrand(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (NetUtil.isSucced(response)) {
                            ArrayList<Item> list = (ArrayList<Item>) apiHandler.JSONArray2List(NetUtil.getArrayData(response), Item.class);

                            if (Util.isNoteEmpty(list)) {
                                adapter = new BrandAdapter(getActivity(),list);
                                GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
                                int w = getResources().getDimensionPixelSize(R.dimen.main_big_divider_height);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.addItemDecoration(new GridItemDecoration(getActivity(), w, manager.getSpanCount()));
                                recyclerView.setAdapter(adapter);
                            }

                        } else {
                            showToast(NetUtil.getError(response));
                        }


                    }
                }, null);
            }
        }).start();





    }


}
