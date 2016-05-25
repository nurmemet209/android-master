package com.cn.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.cn.adapter.BannerAdapter;
import com.cn.commans.NetUtil;
import com.cn.entity.ResProductApp;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.PreOrderDlg;
import com.cn.pppcar.widget.PreferentialPackageDlg;
import com.cn.pppcar.widget.ProductAttrDlg;
import com.cn.pppcar.widget.StagePayDlg;
import com.cn.widget.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class ProductFrag extends BaseFrag {

    public static final String COLLECT = "collect_";
    public static final String ADD_2_CART = "add2cart_";

    @Bind(R.id.banner)
    protected AutoScrollViewPager banner;
    @Bind(R.id.banner_indicator)
    protected CircleIndicator indicator;

    @Bind(R.id.title)
    protected TextView mTitle;

    @Bind(R.id.sub_title)
    protected TextView mSubTitle;
    @Bind(R.id.retail_price)
    protected CustomTextView mRetailPrice;
    @Bind(R.id.whole_sale_price)
    protected CustomTextView mWholeSalePrice;
    @Bind(R.id.pre_order_price)
    protected CustomTextView mPreOrderPrice;
    @Bind(R.id.factory_number)
    protected TextView mFactoryNumber;
    @Bind(R.id.brand)
    protected TextView mBrand;
    @Bind(R.id.stock_num)
    protected TextView mStockNum;
    @Bind(R.id.freight)
    protected TextView mFreight;

    private ResProductApp productDetail;

    private long proId;
    private BannerAdapter bannerAdapter;
    private List bannerViewList;
    private ProductAttrDlg productAttrDlg;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        proId = getActivity().getIntent().getLongExtra("proId", -1);
        ButterKnife.bind(this, mainView);
        loadData();
        return mainView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_product;
    }

    private void loadData() {
        apiHandler.getProductDetail(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    productDetail = apiHandler.toObject(NetUtil.getData(response), ResProductApp.class);
                    if (productDetail != null) {
                        bindData();
                    }
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, null, proId);


    }

    private void bindData() {

        if (bannerViewList == null) {
            bannerViewList = new ArrayList();
        } else {
            bannerViewList.clear();
        }
        String[] imgList = productDetail.getImgs().split(",");
        for (int i = 0; i < imgList.length; i++) {
            SimpleDraweeView img = new SimpleDraweeView(getActivity());
            Uri uri = Uri.parse(imgList[i]);
            img.setImageURI(uri);
            bannerViewList.add(img);
        }
        if (bannerAdapter == null) {
            bannerAdapter = new BannerAdapter(getActivity(), bannerViewList);
            banner.setAdapter(bannerAdapter);
            banner.setInterval(4000);
            // banner.setScrollDurationFactor(5);
            banner.setCycle(true);
            banner.setOffscreenPageLimit(bannerViewList.size());
            banner.setBorderAnimation(true);
            banner.startAutoScroll();
            indicator.setViewPager(banner);
        } else {
            bannerAdapter.setViewList(bannerViewList);
            bannerAdapter.notifyDataSetChanged();
        }

        mTitle.setText(productDetail.getName());
        mSubTitle.setText(productDetail.getBriefDescribe());
        mRetailPrice.setText(String.valueOf(productDetail.getRetailPrice()));
        mRetailPrice.setLine(true);
        mWholeSalePrice.setText(String.valueOf(productDetail.getTradePrice()));
        mPreOrderPrice.setText(String.valueOf(productDetail.getActivityPrice()));
        mFactoryNumber.setText(productDetail.getManufacturingCode());
        mBrand.setText(productDetail.getBrandName());
        mStockNum.setText(String.valueOf(productDetail.getStockNumber()));
        mFreight.setText("到付");
        //收藏
        View c = getActivity().findViewById(R.id.collect);
        c.setSelected(productDetail.getIsFlagFavorites());

        if (productAttrDlg!=null&&productAttrDlg.isShowing()) {
            productAttrDlg.updateData();
        }
    }


    @OnClick(R.id.stage_pay)
    public void stagePay(View veiw) {
        StagePayDlg dlg = new StagePayDlg(getActivity());
        dlg.show();
    }

    @OnClick(R.id.preferential_valume_rl)
    public void preferentialValume(View view) {
        PreferentialPackageDlg dlg = new PreferentialPackageDlg(getActivity(), productDetail.getResGroupApps());
        dlg.show();

//        ShareDialogEx dlg=new ShareDialogEx(getContext());
//        dlg.show();

    }

    /**
     * 预订单
     *
     * @param view
     */
//    @OnClick(R.id.select_pre_order)
//    public void selectPreOrder(View view) {
//        PreOrderDlg dlg = new PreOrderDlg(getActivity(), productDetail);
//        dlg.show();
//    }

    /**
     * 产品规格
     *
     * @param veiw
     */
    @OnClick(R.id.select_product_attr)
    public void selectProductAttr(View veiw) {
        showProductAttrSelectDlg();
    }

    /**
     * 收藏
     *
     * @param event
     */
    @Subscribe
    public void onEventMainThread(final EventBusEv event) {
        if (COLLECT.equals(event.getEvent())) {

            apiHandler.collect(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (NetUtil.isSucced(response)) {
                        View view = (View) event.getData();
                        boolean isSelected = view.isSelected();
                        if (isSelected) {
                            showToast(NetUtil.getMessage(response));
                            //Toast.makeText(getActivity(), getString(R.string.uncollect_successed), Toast.LENGTH_SHORT).show();
                        } else {
                            showToast(NetUtil.getMessage(response));
                            //Toast.makeText(getActivity(), getString(R.string.collect_successed), Toast.LENGTH_SHORT).show();
                        }
                        view.setSelected(!isSelected);
                    } else {
                        showToast(NetUtil.getMessage(response));
                    }
                }
            }, productDetail != null ? productDetail.getId() : -1);
        } else if (ADD_2_CART.equals(event.getEvent())) {
            showProductAttrSelectDlg();

        }
    }

    private void showProductAttrSelectDlg() {
        if (productAttrDlg != null) {
            productAttrDlg.show();
        } else {
            productAttrDlg = new ProductAttrDlg(getActivity(), productDetail, new Runnable() {
                @Override
                public void run() {
                    loadData();
                }
            });
            productAttrDlg.show();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
