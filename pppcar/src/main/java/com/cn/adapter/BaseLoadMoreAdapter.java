package com.cn.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cn.pppcar.R;
import com.cn.util.Util;

import java.util.List;

/**
 * Created by nurmemet on 6/7/2016.
 */
public abstract class BaseLoadMoreAdapter<M extends RecyclerView.ViewHolder, T> extends BaseListAdapter<M, T> {

    public final int STATE_LOADING_MORE = 1;
    public final int STATE_NETWORK_ERROR = 2;
    public final int STATE_NO_MORE = 3;
    public final int STATE_CLICK_2_LOAD = 4;
    private int mLoadState = STATE_LOADING_MORE;

    private final int VIEW_TYPE_LOAD_MORE = 2;
    private final int VIEW_TYPE_COMMON = 1;
    View loadMoreView;
    private boolean isLoadMoreViewRemoved = false;

    public BaseLoadMoreAdapter(@NonNull Context mContext, List<T> list) {
        super(mContext, list);


    }


    @Override
    public int getItemViewType(int position) {
        if (!isLoadMoreViewRemoved) {
            int count = list.size();
            ;
            if (position >= count) {
                return VIEW_TYPE_LOAD_MORE;
            }
        }
        return VIEW_TYPE_COMMON;
    }

    @Deprecated
    @Override
    public void onBindViewHolder(M holder, int position) {
        int type = getItemViewType(position);
        if (type == VIEW_TYPE_COMMON) {
            onBindItemHolder(holder, position);
        } else {
//            ProgressBar progressBar= (ProgressBar) loadMoreView.findViewById(R.id.progress_bar);
//            progressBar.setIndeterminate(false);
//            progressBar.postInvalidate();


        }
    }

    @Deprecated
    @Override
    public M onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_COMMON) {
            return onCreateItemHolder(parent, viewType);
        } else {
            if (loadMoreView == null){
                loadMoreView = LayoutInflater.from(mContext).inflate(R.layout.item_list_load_more, null);
                ProgressBar progressBar= (ProgressBar) loadMoreView.findViewById(R.id.progress_bar);

            }

            RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
            loadMoreView.setLayoutParams(params);
            return getLoadingMoreViewHolder(loadMoreView);
        }

    }

    abstract protected M onCreateItemHolder(ViewGroup parent, int viewType);

    abstract protected void onBindItemHolder(M holder, int position);

    @Override
    public int getItemCount() {
        if (!isLoadMoreViewRemoved) {
            if (Util.isNoteEmpty(list)) {
                return list.size() + 1;
            }
        } else {
            if (Util.isNoteEmpty(list)) {
                return list.size();
            }
        }

        return 0;
    }

    abstract protected M getLoadingMoreViewHolder(View loadingMoreView);


    public void setLoadingMoreState() {
        if (STATE_LOADING_MORE == mLoadState) {
            return;
        }
        TextView message = (TextView) loadMoreView.findViewById(R.id.message);
        message.setText("正在加载");
        ProgressBar progressBar = (ProgressBar) loadMoreView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);
        mLoadState = STATE_LOADING_MORE;
        if (isLoadMoreViewRemoved) {
            int pos = getItemCount();
            notifyItemInserted(pos);
            isLoadMoreViewRemoved = false;
        }
    }

    public void setNetworkErrorState() {
        if (mLoadState == STATE_NETWORK_ERROR) {
            return;
        }
        TextView message = (TextView) loadMoreView.findViewById(R.id.message);
        message.setText("网络错误");
        ProgressBar progressBar = (ProgressBar) loadMoreView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        mLoadState = STATE_NETWORK_ERROR;

        if (isLoadMoreViewRemoved) {
            int pos = getItemCount();
            notifyItemInserted(pos);
            isLoadMoreViewRemoved = false;
        }
    }

    public void setNoMoreStateState() {
        if (mLoadState == STATE_NO_MORE) {
            return;
        }
        TextView message = (TextView) loadMoreView.findViewById(R.id.message);
        message.setText("加载完成");
        ProgressBar progressBar = (ProgressBar) loadMoreView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        mLoadState = STATE_NO_MORE;

        if (isLoadMoreViewRemoved) {
            int pos = getItemCount();
            notifyItemInserted(pos);
            isLoadMoreViewRemoved = false;
        }
    }

    public void setClick2LoadState() {
        if (mLoadState == STATE_CLICK_2_LOAD) {
            return;
        }
        TextView message = (TextView) loadMoreView.findViewById(R.id.message);
        message.setText("点击加载更多");
        ProgressBar progressBar = (ProgressBar) loadMoreView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        mLoadState = STATE_CLICK_2_LOAD;

        if (isLoadMoreViewRemoved) {
            int pos = getItemCount();
            notifyItemInserted(pos);
            isLoadMoreViewRemoved = false;
        }
    }

    public void removeLoadMoreView() {
        if (list != null) {
            isLoadMoreViewRemoved = true;
            //notifyItemRemoved(list.size());
        }

    }

    public boolean isLoadMoreViewRemoved(){
        return isLoadMoreViewRemoved;
    }


}
