package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class MeFragment extends Fragment{
    private View mainView;

    public static MeFragment getInstance(){
        MeFragment frag=new MeFragment();
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView=inflater.inflate(R.layout.me_frag,null);

        return mainView;
    }
}
