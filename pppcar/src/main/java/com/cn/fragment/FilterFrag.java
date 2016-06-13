package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.transition.Transition;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.entity.CarSeriesBean;
import com.cn.entity.Item;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;
import com.cn.pppcar.SearchAct;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 6/8/2016.
 */
public class FilterFrag extends BaseFrag {


    ViewHolder viewHolder;
    Fragment fragment;
    Item selectedPartBrand;
    CarSeriesBean carPriceSelected;
    CarSeriesBean carBrandSelected;
    CarSeriesBean carSeriesSelected;
    CarSeriesBean carYearSelected;
    CarSeriesBean carTypeSelected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        EventBus.getDefault().register(this);
        viewHolder = new ViewHolder(mainView);
        return mainView;
    }

    /**
     * 选择配件品牌
     *
     * @param view
     */
    @OnClick(R.id.part_brand_container)
    public void toSelectPartBrand(View view) {
        FragmentTransaction transition = getFragmentManager().beginTransaction();
        fragment = new FilterPartsBrandFrag();
        transition.add(R.id.fragment_container, fragment).commit();

    }

    /**
     * 选择价格价格区间
     * @param view
     */
    @OnClick(R.id.price_container)
    public void toSelectPrice(View view) {
        fragment =FilterItemSelectFrag.getInstance(carPriceSelected,5,-1,null);
        openSecondFilterFrag(fragment);

    }

    /**
     * 选择汽车品牌
     *
     * @param view
     */
    @OnClick(R.id.car_brand_container)
    public void toSelectCarBrand(View view) {
        fragment = FilterItemSelectFrag.getInstance(carBrandSelected, 1,-1,null);
        openSecondFilterFrag(fragment);
    }



    /**
     * 选择车系
     *
     * @param view
     */
    @OnClick(R.id.car_series_container)
    public void toSelectCarSeries(View view) {
        fragment = FilterItemSelectFrag.getInstance(carSeriesSelected, 2,carBrandSelected.getId(),null);
        openSecondFilterFrag(fragment);

    }

    /**
     * 选择年款
     *
     * @param view
     */
    @OnClick(R.id.car_year_container)
    public void toSelectCarYear(View view) {
        fragment = FilterItemSelectFrag.getInstance(carYearSelected, 3,carSeriesSelected.getId(),null);
        openSecondFilterFrag(fragment);
    }

    /**
     * 选择年款
     *
     * @param view
     */
    @OnClick(R.id.car_type_container)
    public void toSelectCarType(View view) {
        fragment = FilterItemSelectFrag.getInstance(carTypeSelected, 4,carSeriesSelected.getId(),carYearSelected.getName());
        openSecondFilterFrag(fragment);

    }

    private void openSecondFilterFrag(Fragment frag){
        FragmentTransaction transition = getFragmentManager().beginTransaction();
        transition.add(R.id.fragment_container, frag).commit();
        viewHolder.fragmentContainer.setVisibility(View.VISIBLE);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPartBrandSelected(EventBusEv ev) {
        if (EventBusEv.is(ev, "part_brand_selected")) {
            selectedPartBrand = (Item) ev.getData();
            shutDownFilterSecondFrag();
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPriceSelected(EventBusEv ev) {
        if (EventBusEv.is(ev, "price_selected")) {
            carPriceSelected = (CarSeriesBean) ev.getData();
            shutDownFilterSecondFrag();
            viewHolder.selectedPrice.setText(carPriceSelected.getName());
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCarBrandSelected(EventBusEv ev) {
        if (EventBusEv.is(ev, "car_brand_selected")) {
            carBrandSelected = (CarSeriesBean) ev.getData();
            shutDownFilterSecondFrag();
            viewHolder.selectedCarBrand.setText(carBrandSelected.getName());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCarSeriesSelected(EventBusEv ev) {
        if (EventBusEv.is(ev, "car_carseries_selected")) {
            carSeriesSelected = (CarSeriesBean) ev.getData();
            shutDownFilterSecondFrag();
            viewHolder.selectedCarSeries.setText(carSeriesSelected.getName());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCarYearSelected(EventBusEv ev) {
        if (EventBusEv.is(ev, "car_year_selected")) {
            carYearSelected = (CarSeriesBean) ev.getData();
            shutDownFilterSecondFrag();
            viewHolder.selectedCarYear.setText(carYearSelected.getName());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCarTypeSelected(EventBusEv ev) {
        if (EventBusEv.is(ev, "car_type_selected")) {
            carTypeSelected = (CarSeriesBean) ev.getData();
            shutDownFilterSecondFrag();
            viewHolder.selectedCarType.setText(carTypeSelected.getName());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFilterItemFragBack(EventBusEv ev) {
        if (EventBusEv.is(ev, "on_back")) {

            shutDownFilterSecondFrag();
        }
    }
    private void shutDownFilterSecondFrag(){
        FragmentTransaction transition = getFragmentManager().beginTransaction();
        transition.remove(fragment).commit();
        viewHolder.fragmentContainer.setVisibility(View.INVISIBLE);
    }


    @OnClick(R.id.on_back)
    public void onShutdownDrawerView(View view) {
        shutdownDrawerView();
    }
    public void shutdownDrawerView(){
        SearchAct act = (SearchAct) getActivity();
        act.shutDownDrawer();
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.frag_filter;
    }

    static class ViewHolder {
        @Bind(R.id.selected_parts_brand)
        TextView selectedPartsBrand;
        @Bind(R.id.selected_price)
        TextView selectedPrice;
        @Bind(R.id.selected_car_brand)
        TextView selectedCarBrand;
        @Bind(R.id.car_brand_container)
        LinearLayout carBrandContainer;
        @Bind(R.id.selected_car_series)
        TextView selectedCarSeries;
        @Bind(R.id.car_series_container)
        LinearLayout carSeriesContainer;
        @Bind(R.id.selected_car_year)
        TextView selectedCarYear;
        @Bind(R.id.car_year_container)
        LinearLayout carYearContainer;
        @Bind(R.id.selected_car_type)
        TextView selectedCarType;
        @Bind(R.id.car_type_container)
        LinearLayout carTypeContainer;
        @Bind(R.id.fragment_container)
        FrameLayout fragmentContainer;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
