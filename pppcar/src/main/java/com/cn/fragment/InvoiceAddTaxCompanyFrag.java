package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ViewFlipper;

import com.cn.pppcar.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/4/24.
 */
public class InvoiceAddTaxCompanyFrag extends Fragment {

    @Bind(R.id.next_step)
    Button nextStep;
    @Bind(R.id.view_flipper)
    ViewFlipper viewFlipper;
    @Bind(R.id.pre_step)
    Button preStep;
    private View mainView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.frag_invoice_add_tax__company, null);
        ButterKnife.bind(this, mainView);
        return mainView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.next_step)
    public void onClick() {
        viewFlipper.showNext();
    }

    @OnClick(R.id.pre_step)
    public void preStep() {
        viewFlipper.showPrevious();
    }
}
