package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.cn.commans.NetUtil;
import com.cn.entity.AppUserInfo;
import com.cn.entity.CollectAuctionResBean;
import com.cn.net.ApiHandler;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/5/21.
 */
public class LoginAct extends BaseAct {

    @Bind(R.id.user_name)
    protected EditText userName;
    @Bind(R.id.passward)
    protected EditText password;
    @Bind(R.id.verifycation_code)
    protected EditText varifycationCode;

    AppUserInfo appUserInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        ButterKnife.bind(this);
    }


    public void login(View view) {

//        apiHandler.login(new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                if (NetUtil.isSucced(response)) {
//                    ApiHandler.appUserInfo = apiHandler.toObject(NetUtil.getData(response), AppUserInfo.class);
//                    finish();
//                } else {
//                    showToast(NetUtil.getMessage(response));
//                }
//            }
//        }, userName.getText().toString(), password.getText().toString(), varifycationCode.getText().toString(), "1234567890");
    }


}
