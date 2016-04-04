package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.pppcar.R;

/**
 * Created by nurmemet on 2016/4/2.
 */
public class RefiteWork extends Fragment {
    private View mainView;



    public static RefiteWork getInstance(){
        RefiteWork frag=new RefiteWork();
        return frag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView=inflater.inflate(R.layout.frag_refit_work,null);

        return mainView;
    }
}
