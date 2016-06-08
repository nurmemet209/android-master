package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.adapter.BaseLoadMoreAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.commans.NetUtil;
import com.cn.entity.BasePageableItem;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.LoadMoreRecycleView;

import org.json.JSONObject;

/**
 * Created by nurmemet on 6/7/2016.
 */
public abstract class LoadMoreRefreshFrag<T extends BasePageableItem, D extends BaseLoadMoreAdapter> extends BaseFrag implements LoadMoreRecycleView.OnLoadMoreListener, SwipeRefreshLayout.OnRefreshListener {

    protected boolean isLoadingMore = false;
    protected boolean isRefreshing = false;

    Response.ErrorListener mRefreshErrorListener;
    Response.ErrorListener mLoadmoreErrorListener;
    Response.ErrorListener mLoadFirstErrorListener;
    Response.Listener<JSONObject> mRefreshResponseListener;
    Response.Listener<JSONObject> mLoadMoreResponseListener;
    Response.Listener<JSONObject> mLoadFirstResponseListener;

    private final static int EMPTY_STATE_NETWORK_ERROR = 1;
    private final static int EMPTY_STATE_NO_DATA = 2;
    private final static int EMPTY_STATE_HAS_DATA = 3;

    private int emptyState = EMPTY_STATE_HAS_DATA;

    protected T pageContent;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected LoadMoreRecycleView recyclerView;
    protected View emptyView;
    protected D adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mainView.findViewById(R.id.swipe_refresh_widget);
        recyclerView = (LoadMoreRecycleView) mainView.findViewById(R.id.recycle_view);
        emptyView = mainView.findViewById(R.id.empty_view);
        setListener();
        init();
    }

    private void init() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setOnLoadMoreListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(getLayoutManager());
        if (getItemDecoration() != null) {
            recyclerView.addItemDecoration(getItemDecoration());
        }

    }

    RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    RecyclerView.ItemDecoration getItemDecoration() {
        return new CustomItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.main_big_divider_height));
    }




    protected boolean hasNextPage() {
        if (pageContent.getPage() >= pageContent.getTotalPage()) {
            return false;
        }
        return true;
    }


    protected void removeLoadingView() {
        adapter.removeLoadMoreView();
    }


    protected boolean canRefresh() {
        if (isRefreshing || isLoadingMore) {
            return false;
        }
        return true;
    }

    protected boolean canLoadMore() {
        if (isRefreshing || isLoadingMore) {
            return false;
        }
        return true;
    }

    private void setListener() {
        mRefreshErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                mSwipeRefreshLayout.setRefreshing(false);
                isRefreshing = false;
            }
        };
        mLoadmoreErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                isLoadingMore = false;
                adapter.setNetworkErrorState();
            }
        };

        mRefreshResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if (NetUtil.isSucced(response)) {
                    T all = apiHandler.toObject(NetUtil.getData(response), getClazz());
                    if (all != null) {
                        pageContent = all;
                        adapter.setList(pageContent.getList());
                        if (!hasNextPage()) {
                            removeLoadingView();
                        }
                        adapter.notifyDataSetChanged();
                        if (pageContent.getTotalPage() == 0) {
                            setEmptyViewNoDataState();
                        } else {
                            setEmptyViewHasDataState();
                        }


                    }
                }
                mSwipeRefreshLayout.setRefreshing(false);
                isRefreshing = false;
            }
        };
        mLoadMoreResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    T moreContent = apiHandler.toObject(NetUtil.getData(response), getClazz());
                    if (moreContent != null) {
                        int start = pageContent.getList().size();
                        pageContent.addNewPage(moreContent);
                        adapter.notifyItemRangeInserted(start, moreContent.getList().size());
                        if (!hasNextPage()) {
                            adapter.setNoMoreStateState();
                        }
                    }
                }
                isLoadingMore = false;
            }
        };

        mLoadFirstResponseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    pageContent = apiHandler.toObject(NetUtil.getData(response), getClazz());
                    if (pageContent != null) {
                        bindData();
                        if (!hasNextPage()) {
                            removeLoadingView();
                        }
                        if (pageContent.getTotalPage()== 0) {
                            setEmptyViewNoDataState();
                        }

                    }
                }
            }
        };
        mLoadFirstErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                setEmptyViewNetworkErrorState();
            }
        };
    }

    abstract protected Class<T> getClazz();

    abstract protected void bindData();


    @Override
    public void onLoadMore() {
        if (isRefreshing || isLoadingMore || !hasNextPage()) {
            return;
        }
        isLoadingMore = true;
        adapter.setLoadingMoreState();
        callLoadMore();
    }

    @Override
    public void onRefresh() {
        if (isLoadingMore) {
            mSwipeRefreshLayout.setRefreshing(false);
            return;
        }
        isRefreshing = true;
        callRefresh();
    }

    abstract protected void callRefresh();

    abstract protected void callLoadMore();

//    private void showEpmtyView(String message){
//        emptyView.setVisibility(View.VISIBLE);
//        TextView tv= (TextView) emptyView;
//        tv.setText(message);
//        recyclerView.setVisibility(View.INVISIBLE);
//    }
//    private void showRecycleView(){
//        emptyView.setVisibility(View.INVISIBLE);
//        recyclerView.setVisibility(View.VISIBLE);
//    }

    private void setEmptyViewHasDataState() {
        if (emptyState != EMPTY_STATE_HAS_DATA) {
            emptyView.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    private void setEmptyViewNetworkErrorState() {
        if (emptyState != EMPTY_STATE_HAS_DATA) {
            if (emptyState == EMPTY_STATE_HAS_DATA) {
                emptyView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
            }
            TextView tv = (TextView) emptyView;
            tv.setText("网络错误");

        }
    }

    private void setEmptyViewNoDataState() {
        if (emptyState != EMPTY_STATE_NO_DATA) {
            if (emptyState == EMPTY_STATE_HAS_DATA) {
                emptyView.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.INVISIBLE);
            }
            TextView tv = (TextView) emptyView;
            tv.setText("无数据");

        }
    }
}
