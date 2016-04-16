package com.cn.pppcar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.SearchClassificationViewPagerAdapter;
import com.cn.adapter.SearchSeggestAdapter;
import com.cn.util.UIHelper;
import com.cn.viewpager.CustomViewPager;
import com.lhh.apst.library.AdvancedPagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/7.
 */
public class SearchAct extends BaseAct {

    final static  int SEARCH_STATE=0;
    final static int SEARCH_ERROR_STATE=1;
    final static int SEARCH_RESULT_STATE=2;
    final static int SEARCH_SUGGEST_STATE=3;

    @Bind(R.id.view_pager)
    protected CustomViewPager viewPager;
    @Bind(R.id.tab_container)
    protected AdvancedPagerSlidingTabStrip tabLayout;
    @Bind(R.id.view_flipper)
    protected ViewFlipper viewFlipper;
    //搜索提示
    @Bind(R.id.recycle_view)
    protected RecyclerView suggestRecyclerView;
    private SearchSeggestAdapter searchSeggestAdapter;

    @Bind(R.id.search_edit_text)
    protected EditText searchEditText;

    private SearchClassificationViewPagerAdapter adapter;

    private int state=SEARCH_STATE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_search);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        adapter = new SearchClassificationViewPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
        searchSeggestAdapter = new SearchSeggestAdapter(this, null, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TextView tv=(TextView)v;
                searchEditText.setText(((TextView) v).getText());
                viewFlipper.setDisplayedChild(SEARCH_RESULT_STATE);
            }
        });
        suggestRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        suggestRecyclerView.addItemDecoration(new CustomItemDecoration(this, UIHelper.dip2px(this, 1)));
        suggestRecyclerView.setAdapter(searchSeggestAdapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (state!=SEARCH_SUGGEST_STATE){
                    viewFlipper.setDisplayedChild(SEARCH_SUGGEST_STATE);
                }

            }
        });
    }


    public void search(View view){
//        if (state==SEARCH_STATE)
//        {
//            viewFlipper.setDisplayedChild(2);
//
//        }

    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(actFinishAnimInResId,actFinishAnimOutResId);
    }

}


