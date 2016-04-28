package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.pppcar.R;

import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/24.
 */
public class InvoiceAddTaxCompanyFrag extends Fragment {

    private View mainView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView=inflater.inflate(R.layout.frag_invoice_add_tax__company,null);
        ButterKnife.bind(this,mainView);
        return mainView;
    }
}
