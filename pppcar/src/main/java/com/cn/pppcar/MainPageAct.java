package com.cn.pppcar;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.cn.pppcar.com.cn.adapter.MainPageAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2015/12/17.
 */
public class MainPageAct extends FragmentActivity {
    @Bind(R.id.vp_view)
    protected ViewPager mViewPager;
    @Bind(R.id.bottom_tab)
    protected RadioGroup bottomTab;
    @Bind(R.id.home)
    protected RadioButton homeBtn;
    @Bind(R.id.car_brand)
    protected RadioButton carBrand;
    @Bind(R.id.parts_brand)
    protected RadioButton partsBrand;
    @Bind(R.id.me)
    protected RadioButton meBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setUpViewPager();


       // UmengUpdateAgent.update(this);



    }






    private void setUpViewPager() {
        MainPageAdapter mAdapter = new MainPageAdapter(this.getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);//给ViewPager设置适配器
        mViewPager.setOffscreenPageLimit(mAdapter.getCount());

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        homeBtn.setChecked(true);
                        break;
                    case 1:
                        carBrand.setChecked(true);
                        break;
                    case 2:
                        partsBrand.setChecked(true);
                        break;
                    case 3:
                        meBtn.setChecked(true);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        bottomTab.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.home:
                        mViewPager.setCurrentItem(0, false);
                        break;
                    case R.id.car_brand:
                        mViewPager.setCurrentItem(1, false);
                        break;
                    case R.id.parts_brand:
                        mViewPager.setCurrentItem(2, false);
                        break;
                    case R.id.me:
                        mViewPager.setCurrentItem(3, false);
                        break;
                    default:
                        break;
                }

            }
        });
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

}
