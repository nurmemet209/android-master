package com.cn.pppcar;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;


import com.android.volley.Response;
import com.cn.adapter.MainPageAdapter;
import com.cn.commans.NetUtil;
import com.cn.commans.SharedPreferenceHelper;
import com.cn.entity.AppUserInfo;
import com.cn.net.ApiHandler;
import com.cn.util.IOUtils;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2015/12/17.
 */
public class MainPageAct extends BaseAct {
    @Bind(R.id.vp_view)
    protected ViewPager mViewPager;


    @Bind(R.id.bottom_tab)
    protected AdvancedPagerSlidingTabStrip tabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.act_main_page);
        ButterKnife.bind(this);
        init();
        // UmengUpdateAgent.update(this);
    }

    private void init() {
        login(null);
        IOUtils.copyAssetFile2ContextDir(this.getApplicationContext(), "place.db", "databases");
        setUpViewPager();
    }

    private void setUpViewPager() {
        MainPageAdapter mAdapter = new MainPageAdapter(this.getSupportFragmentManager(), this);
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());
        tabStrip.setViewPager(mViewPager);


    }

    @Override
    public void onBackPressed() {

        if (!this.getSupportFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        //MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    public void login(View view) {
        SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(MainPageAct.this);
        ApiHandler.appUserInfo = sharedPreferenceHelper.readToken();
        if (ApiHandler.appUserInfo != null)
            return;
        apiHandler.login(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    ApiHandler.appUserInfo = apiHandler.toObject(NetUtil.getData(response), AppUserInfo.class);
                    SharedPreferenceHelper sharedPreferenceHelper = new SharedPreferenceHelper(MainPageAct.this);
                    sharedPreferenceHelper.saveToken(ApiHandler.appUserInfo);
                    showToast("登录成功");
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, null, null, null, null);
    }

}
