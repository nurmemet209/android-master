package com.cn.fragment;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.adapter.StateOneAdapter;
import com.cn.entity.BaseEntity;
import com.cn.pppcar.R;
import com.cn.util.UIHelper;
import com.cn.util.Util;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by nurmemet on 2016/3/19.
 */
public abstract  class BaseStateFragment extends Fragment implements StateOneAdapter.OnSelListner{
    public static final  String SELECTED="selected";

    private View mainView;
    @Bind(R.id.children)
    protected RecyclerView recyclerView;
    @Bind(R.id.swipe_refresh_widget)
    protected SwipeRefreshLayout swipeRefreshLayout;
    private Handler mHandler;
    private List<BaseEntity> dataList=new ArrayList<>();
    private StateOneAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private BaseEntity selectedEntity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView=inflater.inflate(R.layout.multi_stage,null);
        ButterKnife.bind(this,mainView);
        init();
        return mainView;
    }

    private void init(){
        mHandler=new Handler();
        layoutManager=new LinearLayoutManager(getActivity());

        adapter=new StateOneAdapter(getActivity(),recyclerView,dataList,selectedEntity,this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        swipeRefreshLayout.setEnabled(false);

        loadData();
    }


    private void loadData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final List<BaseEntity> tempList=pullData();
                if(Util.isNoteEmpty(tempList)){
                    if(BaseStateFragment.this.isAdded()){
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                dataList=tempList;
                                adapter.setData(dataList);
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
            }
        }).start();
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    /**
     * 非UI线程
     */
    protected   abstract ArrayList<BaseEntity> pullData();





     class DividerItemDecoration extends RecyclerView.ItemDecoration {


        public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

        public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
         /**
          * dp为单位
          */
         private float height=1;

        private GradientDrawable mDivider;

        private int mOrientation;

        public DividerItemDecoration(Context context, int orientation) {
            mDivider=new GradientDrawable();
            mDivider.setColor(context.getResources().getColor(R.color.lightgray_1));

            setOrientation(orientation);
        }

        public void setOrientation(int orientation) {
            if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
                throw new IllegalArgumentException("invalid orientation");
            }
            mOrientation = orientation;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {

            if (mOrientation == VERTICAL_LIST) {
                drawVertical(c, parent);
            } else {
                drawHorizontal(c, parent);
            }

        }

        public void drawVertical(Canvas c, RecyclerView parent) {
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + UIHelper.dip2px(getActivity(),height);
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        public void drawHorizontal(Canvas c, RecyclerView parent) {
            final int top = parent.getPaddingTop();
            final int bottom = parent.getHeight() - parent.getPaddingBottom();

            final int childCount = parent.getChildCount();
            for (int i = 0; i < childCount; i++) {
                final View child = parent.getChildAt(i);
                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                        .getLayoutParams();
                final int left = child.getRight() + params.rightMargin;
                final int right = left + mDivider.getIntrinsicHeight();
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(c);
            }
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if (mOrientation == VERTICAL_LIST) {
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            } else {
                outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
            }
        }
    }


}




