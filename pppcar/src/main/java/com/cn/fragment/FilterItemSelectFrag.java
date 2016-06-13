package com.cn.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.adapter.BaseSelectableListAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.StateOneAdapterEx;
import com.cn.commans.NetUtil;
import com.cn.entity.CarSeriesBean;
import com.cn.entity.CarSeriesBeanChildren;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.BaseAct;
import com.cn.pppcar.R;
import com.cn.util.MyLogger;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 6/12/2016.
 */
public class FilterItemSelectFrag extends BaseFrag {

    /**
     * 汽车品牌
     */
    public final int FILTER_TYPE_CAR_BRAND = 1;
    /**
     * 车系
     */
    public final int FILTER_TYPE_CAR_SERIES = 2;
    /**
     * 年款
     */
    public final int FILTER_TYPE_CAR_YEAR = 3;
    /**
     * 车型
     */
    public final int FILTER_TYPE_CAR_TYPE = 4;
    /**
     * 价格区间
     */
    public final int FILTER_TYPE_PRICE = 5;

    private int type = -1;
    private String year;

    @Bind(R.id.recycle_view)
    RecyclerView recyclerView;

    StateOneAdapterEx adapter;

    public static FilterItemSelectFrag getInstance(CarSeriesBean bean, int type, long id, String year) {
        FilterItemSelectFrag frag = new FilterItemSelectFrag();
        Bundle bd = new Bundle();
        bd.putSerializable("selected_bean", bean);
        bd.putInt("filter_type", type);
        bd.putLong("id_", id);
        bd.putString("year_", year);
        frag.setArguments(bd);
        return frag;
    }

    List<CarSeriesBeanChildren> carSeriesBeanChildrenList;
    CarSeriesBean selectedBean;
    private long id;
    List<CarSeriesBean> carSeriesBeanList;
    List<Object> carObjectList;
    private Handler mHandler;
    private Response.ErrorListener errorListener;
    private Response.Listener responseListener;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        selectedBean = (CarSeriesBean) getArguments().getSerializable("selected_bean");
        type = getArguments().getInt("filter_type", -1);
        id = getArguments().getLong("id_");
        year = getArguments().getString("year_");
//        mHandler = new Handler();
//        mHandler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                loadData();
//            }
//        }, 100);

        loadData();
        setListener();
        return mainView;
    }

    private void setListener() {
        errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                MyLogger.showError(error.getMessage());
                setNetworkErrorUi();
            }
        };


    }

    private void setAdapter() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new CustomItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.divider_)));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        setHasDataUi();
    }

    public void loadData() {
        if (type == FILTER_TYPE_CAR_BRAND) {
            apiHandler.getCarBrandList(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (NetUtil.isSucced(response)) {
                        carSeriesBeanChildrenList = apiHandler.JSONArray2List(NetUtil.getArrayData(response), CarSeriesBeanChildren.class);
                        if (carSeriesBeanChildrenList != null) {
                            convert(carSeriesBeanChildrenList);
                            int position = carObjectList.indexOf(selectedBean);
                            adapter = new StateOneAdapterEx(getActivity(), carObjectList, position, new BaseSelectableListAdapter.OnSelectedListener() {
                                @Override
                                public void OnSelected(int position) {
                                    CarSeriesBean obj = (CarSeriesBean) carObjectList.get(position);
                                    EventBus.getDefault().post(new EventBusEv("car_brand_selected", obj));

                                }
                            });
                            setAdapter();
                        }
                    }
                }
            }, errorListener);
        } else if (type == FILTER_TYPE_CAR_SERIES) {
            apiHandler.getCarSeriesList(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (NetUtil.isSucced(response)) {
                        carSeriesBeanChildrenList = apiHandler.JSONArray2List(NetUtil.getArrayData(response), CarSeriesBeanChildren.class);
                        if (carSeriesBeanChildrenList != null) {
                            convert(carSeriesBeanChildrenList);
                            int position = carObjectList.indexOf(selectedBean);
                            adapter = new StateOneAdapterEx(getActivity(), carObjectList, position, new BaseSelectableListAdapter.OnSelectedListener() {
                                @Override
                                public void OnSelected(int position) {

                                    CarSeriesBean obj = (CarSeriesBean) carObjectList.get(position);
                                    EventBus.getDefault().post(new EventBusEv("car_carseries_selected", obj));
                                }
                            });
                            setAdapter();
                        }
                    }
                }
            }, errorListener, String.valueOf(id));
        } else if (type == FILTER_TYPE_CAR_YEAR) {
            apiHandler.getCarYearList(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (NetUtil.isSucced(response)) {

                        carSeriesBeanList = convertYearType2Object(NetUtil.getArrayData(response));
                        if (carSeriesBeanList != null) {
                            int position = carSeriesBeanList.indexOf(selectedBean);
                            adapter = new StateOneAdapterEx(getActivity(), carSeriesBeanList, position, new BaseSelectableListAdapter.OnSelectedListener() {
                                @Override
                                public void OnSelected(int position) {
                                    CarSeriesBean obj = (CarSeriesBean) carSeriesBeanList.get(position);
                                    EventBus.getDefault().post(new EventBusEv("car_year_selected", obj));
                                }
                            });
                            setAdapter();
                        }
                    }
                }
            }, errorListener, String.valueOf(id));

        } else if (type == FILTER_TYPE_CAR_TYPE) {
            apiHandler.getCarTypeList(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (NetUtil.isSucced(response)) {

                        carSeriesBeanList = apiHandler.JSONArray2List(NetUtil.getArrayData(response), CarSeriesBean.class);
                        if (carSeriesBeanList != null) {

                            int position = carSeriesBeanList.indexOf(selectedBean);
                            adapter = new StateOneAdapterEx(getActivity(), carSeriesBeanList, position, new BaseSelectableListAdapter.OnSelectedListener() {
                                @Override
                                public void OnSelected(int position) {

                                    CarSeriesBean obj = (CarSeriesBean) carSeriesBeanList.get(position);
                                    EventBus.getDefault().post(new EventBusEv("car_type_selected", obj));
                                }
                            });
                            setAdapter();
                        }
                    }
                }
            }, errorListener, String.valueOf(id), year);

        } else if (type == FILTER_TYPE_PRICE) {
            carSeriesBeanList=getPriceList();
            int position = carSeriesBeanList.indexOf(selectedBean);
            adapter = new StateOneAdapterEx(getActivity(), carSeriesBeanList, position, new BaseSelectableListAdapter.OnSelectedListener() {
                @Override
                public void OnSelected(int position) {

                    CarSeriesBean obj = (CarSeriesBean) carSeriesBeanList.get(position);
                    EventBus.getDefault().post(new EventBusEv("price_selected", obj));
                }
            });
            setAdapter();
        }
    }



    private void setHasDataUi() {
        mainView.findViewById(R.id.loading_view).setVisibility(View.INVISIBLE);
    }

    private void setNoDataUi() {
        TextView message = (TextView) mainView.findViewById(R.id.message);
        message.setText("无数据");
    }

    private void setNetworkErrorUi() {
        TextView message = (TextView) mainView.findViewById(R.id.message);
        message.setText("网络错误");
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_filter_item_select;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private List<Object> convert(List<CarSeriesBeanChildren> carSeriesBeanChildrenList) {
        List<Object> arr = new ArrayList<>();
        for (CarSeriesBeanChildren section : carSeriesBeanChildrenList) {
            arr.add(section);
            for (CarSeriesBean item : section.getChildren()) {
                arr.add(item);
            }
        }
        carObjectList = arr;
        return arr;
    }

    private List<CarSeriesBean> getPriceList() {
        List<CarSeriesBean> list = new ArrayList<>();
        list.add(new CarSeriesBean(1, "500以下"));
        list.add(new CarSeriesBean(2, "2000-10000"));
        list.add(new CarSeriesBean(3, "10000-30000"));
        list.add(new CarSeriesBean(4, "30000-50000"));
        list.add(new CarSeriesBean(5, "50000以上"));
        return list;
    }


    @OnClick(R.id.on_back)
    public void onBack(View view) {
        EventBus.getDefault().post(new EventBusEv("on_back", null));
    }


    private List<CarSeriesBean> convertYearType2Object(String str) {
        List<CarSeriesBean> list = new ArrayList<>();
        com.alibaba.fastjson.JSONArray array = com.alibaba.fastjson.JSONArray.parseArray(str);
        for (int i = 0; i < array.size(); i++) {
            String name = array.getString(i);
            CarSeriesBean obj = new CarSeriesBean();
            obj.setName(name);
            obj.setId(i + 1);
            list.add(obj);
        }
        return list;
    }


}
