package com.cn.pppcar;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.cn.adapter.AuctionCenterAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.commans.NetUtil;
import com.cn.component.OnItemSelected;
import com.cn.entity.CollectAuctionResBean;
import com.cn.pppcar.widget.YearSelectDlg;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/4/17.
 */
public class AuctionAct extends BaseAct {


    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;
    @Bind(R.id.year)
    protected TextView mYear;

    private AuctionCenterAdapter adapter;
    private CollectAuctionResBean auctionPage;
    private LinearLayoutManager linearLayoutManager;
    private int selectedYear = -1;
    private Handler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_auction_list);
        ButterKnife.bind(this);
        init();

    }
    private void init(){
        mHandler=new Handler();
        loadData();
    }
    private void loadData() {

        apiHandler.getAuctionList(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    auctionPage = apiHandler.toObject(NetUtil.getData(response), CollectAuctionResBean.class);
                    if (adapter == null) {
                        adapter = new AuctionCenterAdapter(AuctionAct.this,recyclerView, auctionPage.getAuctionResBeans());
                        linearLayoutManager = new LinearLayoutManager(AuctionAct.this);
                        recyclerView.setLayoutManager(linearLayoutManager);
                        CustomItemDecoration decoration = new CustomItemDecoration(AuctionAct.this, getResources().getDimensionPixelSize(R.dimen.main_big_divider_height));
                        recyclerView.addItemDecoration(decoration);
                        recyclerView.setAdapter(adapter);
                        update();
                    } else {
                        adapter.setList(auctionPage.getAuctionResBeans());
                        adapter.notifyDataSetChanged();
                    }
                    mYear.setText(getSelectedYear());
                } else {
                    showToast(NetUtil.getError(response));
                }
            }
        }, selectedYear == -1 ? "" : String.valueOf(selectedYear));


    }

    @OnClick(R.id.year_select)
    public void selectYear(View view) {

        YearSelectDlg dlg = new YearSelectDlg(this, new OnItemSelected() {
            @Override
            public void onItemSelected(View view, int position, Object data) {

                String year = (String) data;
                if ("全部".equals(year)) {
                    selectedYear = -1;
                } else {
                    selectedYear = Integer.valueOf(year);
                }

            }
        }, selectedYear);
        dlg.show();
    }

    private String getSelectedYear() {
        if (selectedYear == -1) {
            return "全部";
        } else {
            return String.valueOf(selectedYear);
        }
    }

    private void update() {

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int start = linearLayoutManager.findFirstVisibleItemPosition();
                int end = linearLayoutManager.findLastVisibleItemPosition();
                adapter.update(start, end);

                update();
            }
        },1000);
    }


}
