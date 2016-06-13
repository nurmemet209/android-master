package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Response;
import com.cn.adapter.BrandAdapter;
import com.cn.adapter.FilterPartBrandAdapter;
import com.cn.adapter.GridItemDecoration;
import com.cn.commans.NetUtil;
import com.cn.component.OnListItemWidgetClickedListener;
import com.cn.entity.Item;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;
import com.cn.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 6/8/2016.
 */
public class FilterPartsBrandFrag extends BaseFrag {

    private FilterPartBrandAdapter adapter;

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    private Item selectedItem;


    @Override
    protected int getLayoutResId() {
        return R.layout.frag_filter_part_brand;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, mainView);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadData(EventBusEv ev) {
        if (EventBusEv.is(ev, "load_part_brand_data"))
            apiHandler.getAllBrand(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (NetUtil.isSucced(response)) {
                        final ArrayList<Item> list = (ArrayList<Item>) apiHandler.JSONArray2List(NetUtil.getArrayData(response), Item.class);
                        if (Util.isNoteEmpty(list)) {
                            adapter = new FilterPartBrandAdapter(getActivity(), list, new OnListItemWidgetClickedListener() {
                                @Override
                                public void OnItemClicke(int commond, RecyclerView.ViewHolder holder, Object extra) {
                                    int pos = holder.getAdapterPosition();
                                    selectedItem = list.get(pos);
                                    EventBus.getDefault().post(new EventBusEv("part_brand_selected", selectedItem));
                                }
                            });
                            GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
                            int w = getResources().getDimensionPixelSize(R.dimen.main_big_divider_height);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.addItemDecoration(new GridItemDecoration(getActivity(), w, manager.getSpanCount()));
                            recyclerView.setAdapter(adapter);
                        }

                    } else {
                        showToast(NetUtil.getMessage(response));
                    }


                }
            }, null);
    }

}
