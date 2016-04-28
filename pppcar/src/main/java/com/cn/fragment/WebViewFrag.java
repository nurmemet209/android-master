package com.cn.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/28.
 */
public class WebViewFrag extends BaseFrag {


    static public WebViewFrag getInstance() {
        WebViewFrag frag = new WebViewFrag();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return mainView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_web_view;
    }
}
