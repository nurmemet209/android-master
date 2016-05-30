package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.cn.adapter.BaseSelectableListAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.adapter.InvoiceCommonListAdapter;
import com.cn.commans.ActivitySwitcher;
import com.cn.commans.NetUtil;
import com.cn.entity.ResInvoiceInfo;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 5/30/2016.
 */
public class InvoiceCommonListFrag extends BaseFrag {

    @Bind(R.id.recycle_view)
    RecyclerView recycleView;

    private List<ResInvoiceInfo> list;

    private InvoiceCommonListAdapter adapter;
    ResInvoiceInfo selctedInvoiceInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        EventBus.getDefault().register(this);
        getIntentData();
        loadData();
        return mainView;
    }

    private void getIntentData() {
        selctedInvoiceInfo = (ResInvoiceInfo) getActivity().getIntent().getSerializableExtra("invoice_common");
    }


    private void loadData() {
        apiHandler.getInvoiceInformationList(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    list = apiHandler.JSONArray2List(NetUtil.getArrayData(response), ResInvoiceInfo.class);
                    if (list != null) {
                        bindData();
                    }
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }

    private void bindData() {
        if (adapter == null) {
            int index = list.indexOf(selctedInvoiceInfo);
            adapter = new InvoiceCommonListAdapter(getActivity(), list, index != -1 ? index : 0, new BaseSelectableListAdapter.OnSelectedListener() {
                @Override
                public void OnSelected(int position) {
                    ResInvoiceInfo resInvoiceInfo = list.get(position);
                    EventBus.getDefault().post(new EventBusEv("setSelectInvoice", resInvoiceInfo));
                    getActivity().finish();
                }
            });
            recycleView.addItemDecoration(new CustomItemDecoration(getActivity(), getResources().getDimensionPixelSize(R.dimen.main_big_divider_height)));
            recycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recycleView.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
        }

    }

    public static InvoiceCommonListFrag getInstance() {

        InvoiceCommonListFrag frag = new InvoiceCommonListFrag();
        return frag;
    }


    @Override
    protected int getLayoutResId() {
        return R.layout.frag_invoice_common_list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deleteInvoiceInformation(EventBusEv ev) {
        if (EventBusEv.is(ev, "deleteInvoice")) {

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.add_invoice_item)
    public void onClick() {
        ActivitySwitcher.toInvoiceCommonEditAct(getActivity(), null);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshList(EventBusEv ev) {
        if (EventBusEv.is(ev, "refreshInoiceList")) {
            loadData();
        }
    }
}
