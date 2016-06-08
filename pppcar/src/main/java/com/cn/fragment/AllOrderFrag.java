package com.cn.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.adapter.BaseLoadMoreAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.OrderAdapter;
import com.cn.commans.NetUtil;
import com.cn.component.OnListItemWidgetClickedListener;
import com.cn.entity.PageResPersonalCenterOrder;
import com.cn.entity.ResOrder;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.LoadMoreRecycleView;
import com.cn.util.MyLogger;
import com.cn.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class AllOrderFrag extends LoadMoreRefreshFrag<PageResPersonalCenterOrder, OrderAdapter> {
    /**
     * 1 普通订单，2 预订单
     */
    private int orderType = 1;
    private String orderState;


    public static AllOrderFrag getInstance(int orderType, String state) {
        AllOrderFrag frag = new AllOrderFrag();
        Bundle bd = new Bundle();
        bd.putInt("orderType", orderType);
        bd.putString("orderState", state);
        frag.setArguments(bd);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        orderType = getArguments().getInt("orderType");
        orderState = getArguments().getString("orderState");
        ButterKnife.bind(this, mainView);
        EventBus.getDefault().register(this);
        return mainView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_all_order;
    }

    private void loadData() {
        apiHandler.getMyOrder(mLoadFirstResponseListener, orderType, orderState == "all" ? "" : orderState, "1", mLoadFirstErrorListener);
    }

    @Override
    protected void bindData() {
        adapter = new OrderAdapter(pageContent.getResOrders(), getActivity(), orderType, new OnListItemWidgetClickedListener() {
            @Override
            public void OnItemClicke(int commond, RecyclerView.ViewHolder holder, Object extra) {

                int position = holder.getAdapterPosition();
                if (commond == OrderAdapter.CANCEL_BTN) {
                    cancelOrder(pageContent.getResOrders().get(position).getId(), holder);
                } else if (commond == OrderAdapter.DELETE_BTN) {
                    deleteOrder(pageContent.getResOrders().get(position).getId(), holder);
                } else if (commond == OrderAdapter.PAY_BTN) {

                }
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void callRefresh() {
        apiHandler.getMyOrder(mRefreshResponseListener, orderType, orderState == "all" ? "" : orderState, "1", mRefreshErrorListener);
    }

    @Override
    protected void callLoadMore() {
        apiHandler.getMyOrder(mLoadMoreResponseListener, orderType, orderState == "all" ? "" : orderState, String.valueOf(pageContent.getPage() + 1), mLoadmoreErrorListener);

    }


    private void cancelOrder(long orderId, final RecyclerView.ViewHolder holder) {
        Map<String, String> param = new HashMap<>();
        param.put("orderId", String.valueOf(orderId));
        if (orderType == 1) {
            showProgressDlg();
            apiHandler.cancelCommonOrder(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    dismissProgressDlg();
                    showToast(NetUtil.getMessage(response));
                    if (NetUtil.isSucced(response)) {
                        //loadData();
                        int pos = holder.getAdapterPosition();
                        postCancel(pos);
                    }
                }
            }, param, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dismissProgressDlg();
                    MyLogger.showError(error.getMessage());
                }
            });
        } else if (orderType == 2) {
            apiHandler.cancelPreOrder(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, param, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }

    }

    private void deleteOrder(long orderId, final RecyclerView.ViewHolder holder) {
        Map<String, String> param = new HashMap<>();
        param.put("orderId", String.valueOf(orderId));
        if (orderType == 1) {
            showProgressDlg();
            apiHandler.deleteCommonOrder(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    dismissProgressDlg();
                    showToast(NetUtil.getMessage(response));
                    if (NetUtil.isSucced(response)) {
                        int position = holder.getAdapterPosition();
//                        allOrder.getResOrders().remove(position);
//                        adapter.notifyItemRemoved(position);
                        postDelete(position);
                    }

                }
            }, param, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    dismissProgressDlg();
                    MyLogger.showError(error.getMessage());
                }
            });
        } else if (orderType == 2) {
            apiHandler.cancelPreOrder(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                }
            }, param, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        }

    }


    private void postDelete(int position) {
        ResOrder resOrder = pageContent.getResOrders().get(position);
        EventBus.getDefault().post(new EventBusEv("order_delete", resOrder));
    }

    private void postCancel(int position) {
        String state = pageContent.getResOrders().get(position).getState();
        EventBus.getDefault().post(new EventBusEv("order_cancel", state));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(EventBusEv ev) {
        if (EventBusEv.is(ev, "order_cancel")) {
            String state = (String) ev.getData();
            if (state.equals(orderState) || "all".equals(orderState)) {
                onRefresh();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void delete(EventBusEv ev) {
        if (EventBusEv.is(ev, "order_delete")) {
            ResOrder resOrder = (ResOrder) ev.getData();
            int pos = pageContent.getResOrders().indexOf(resOrder);
            if (pos != -1) {
                pageContent.getResOrders().remove(pos);
                adapter.notifyItemRemoved(pos);
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @Override
    protected Class<PageResPersonalCenterOrder> getClazz() {
        return PageResPersonalCenterOrder.class;
    }
}
