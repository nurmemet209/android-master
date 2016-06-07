package com.cn.pppcar.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.cn.adapter.BaseLoadMoreAdapter;
import com.cn.util.MyLogger;

/**
 * Created by nurmemet on 5/29/2016.
 */
public class LoadMoreRecycleView extends RecyclerView {

    private BaseLoadMoreAdapter adapter;
    private OnLoadMoreListener onLoadMoreListener;


    public interface OnLoadMoreListener {
        void onLoadMore();
    }

    public LoadMoreRecycleView(Context context) {
        super(context);
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadMoreRecycleView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        this.adapter = (BaseLoadMoreAdapter) adapter;
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);

    }


    private void init() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
                    if (layoutManager != null) {
                        int lastOne = layoutManager.findLastVisibleItemPosition();
                        int count=getAdapter().getItemCount() - 1;
                        if (dy > 0 && lastOne == count) {
                            adapter.setLoadingMoreState();
                            if (onLoadMoreListener != null) {
                                onLoadMoreListener.onLoadMore();
                            }
                        }
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        if (canScrollVertically(-1)) {

        }
        return super.onInterceptTouchEvent(e);
    }

    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        this.onLoadMoreListener = onLoadMoreListener;
    }


}
