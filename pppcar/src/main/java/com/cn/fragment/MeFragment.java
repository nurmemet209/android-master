package com.cn.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cn.pppcar.R;
import com.cn.pppcar.UserBaseInformationAct;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class MeFragment extends Fragment{
    private View mainView;

    @Bind(R.id.head_img)
    public SimpleDraweeView headImg;
    @Bind(R.id.head_bg)
    public SimpleDraweeView headBgImg;

    public static MeFragment getInstance(){
        MeFragment frag=new MeFragment();
        return frag;
    }




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mainView=inflater.inflate(R.layout.me_frag,null);
        ButterKnife.bind(this,mainView);
        bindData();
        setListener();
        return mainView;
    }

    private void bindData(){
        headImg.setImageURI(Uri.parse("http://c.hiphotos.bdimg.com/imgad/pic/item/00e93901213fb80ec98291ed31d12f2eb93894bd.jpg"));
        headBgImg.setImageURI(Uri.parse("http://pic10.nipic.com/20100926/5410600_133352002046_2.jpg"));
    }
    private void setListener(){
//        headImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(getActivity(), UserBaseInformationAct.class);
//                getActivity().startActivity(it);
//            }
//        });
    }
    @OnClick(R.id.head_img)
    public void toUserBaseInformation(View view){
        Intent it=new Intent(getActivity(), UserBaseInformationAct.class);
        getActivity().startActivity(it);
    }


}
