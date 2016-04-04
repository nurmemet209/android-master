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
 * 精装店
 * Created by nurmemet on 2016/4/2.
 */
public class RelicateShop extends Fragment {

    private View mainView;


    public static RelicateShop getInstance() {
        RelicateShop frag = new RelicateShop();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView = inflater.inflate(R.layout.frag_relicate_shop, null);
        ButterKnife.bind(this, mainView);
        return mainView;
    }
}
