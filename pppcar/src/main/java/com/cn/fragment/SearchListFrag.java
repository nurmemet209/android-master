package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cn.adapter.GridItemDecoration;
import com.cn.adapter.SearchListFragAdapter;
import com.cn.entity.PageProductBean;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/8.
 */
public class SearchListFrag extends LoadMoreRefreshFrag<PageProductBean, SearchListFragAdapter> {

    public static String keyWord = "";

    public final static String SORT_TYPE_MOST_NEW = "putWayTime_desc";
    /**
     * 价格降序
     */
    public final static String SORT_TYPE_PRICE_DESC = "price_desc";
    /**
     * 价格升序
     */
    public final static String SORT_TYPE_PRICE_ASC = "price_asc";


    private String priceSort;

    /**
     * 1 综合，2 最新，3 价格顺序
     */
    private int searchType = 0;

    private String sortType;

    //@Bind(R.id.recycle_view)
    // protected RecyclerView recyclerView;

    // SearchListFragAdapter adapter;
    //private PageProductBean pageProductBean;

    public static SearchListFrag getInstance(int type) {
        SearchListFrag frag = new SearchListFrag();
        Bundle bd = new Bundle();
        bd.putInt("type", type);
        frag.setArguments(bd);
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (getArguments() != null) {
            searchType = getArguments().getInt("type");
        }
        ButterKnife.bind(this, mainView);
        return mainView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_search_list;
    }


    @Override
    protected Class<PageProductBean> getClazz() {
        return PageProductBean.class;
    }

    @Override
    protected void bindData() {
        adapter = new SearchListFragAdapter(getActivity(), pageContent.getProductBean());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void callRefresh() {
        Map param = apiHandler.getParamBuilder().putParam("search_searchContent", keyWord).putParam("search_sortType", sortType).putParam("page", "1").getMap();
        apiHandler.getProductList(mRefreshResponseListener, mRefreshErrorListener, param);
    }

    @Override
    protected void callLoadMore() {
        Map param = apiHandler.getParamBuilder().putParam("search_searchContent", keyWord).putParam("search_sortType", sortType).putParam("page", getNextPage()).getMap();
        apiHandler.getProductList(mLoadMoreResponseListener, mLoadmoreErrorListener, param);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSearch(EventBusEv event) {
        String st = null;
        if ("sort".equals(event.getEvent())) {
            Map<String, String> map = (Map<String, String>) event.getData();

            String sortType = map.get("sortType");
            String fragType = map.get("fragType");
            if (!fragType.equals(String.valueOf(searchType))) {
                return;
            }
            if ("up".equals(sortType)) {
                st = SORT_TYPE_PRICE_ASC;
            } else if ("down".equals(sortType)) {
                st = SORT_TYPE_PRICE_DESC;
            }
            priceSort = st;
            // Toast.makeText(getActivity(), "refresh", Toast.LENGTH_SHORT).show();
        } else if ("search".equals(event.getEvent())) {
            //最新
            if (searchType == 1) {
                st = SORT_TYPE_MOST_NEW;
            } else if (searchType == 2) {
                st = priceSort;
            }
        }
        sortType = st;
        Map param = apiHandler.getParamBuilder().putParam("search_searchContent", keyWord).putParam("search_sortType", sortType).putParam("page", "1").getMap();
        apiHandler.getProductList(mLoadFirstResponseListener, mLoadFirstErrorListener, param);
//        apiHandler.getProductList(new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                if (NetUtil.isSucced(response)) {
//                    pageProductBean = apiHandler.toObject(NetUtil.getData(response), PageProductBean.class);
//
//                    if (pageProductBean != null && Util.isNoteEmpty(pageProductBean.getProductBean())) {
//
//                        if (adapter == null) {
//                            adapter = new SearchListFragAdapter(getActivity(), pageProductBean.getProductBean());
//                            GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
//                            recyclerView.setLayoutManager(manager);
//                            //suggestRecyclerView.setBackgroundColor(getResources().getColor(R.color.main_bg_gray));
//                           // GridItemDecoration decoration = new GridItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.main_big_divider_height) / 2, 2);
//                           // recyclerView.addItemDecoration(decoration);
//                            recyclerView.setAdapter(adapter);
//                        } else {
//                            adapter.setList(pageProductBean.getProductBean());
//                            adapter.notifyDataSetChanged();
//                        }
//                    }
//                } else {
//                    showToast(NetUtil.getMessage(response));
//                }
//            }
//        }, keyWord, st);
    }

    @Override
    RecyclerView.LayoutManager getLayoutManager() {
        GridLayoutManager manager=new GridLayoutManager(getActivity(), 2);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (!adapter.isLoadMoreViewRemoved()){
                    if (adapter.getItemCount()-1==position){
                        return 2;
                    }
                }
                return 1;
            }
        });
        return manager;
    }

    @Override
    RecyclerView.ItemDecoration getItemDecoration() {
        return new GridItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.main_big_divider_height) / 2, 2);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void setNetworkErrorUi(View emptyView) {
        TextView tv = (TextView) emptyView.findViewById(R.id.message);
        tv.setText("网络错误");
    }

    @Override
    protected void setNoDataUi(View emptyView) {
        TextView tv = (TextView) emptyView.findViewById(R.id.message);
        tv.setText("没搜到相关内容");
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }


}
