package com.cn.pppcar;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Response;
import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.ReceiveAddressListAdapter;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.Consignee;
import com.cn.localutils.EventBusEv;
import com.cn.util.Util;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 收货地址选择
 * Created by nurmemet on 2016/4/7.
 */
public class ReceiveAddressListAct extends BaseAct {

    private Consignee selectedConsignee;


    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;

    ReceiveAddressListAdapter adapter;

    private List<Consignee> mAddresList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments();
        setContentView(R.layout.act_receive_address_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        loadData();
    }

    private void getArguments() {
        selectedConsignee = (Consignee) getIntent().getSerializableExtra("Consignee");
    }


    private void loadData() {
        apiHandler.getReceriverAddressList(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    mAddresList = apiHandler.JSONArray2List(NetUtil.getArrayData(response), Consignee.class);
                    if (Util.isNoteEmpty(mAddresList)) {
                        bindData();
                    }
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        });
    }


    private void bindData() {
        if (adapter == null) {
            adapter = new ReceiveAddressListAdapter(this, mAddresList);
            setSelectedConsignee();
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.addItemDecoration(new CustomItemDecoration(this, getResources().getDimensionPixelSize(R.dimen.main_big_divider_height)));
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setList(mAddresList);
            adapter.notifyDataSetChanged();
        }

    }

    @OnClick(R.id.add_reveive_addr)
    public void addNewReceiveAddr(View view) {
        ActivitySwitcher.toReceiveAddressEditAct(this, null);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deleteConsignee(EventBusEv ev) {
        if (ev != null && "deleteConsignee".equals(ev.getEvent())) {
            RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) ev.getData();
            final int position = holder.getAdapterPosition();
            long id = mAddresList.get(position).getId();
            showProgressDlg();
            apiHandler.deleteReceriverAddress(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    dismissProgressDlg();
                    if (NetUtil.isSucced(response)) {
                        mAddresList.remove(position);
                        adapter.notifyItemRemoved(position);
                    } else {
                        showToast(NetUtil.getMessage(response));
                    }

                }
            }, String.valueOf(id), this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refresh(EventBusEv ev) {
        if (EventBusEv.is(ev, "refresh")) {
            loadData();
        }
    }


    @Override
    public void OnBack(View view) {
        int pos = adapter.getSelectedPosition();
        if (pos != -1) {
            Consignee consignee = mAddresList.get(pos);
            EventBus.getDefault().post(new EventBusEv("ReceiveAddressListAct_setConsignee", consignee));
        }
        super.OnBack(view);
    }


//    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
//    public void setSelectedAddress(EventBusEv ev) {
//        if (EventBusEv.is(ev, "PaySettlementAct_setConsignee")) {
//            selectedConsignee = (Consignee) ev.getData();
//            EventBus.getDefault().removeStickyEvent(ev);
//        }
//    }

    private void setSelectedConsignee() {
        if (selectedConsignee != null) {
            int index = mAddresList.indexOf(selectedConsignee);
            adapter.setSelectedPosition(index);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
