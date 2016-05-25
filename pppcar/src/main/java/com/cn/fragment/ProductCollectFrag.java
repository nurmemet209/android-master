package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.cn.adapter.CollectProductAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.commans.NetUtil;
import com.cn.component.OnItemClicked;
import com.cn.entity.PageResFavorites;
import com.cn.entity.ResFavorites;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/5/23.
 */
public class ProductCollectFrag extends BaseFrag {

    PageResFavorites pageResFavorites;
    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    private CollectProductAdapter adapter;
    private LinearLayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        loadData();
        return mainView;
    }

    public static ProductCollectFrag getInstance() {
        ProductCollectFrag frag = new ProductCollectFrag();
        return frag;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_collect_product;
    }

    private void loadData() {
        apiHandler.collectList(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    pageResFavorites = apiHandler.toObject(NetUtil.getData(response), PageResFavorites.class);
                    if (pageResFavorites != null) {
                        adapter = new CollectProductAdapter(getActivity(), pageResFavorites.getFavorites(), new OnItemClicked() {

                            /**
                             * 添加到购物车
                             * @param v
                             * @param position
                             * @param data
                             */

                            @Override
                            public void OnClick(View v, int position, Object data) {
                                addToCart((ResFavorites) data);
                            }
                        }, new OnItemClicked() {
                            /**
                             * 取消收藏
                             * @param v
                             * @param position
                             * @param data
                             */
                            @Override
                            public void OnClick(View v, int position, Object data) {
                                unCollect((ResFavorites) data, position);
                            }
                        });
                    }

                    layoutManager=new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.addItemDecoration(new CustomItemDecoration(getActivity(), getActivity().getResources().getDimensionPixelSize(R.dimen.divider_)));
                    recyclerView.setAdapter(adapter);

                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, pageResFavorites != null ? pageResFavorites.getPage() + 1 : 1);
    }


    private void addToCart(ResFavorites favorites) {
        apiHandler.add2Cart(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    showToast(NetUtil.getMessage(response));
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, favorites.getId(), 1, 1);
    }

    private void unCollect(final ResFavorites favorites, final int position) {

        apiHandler.collect(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    showToast(NetUtil.getMessage(response));
                    pageResFavorites.getFavorites().remove(position);

                    adapter.notifyItemRemoved(position);
                    //adapter.notifyDataSetChanged();
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, favorites.getProductId());
    }

}

