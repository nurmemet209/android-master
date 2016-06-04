package com.cn.pppcar;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Response;
import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.SearchClassificationViewPagerAdapter;
import com.cn.adapter.SearchHistoryAdapter;
import com.cn.adapter.SearchSeggestAdapter;
import com.cn.commans.NetUtil;
import com.cn.entity.SearchPage;
import com.cn.fragment.SearchListFrag;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.widget.CustomTabLayout;
import com.cn.pppcar.widget.TagGroupLayout;
import com.cn.util.Util;
import com.cn.viewpager.CustomViewPager;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/4/7.
 */
public class SearchAct extends BaseAct {

    final static int SEARCH_STATE = 0;
    final static int SEARCH_ERROR_STATE = 1;
    final static int SEARCH_RESULT_STATE = 2;
    final static int SEARCH_SUGGEST_STATE = 3;

    @Bind(R.id.view_pager)
    protected CustomViewPager viewPager;
    @Bind(R.id.tab_container)
    protected CustomTabLayout tabLayout;
    @Bind(R.id.view_flipper)
    protected ViewFlipper viewFlipper;
    //搜索提示
    @Bind(R.id.recycle_view)
    protected RecyclerView suggestRecyclerView;
    private SearchSeggestAdapter searchSeggestAdapter;

    @Bind(R.id.history_search_recycle_view)
    protected RecyclerView historySearch;
    private SearchHistoryAdapter searchHistoryAdapter;

    @Bind(R.id.search_edit_text)
    protected EditText searchEditText;

    @Bind(R.id.hot_search)
    protected TagGroupLayout hotSearch;

    private SearchClassificationViewPagerAdapter adapter;
    private int state = SEARCH_STATE;
    private SearchPage searchPage;


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
        viewPager.setOffscreenPageLimit(adapter.getCount());
        viewPager.setCanScroll(true);
        tabLayout.setDrawablePadding(getResources().getDimensionPixelSize(R.dimen.padding_smallest_));
        tabLayout.setViewPager(viewPager, new CustomTabLayout.BindView() {
            @Override
            public void OnBindView(TextView tv, ImageView img, int position) {

                //价格
                if (position == 2) {
                    img.setTag("down");
                    img.setBackgroundResource(R.mipmap.bottom_selected);

                }
                tv.setTextColor(ContextCompat.getColorStateList(SearchAct.this,R.color.main_text_color_to_main_red_sl));

            }
        }, new CustomTabLayout.CustomOnItemClick() {
            @Override
            public void OnItemClicked(TextView tv, ImageView img, int newPosition, int oldPosition, boolean state) {
                if (!state&&newPosition==2) {
                    String tag = (String) img.getTag();
                    if ("down".equals(tag)) {
                        img.setBackgroundResource(R.mipmap.to_selected);
                        img.setTag("up");
                    } else {
                        img.setBackgroundResource(R.mipmap.bottom_selected);
                        img.setTag("down");
                    }
                    //价格
                    SearchListFrag.keyWord = searchEditText.getText().toString();
                    Map<String,String> map=new HashMap<>();
                    map.put("sortType",tag);
                    map.put("fragType","3");
                    EventBusEv ev = new EventBusEv("sort", map);
                    EventBus.getDefault().post(ev);

                }
            }
        });
        loadData();
        searchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    search();
                    return true;
                }
                return false;
            }
        });
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count >= 2 && !searchEditText.getText().toString().equals(""))
                    apiHandler.getSearchPage(new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            if (NetUtil.isSucced(response)) {
                                SearchPage s = apiHandler.toObject(NetUtil.getData(response), SearchPage.class);
                                if (s != null && Util.isNoteEmpty(s.getPrompt())) {
                                    searchSeggestAdapter.setList(s.getPrompt());
                                    searchSeggestAdapter.notifyDataSetChanged();
                                }

                            }
                        }
                    }, searchEditText.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (state != SEARCH_SUGGEST_STATE) {
                    viewFlipper.setDisplayedChild(SEARCH_SUGGEST_STATE);
                }


            }
        });

    }

    private void loadData() {
        apiHandler.getSearchPage(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    searchPage = apiHandler.toObject(NetUtil.getData(response), SearchPage.class);
                    bindData();

                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, "");
    }

    private void bindData() {
        //搜索提示
        searchSeggestAdapter = new SearchSeggestAdapter(this, searchPage.getPrompt(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String keyWord = ((TextView) v).getText().toString();
                searchEditText.setText(keyWord);
                search();

            }
        });
        suggestRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        suggestRecyclerView.addItemDecoration(new CustomItemDecoration(this, getResources().getDimensionPixelSize(R.dimen.divider_)));
        suggestRecyclerView.setAdapter(searchSeggestAdapter);

        //历史搜索
        searchHistoryAdapter = new SearchHistoryAdapter(this, searchPage.getHistory());
        historySearch.setLayoutManager(new LinearLayoutManager(this));
        historySearch.addItemDecoration(new CustomItemDecoration(this, getResources().getDimensionPixelSize(R.dimen.divider_)));
        historySearch.setAdapter(searchHistoryAdapter);

        //热门搜索
        hotSearch.setPadding(10, 4);
        hotSearch.setCheckable(false);
        hotSearch.setOnItemChecked(new TagGroupLayout.OnItemChecked() {
            @Override
            public void onItemClick(int position, Object data) {

                String keyWord = (String) data;
                searchEditText.setText(keyWord);
                search();
            }
        });
        hotSearch.setTags(getHotSearch(), new TagGroupLayout.BindProperty() {
            @Override
            public void OnBindProperty(TextView view) {
                view.setTextColor(ContextCompat.getColorStateList(SearchAct.this, R.color.main_text_color_to_white));
                view.setBackground(ContextCompat.getDrawable(SearchAct.this, R.drawable.circle_stroke_gray_to_fill_gray_sl));
            }
        });


    }

//    private void setDrawables() {
//
//        Drawable d = getResources().getDrawable(R.mipmap.top_bottom);
//        d.setBounds(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
//        int drawablePadding = getResources().getDimensionPixelOffset(R.dimen.padding_smallest_);
//        TextView time = (TextView) tabLayout.getTabAt(2).findViewById(R.id.id_tab_txt);
//        time.setCompoundDrawablePadding(drawablePadding);
//        time.setCompoundDrawables(null, null, d, null);
//        time.setTag("up");
//        priceSortView = time;
//        time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                TextView tv = (TextView) v;
//                //Toast.makeText(IntegralMallAct.this,"clicked",Toast.LENGTH_LONG).show();
//                viewPager.setCurrentItem(2);
//
//
//
//            }
//        });
//        View mostNew = tabLayout.getTabAt(1);
//        mostNew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBusEv ev = new EventBusEv("sort", "new");
//                EventBus.getDefault().post(ev);
//
//            }
//        });
//        View universal = tabLayout.getTabAt(0);
//        universal.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EventBusEv ev = new EventBusEv("sort", "universal");
//                EventBus.getDefault().post(ev);
//
//            }
//        });
//    }

//    public void search(View view) {
////        if (state==SEARCH_STATE)
////        {
////            viewFlipper.setDisplayedChild(2);
////
////        }
//
//    }

//
//    @Override
//    public void finish() {
//        super.finish();
//        overridePendingTransition(actFinishAnimInResId,actFinishAnimOutResId);
//    }


    private List<String> getHotSearch() {

        List<String> hotSearch = new ArrayList<>();
        hotSearch.add("轮毂");
        hotSearch.add("弹簧");
        hotSearch.add("避震");
        hotSearch.add("轮胎");
        hotSearch.add("机油");
        hotSearch.add("奥迪");
        hotSearch.add("沃尔沃");
        hotSearch.add("JBOM排气头段");
        return hotSearch;
    }

//
//    private void search() {
//        SearchListFrag.keyWord = searchEditText.getText().toString();
//        EventBusEv ev = new EventBusEv("search", null);
//        EventBus.getDefault().post(ev);
//    }

    @OnClick(R.id.search_btn)
    public void searchButtonClicked(View view) {

        search();
    }

    private void search() {
        SearchListFrag.keyWord = searchEditText.getText().toString();
        EventBusEv ev = new EventBusEv("search", null);
        EventBus.getDefault().post(ev);
        if (state != SEARCH_RESULT_STATE) {
            viewFlipper.setDisplayedChild(SEARCH_RESULT_STATE);
        }
    }

}


