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
import com.cn.commans.NetUtil;
import com.cn.dialog.ConfirmDialog;
import com.cn.entity.ResIntegralProductDetail;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.RaPageIndicator;
import com.cn.util.MyLogger;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by nurmemet on 2016/4/28.
 */
public class IntegralProductDetailFrag extends BaseFrag {

    @Bind(R.id.convenientBanner)
    protected ConvenientBanner banner;
    @Bind(R.id.banner_indicator)
    protected RaPageIndicator indicator;
    //商品名称
    @Bind(R.id.title)
    protected TextView mTitle;
    //详情
    @Bind(R.id.sub_title)
    protected TextView mSubTitle;
    //积分价格
    @Bind(R.id.integral_price)
    protected TextView mIntegralPrice;
    //零售价
    @Bind(R.id.retail_price)
    protected TextView mRetailPrice;
    //出厂编号
    @Bind(R.id.factory_number)
    protected TextView mFactoryNumber;
    //品牌
    @Bind(R.id.brand)
    protected TextView mBrand;
    //库存
    @Bind(R.id.stock_num)
    protected TextView mStockNum;
    //运费
    @Bind(R.id.freight)
    protected TextView mFreight;


    private long proId = -1;


    private ResIntegralProductDetail integralProductDetail;

    static public IntegralProductDetailFrag getInstance() {
        IntegralProductDetailFrag frag = new IntegralProductDetailFrag();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        getIntentData();
        loadData();
        return mainView;
    }

    private void getIntentData() {
        proId = getActivity().getIntent().getLongExtra("proId", -1);
    }

    private void loadData() {

        apiHandler.getIntegralProductDetail(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    integralProductDetail = apiHandler.toObject(NetUtil.getData(response), ResIntegralProductDetail.class);
                    if (integralProductDetail != null) {
                        String[] imgList = integralProductDetail.getDetailImg().split(",");
                        List<String> bannerList = new ArrayList<>();
                        Collections.addAll(bannerList, imgList);
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


                        mTitle.setText(integralProductDetail.getName());
                        mSubTitle.setText(integralProductDetail.getBriefDescripe());
                        mIntegralPrice.setText(spanHelper.priceSpan("兑换积分", String.valueOf(integralProductDetail.getIntegralPrice())));
                        //mRetailPrice.setText(integralProductDetail.get);
                        mFactoryNumber.setText(integralProductDetail.getProductNumber());
                        mBrand.setText(integralProductDetail.getTypeName());
                        mStockNum.setText(String.valueOf(integralProductDetail.getStockNumber()));
                        mFreight.setText("到付");
                    } else {
                        showToast(NetUtil.getMessage(response));
                    }
                }
            }
        }, String.valueOf(proId), null);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_integral_product_detail;
    }


    @OnClick(R.id.exchange_quickly)
    public void exchangeQuickly(View view) {
        ConfirmDialog dlg = new ConfirmDialog(getActivity(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tag = (int) v.getTag();
                if (tag == ConfirmDialog.CANCEL) {
                    return;
                } else if (tag == ConfirmDialog.SURE) {

                    showProgressDlg();
                    apiHandler.getIntegralProductPaySettlementPage(new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dismissProgressDlg();
                            if (NetUtil.isSucced(response)) {

                            }
                            showToast(NetUtil.getMessage(response));
                        }
                    }, String.valueOf(proId), new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dismissProgressDlg();
                            MyLogger.showError(error.getMessage());
                        }
                    });

                }
            }
        });

        dlg.setTitleText("确定兑换");
        dlg.show();
        //ActivitySwitcher.toIntegralPaySettelmentAct(getActivity(),proId);
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
