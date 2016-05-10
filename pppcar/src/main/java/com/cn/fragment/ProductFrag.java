package com.cn.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.cn.adapter.BannerAdapter;
import com.cn.commans.NetUtil;
import com.cn.entity.Item;
import com.cn.entity.ResIntegralProductDetail;
import com.cn.entity.ResProduct;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.PreferentialPackageDlg;
import com.cn.pppcar.widget.StagePayDlg;
import com.cn.sharesdk.ShareDialogEx;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class ProductFrag extends BaseFrag {


    @Bind(R.id.banner)
    protected AutoScrollViewPager banner;
    @Bind(R.id.banner_indicator)
    protected CircleIndicator indicator;

    @Bind(R.id.title)
    protected TextView mTitle;

    @Bind(R.id.sub_title)
    protected TextView mSubTitle;
    @Bind(R.id.retail_price)
    protected TextView mRetailPrice;
    @Bind(R.id.whole_sale_price)
    protected TextView mWholeSalePrice;
    @Bind(R.id.factory_number)
    protected TextView mFactoryNumber;
    @Bind(R.id.brand)
    protected TextView mBrand;
    @Bind(R.id.stock_num)
    protected TextView mStockNum;
    @Bind(R.id.freight)
    protected TextView mFreight;

    private ResProduct productDetail;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        ButterKnife.bind(this, mainView);
        init();
        return mainView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_product;
    }

    private void init() {


        new Thread(new Runnable() {
            @Override
            public void run() {

                apiHandler.getProductDetail(new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (NetUtil.isSucced(response)) {
                            productDetail = apiHandler.toObject_(NetUtil.getData(response), ResProduct.class);
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {

                                    if (productDetail != null) {
                                        ArrayList viewList = new ArrayList();
                                        String[] imgList = productDetail.getImgs().split(",");
                                        for (int i = 0; i < imgList.length; i++) {
                                            SimpleDraweeView img = new SimpleDraweeView(getActivity());
                                            Uri uri = Uri.parse(imgList[i]);
                                            img.setImageURI(uri);
                                            viewList.add(img);
                                        }
                                        BannerAdapter adapter = new BannerAdapter(getActivity(), viewList);
                                        banner.setAdapter(adapter);
                                        banner.setInterval(4000);
                                        // banner.setScrollDurationFactor(5);
                                        banner.setCycle(true);
                                        banner.setOffscreenPageLimit(viewList.size());
                                        banner.setBorderAnimation(true);
                                        banner.startAutoScroll();
                                        indicator.setViewPager(banner);


                                        mTitle.setText(productDetail.getName());
                                        mSubTitle.setText(productDetail.getName());
                                        mRetailPrice.setText(String.valueOf(productDetail.getRetailPrice()));
                                        mFactoryNumber.setText(productDetail.getManufacturingCode());
                                        mWholeSalePrice.setText(String.valueOf(productDetail.getTradePrice()));
                                        mBrand.setText(productDetail.getBrandName());
                                        mStockNum.setText(String.valueOf(productDetail.getStockNumber()));
                                        mFreight.setText("到付");


                                    }
                                }
                            });

                        } else {

                            showToast(NetUtil.getError(response));
                        }
                    }
                }, null);
            }
        }).start();


        setUpData();
    }

    private void setUpData() {


    }

    private Handler mHandler = new Handler();

    @OnClick(R.id.stage_pay)
    public void stagePay(View veiw) {
        StagePayDlg dlg = new StagePayDlg(getActivity());
        dlg.show();
    }

    @OnClick(R.id.preferential_valume_rl)
    public void preferentialValume(View view) {
//        PreferentialPackageDlg dlg = new PreferentialPackageDlg(getActivity(), null);
//        dlg.show();

        ShareDialogEx dlg=new ShareDialogEx(getContext());
        dlg.show();

    }


}
