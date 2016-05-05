package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.cn.adapter.ClassifycationAdapter;
import com.cn.adapter.ClassifycationNavigationAdapter;
import com.cn.commans.NetUtil;
import com.cn.component.OnItemSelected;
import com.cn.entity.Child;
import com.cn.pppcar.R;
import com.cn.util.Util;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 品牌
 * * Created by nurmemet on 2015/12/19.
 */
public class ClassificationFrag extends BaseFrag implements OnItemSelected {


    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;
    @Bind(R.id.navigation)
    protected RecyclerView navigation;

    ClassifycationAdapter adapter;
    ClassifycationNavigationAdapter navigationAdapter;

    public static ClassificationFrag getInstance() {
        ClassificationFrag frag = new ClassificationFrag();
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
        new Thread(new Runnable() {
            @Override
            public void run() {


                apiHandler.getClassifycation(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (NetUtil.isSucced(response)) {
                            ArrayList<Child> list = (ArrayList<Child>) apiHandler.JSONArray2List(NetUtil.getArrayData(response), Child.class);

                            if (Util.isNoteEmpty(list)) {
                                navigationAdapter = new ClassifycationNavigationAdapter(getActivity(), list, ClassificationFrag.this);
                                navigation.setLayoutManager(new LinearLayoutManager(getActivity()));
                                navigation.setAdapter(navigationAdapter);

                                adapter = new ClassifycationAdapter(getActivity(), getChildList(list.get(0)));
                                GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
                                manager.setSpanSizeLookup(adapter.getSpanSizeLookUp());
                                recyclerView.addItemDecoration(adapter.getDecoration());
                                recyclerView.setLayoutManager(manager);
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

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_classifycation;
    }

    @Override
    public void onItemSelected(View view, int position, Object data) {
        Child child = (Child) data;
        adapter.setList(getChildList(child));
        adapter.notifyDataSetChanged();
    }

    private ArrayList<Object> getChildList(Child child) {
        if (child != null && Util.isNoteEmpty(child.getChildren())) {
            ArrayList<Object> list = new ArrayList<>();
            for (Child c : child.getChildren()) {
                list.add(c.getName());
                for (Child s : c.getChildren()) {
                    list.add(s);

                }
            }
            return list;
        }
        return null;
    }

    @OnClick(R.id.select_car_type)
    public void SelecteCarType(View view) {

    }
}
