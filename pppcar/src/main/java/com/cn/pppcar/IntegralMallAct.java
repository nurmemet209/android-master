package com.cn.pppcar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.adapter.IntegralDetailViewPagerAdapter;
import com.cn.adapter.IntegralMallViewPagerAdapter;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralMallAct extends BaseViewPagerAct {


    protected IntegralMallViewPagerAdapter adapter;

//    @Bind(R.id.integral)
//    protected TextView integralTv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_integral_mall);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void init() {
        adapter = new IntegralMallViewPagerAdapter(getSupportFragmentManager(), this);
        setUpViewPager(adapter);
        setDrawables();
        viewPager.setOffscreenPageLimit(adapter.getCount());

    }

    private void setDrawables() {
        TextView integral = (TextView) tabLayout.getTabAt(1).findViewById(R.id.id_tab_txt);
        Drawable d = getResources().getDrawable(R.mipmap.top_bottom);
        d.setBounds(0,0,d.getIntrinsicWidth(), d.getIntrinsicHeight());
        int drawablePadding = getResources().getDimensionPixelOffset(R.dimen.padding_smallest_);
        integral.setCompoundDrawablePadding(drawablePadding);
        integral.setCompoundDrawables(null, null, d, null);
        integral.setTag("up");
        integral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=(TextView)v;
                //Toast.makeText(IntegralMallAct.this,"clicked",Toast.LENGTH_LONG).show();
                viewPager.setCurrentItem(1);
                if (v.getTag().equals("up")){

                    Drawable d = getResources().getDrawable(R.mipmap.top_bottom);
                    d.setBounds(0,0,d.getIntrinsicWidth(), d.getIntrinsicHeight());
                    tv.setCompoundDrawables(null, null, d, null);
                    v.setTag("down");
                }else{
                    Drawable d = getResources().getDrawable(R.mipmap.top_bottom);
                    d.setBounds(0,0,d.getIntrinsicWidth(), d.getIntrinsicHeight());
                    tv.setCompoundDrawables(null, null, d, null);
                    v.setTag("up");

                }
                EventBus.getDefault().post("refresh");
            }
        });
        TextView time = (TextView) tabLayout.getTabAt(2).findViewById(R.id.id_tab_txt);
        time.setCompoundDrawablePadding(drawablePadding);
        time.setCompoundDrawables(null, null, d, null);
        time.setTag("up");
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=(TextView)v;
                //Toast.makeText(IntegralMallAct.this,"clicked",Toast.LENGTH_LONG).show();
                viewPager.setCurrentItem(2);
                if (v.getTag().equals("up")){
                    Drawable d = getResources().getDrawable(R.mipmap.top_bottom);
                    d.setBounds(0,0,d.getIntrinsicWidth(), d.getIntrinsicHeight());
                    tv.setCompoundDrawables(null, null, d, null);
                    v.setTag("down");

                }else{
                    Drawable d = getResources().getDrawable(R.mipmap.top_bottom);
                    d.setBounds(0,0,d.getIntrinsicWidth(), d.getIntrinsicHeight());
                    tv.setCompoundDrawables(null, null, d, null);
                    v.setTag("up");

                }
                EventBus.getDefault().post("refresh");
            }
        });
    }
    public void setIntegral(int integral){
        //integralTv.setText(String.valueOf(integral));
        TextView textView=(TextView) findViewById(R.id.integral);
        textView.setText(String.valueOf(integral));
    }
}
