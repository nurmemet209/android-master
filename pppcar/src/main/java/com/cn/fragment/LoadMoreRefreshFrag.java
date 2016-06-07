package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

    protected T pageContent;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected LoadMoreRecycleView recyclerView;
    protected D adapter;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = (SwipeRefreshLayout) mainView.findViewById(R.id.swipe_refresh_widget);
        recyclerView = (LoadMoreRecycleView) mainView.findViewById(R.id.recycle_view);
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

    abstract RecyclerView.LayoutManager getLayoutManager();

    abstract RecyclerView.ItemDecoration getItemDecoration();


    protected void resetSate() {
        if (isRefreshing) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        isLoadingMore = false;
        isRefreshing = false;
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

    protected void setLoadedMoreData(T moreContent) {
        pageContent.addNewPage(moreContent);
        adapter.notifyDataSetChanged();
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
                        pageContent.addNewPage(moreContent);
                        adapter.notifyDataSetChanged();
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
                        if (!hasNextPage()) {
                            removeLoadingView();
                        }
                        bindData();
                    }
                }
            }
        };
        mLoadFirstErrorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };
    }

    abstract protected Class<T> getClazz();

    abstract protected void bindData();


    @Override
    public void onLoadMore() {
        if (isRefreshing || isLoadingMore) {
            return;
        }
        isLoadingMore = true;
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

}
