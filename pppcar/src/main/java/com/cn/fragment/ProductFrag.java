package com.cn.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.cn.adapter.BannerAdapter;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.ResProductApp;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.PaySettlementAct;
import com.cn.pppcar.ProductDetailAct;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.PreferentialPackageDlg;
import com.cn.pppcar.widget.ProductAttrDlg;
import com.cn.pppcar.widget.RaPageIndicator;
import com.cn.pppcar.widget.StagePayDlg;
import com.cn.widget.CustomTextView;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class ProductFrag extends BaseFrag {

    public static final String COLLECT = "collect_";
    public static final String ADD_2_CART = "add2cart_";
    @Bind(R.id.convenientBanner)
    protected ConvenientBanner banner;
    @Bind(R.id.banner_indicator)
    protected RaPageIndicator indicator;
    @Bind(R.id.title)
    protected TextView mTitle;
    @Bind(R.id.sub_title)
    protected TextView mSubTitle;
    @Bind(R.id.left_price)
    protected CustomTextView mLeftPrice;
    @Bind(R.id.right_price)
    protected CustomTextView mRightPrice;
    @Bind(R.id.bottom_price)
    protected CustomTextView mBottomPrice;
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
    private ProductAttrDlg productAttrDlg;
    private PreferentialPackageDlg preferentialPackageDlg;


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
                        dataLoaded(true);
                    }
                } else {
                    showToast(NetUtil.getMessage(response));
                    dataLoaded(false);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataLoaded(false);
            }
        }, proId);


    }

    private void bindData() {


        String[] imgList = productDetail.getImgs().split(",");
        List<String> bannerList = new ArrayList<>();
        Collections.addAll(bannerList, imgList);
        banner.startTurning(5000);
        banner.setScrollDuration(1000);
        banner.setCanLoop(true);
        banner.setPointViewVisible(false);
        banner.setManualPageable(true);
        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Object createHolder() {

                return new LocalImageHolderView();
            }
        }, bannerList);
        banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                indicator.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        indicator.setBackgroundDr(R.drawable.indicator_red_radius, R.drawable.indicator_gray_radius);
        indicator.init(bannerList.size(), R.dimen.padding_normal, null);
        indicator.setCurrentItem(banner.getCurrentItem());


        mTitle.setText(productDetail.getName());
        mSubTitle.setText(productDetail.getBriefDescribe());
        if (productDetail.getIsFlagBorC()){

            mLeftPrice.setText(spanHelper.priceSpan("零售价：","￥"+productDetail.getRetailPrice()));


        }else{
            mLeftPrice.setText(spanHelper.priceSpan("批发价：","￥"+productDetail.getTradePrice()));
            mBottomPrice.setLine(true);
            mBottomPrice.setText("零售价：￥"+productDetail.getRetailPrice());

            if (productDetail.hasStock()){

            }
        }
        mFactoryNumber.setText(productDetail.getManufacturingCode());
        mBrand.setText(productDetail.getBrandName());
        mStockNum.setText(String.valueOf(productDetail.getStockNumber()));
        mFreight.setText("到付");
        //收藏
        View c = getActivity().findViewById(R.id.collect);
        c.setSelected(productDetail.getIsFlagFavorites());

        if (productAttrDlg != null && productAttrDlg.isShowing()) {
            productAttrDlg.updateData();
        }
        setActionButton();

        //优惠套餐
        if (!productDetail.getIsFlagGroup()) {
            mainView.findViewById(R.id.preferential_valume_rl).setVisibility(View.GONE);
        } else {
            mainView.findViewById(R.id.preferential_valume_rl).setVisibility(View.VISIBLE);
        }

        //规格参数
        if (!productDetail.getIsFlagProperty()) {
            mainView.findViewById(R.id.select_product_attr).setVisibility(View.GONE);
        } else {
            mainView.findViewById(R.id.select_product_attr).setVisibility(View.VISIBLE);
        }
    }

    private void setActionButton() {
        ProductDetailAct act = (ProductDetailAct) getActivity();
        act.getActionButton().setEnabled(true);
        if (productDetail.hasStock()) {
            act.getActionButton().setText("加入购物车");
        } else {
            //has stock regular
            if (productDetail.getIsFlagGoodsCycle()) {
                act.getActionButton().setText("立即预定");
            } else {
                act.getActionButton().setEnabled(false);
            }

        }
    }


    @OnClick(R.id.stage_pay)
    public void stagePay(View veiw) {
        StagePayDlg dlg = new StagePayDlg(getActivity());
        dlg.show();
    }

    @OnClick(R.id.preferential_valume_rl)
    public void preferentialValume(View view) {
        if (preferentialPackageDlg == null) {
            preferentialPackageDlg = new PreferentialPackageDlg(getActivity(), productDetail.getResGroupApps());
        }
        preferentialPackageDlg.show();

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
            if (productAttrDlg != null && productAttrDlg.submitable()) {
                //是预定订单
                if (productDetail.getIsFlagGoodsCycle()) {
                    //提交预订单
                    long id = productAttrDlg.getOrderRuleId();
                    int num = productAttrDlg.getNum();
                    ActivitySwitcher.toPaySettlementAct(getActivity(), productDetail.getId(), num, id, PaySettlementAct.ORDER_TYPE_PREORDER);
                } else {
                    //如果有选中套餐,套餐产品加入到购物车
                    if (isPreferentialSelected()) {
                        long id = preferentialPackageDlg.getSelectedPreferentialId();
                        apiHandler.add2Cart(new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (NetUtil.isSucced(response)) {
                                    showToast(NetUtil.getMessage(response));
                                } else {
                                    showToast(NetUtil.getMessage(response));
                                }
                            }
                        }, id, -1, 2);
                    }
                    //普通产品加入到购物车
                    else {
                        int num = productAttrDlg.getNum();
                        apiHandler.add2Cart(new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                showToast(NetUtil.getMessage(response));
                            }
                        }, productDetail.getId(), num, 1);
                    }
                }
            } else {
                showProductAttrSelectDlg();
            }
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


    private boolean isSubmitable() {
        if (productAttrDlg != null && productAttrDlg.submitable()) {
            return true;
        }
        return false;
    }

    private boolean isPreferentialSelected() {
        if (preferentialPackageDlg != null && preferentialPackageDlg.isPrefrentialSelected()) {
            return true;
        }
        return false;
    }


    class LocalImageHolderView implements Holder<String> {
        private ImageView img;

        @Override
        public View createView(Context context) {
            img = new SimpleDraweeView(getActivity());
            return img;
        }

        @Override
        public void UpdateUI(Context context, final int position, String data) {
            Uri uri = Uri.parse(data);
            img.setImageURI(uri);
        }
    }
}
