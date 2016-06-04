package com.cn.pppcar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cn.adapter.IntegralDetailViewPagerAdapter;
import com.cn.adapter.IntegralMallViewPagerAdapter;
import com.cn.fragment.SearchListFrag;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.widget.CustomTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/27.
 */
public class IntegralMallAct extends BaseAct {


    protected IntegralMallViewPagerAdapter adapter;

//    @Bind(R.id.integral)
//    protected TextView integralTv;
    @Bind(R.id.tab_container)
    CustomTabLayout mTablayout;
    @Bind(R.id.view_pager)
    ViewPager viewPager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_integral_mall);
        ButterKnife.bind(this);
        init();
    }


    protected void init() {
        adapter = new IntegralMallViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(adapter.getCount());


        mTablayout.setDrawablePadding(getResources().getDimensionPixelSize(R.dimen.padding_smallest_));
        mTablayout.setViewPager(viewPager, new CustomTabLayout.BindView() {
            @Override
            public void OnBindView(TextView tv, ImageView img, int position) {

                //价格
                if (position == 1||position==2) {
                    img.setTag("down");
                    img.setBackgroundResource(R.mipmap.bottom_selected);

                }
                tv.setTextColor(ContextCompat.getColorStateList(IntegralMallAct.this,R.color.main_text_color_to_main_red_sl));


            }
        }, new CustomTabLayout.CustomOnItemClick() {
            @Override
            public void OnItemClicked(TextView tv, ImageView img, int newPosition, int oldPosition, boolean state) {
                if (!state&&(newPosition==2||newPosition==1)) {
                    String tag = (String) img.getTag();
                    if ("down".equals(tag)) {
                        img.setBackgroundResource(R.mipmap.to_selected);
                        img.setTag("up");
                    } else {
                        img.setBackgroundResource(R.mipmap.bottom_selected);
                        img.setTag("down");
                    }
                    Map<String,String> map=new HashMap<>();
                    map.put("sortType",tag);
                    map.put("fragType", String.valueOf(newPosition));
                    EventBusEv ev = new EventBusEv("integral_mall_sort", map);
                    EventBus.getDefault().post(ev);

                }
            }
        });

    }

    public void setIntegral(int integral){
        //integralTv.setText(String.valueOf(integral));
        TextView textView=(TextView) findViewById(R.id.integral);
        textView.setText(String.valueOf(integral));
    }
}
