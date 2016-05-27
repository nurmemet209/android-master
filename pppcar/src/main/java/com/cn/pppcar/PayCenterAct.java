package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.cn.pppcar.widget.SelectecableLinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/4/4.
 */
public class PayCenterAct extends BaseAct {


    @Bind(R.id.pay_way_container)
    protected SelectecableLinearLayout mContainer;

    int payPayWayId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_pay_center);
        ButterKnife.bind(this);
        loadData();
    }


    private void loadData() {
        mContainer.init();
    }


//    @OnClick({R.id.pay_by_card,R.id.pay_by_ali,R.id.pay_by_wechat,R.id.pay_by_offline})
//    protected void payWaySelect(View view){
//        View imgView=view.findViewWithTag("selector");
//        if (payPayWayId!=-1){
//            View selected=findViewById(payPayWayId);
//            selected.setSelected(false);
//        }
//        payPayWayId=imgView.getId();
//        imgView.setSelected(true);
//    }
}
