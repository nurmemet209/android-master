package com.cn.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.cn.pppcar.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/3/31.
 */
public class ProductDetailFrag extends Fragment {
    private View mainView;
    @Bind(R.id.product_detail_webview)
    protected WebView webView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.frag_product_detail, null);
        ButterKnife.bind(this, mainView);
        return mainView;
    }

    private void init(){
        webView.loadUrl("");
    }
}
