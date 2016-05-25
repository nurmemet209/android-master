package com.cn.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.android.volley.Response;
import com.cn.adapter.CarFragAdapter;
import com.cn.adapter.CustomItemDecoration;
import com.cn.commans.NetUtil;
import com.cn.component.OnListItemWidgetClickedListener;
import com.cn.entity.CartBean;
import com.cn.entity.CartResBean;
import com.cn.pppcar.R;
import com.cn.pppcar.widget.NumEditLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2015/12/19.
 */
public class CartFrag extends BaseFrag implements OnListItemWidgetClickedListener {

    public final static int COMMOND_CHECK_STATE_CHANGED = 1;
    public final static int COMMOND_NUM_CHANGED = 2;

    @Bind(R.id.recycle_view)
    protected RecyclerView recyclerView;
    @Bind(R.id.view_flipper)
    protected ViewFlipper viewFlipper;
    @Bind(R.id.select_all)
    protected CheckBox mCheckBox;
    @Bind(R.id.select_all_)
    protected CheckBox mCheckBox4Edit;
    private CarFragAdapter adapter;
    private CartResBean cartResBean;


    private boolean isEditMode = false;


    public static CartFrag getInstance() {
        CartFrag frag = new CartFrag();
        return frag;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, mainView);
        loadData();
        return mainView;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.frag_cart;
    }

    private void loadData() {
        apiHandler.getCartPage(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    cartResBean = apiHandler.toObject(NetUtil.getData(response), CartResBean.class);
                    if (cartResBean != null) {
                        adapter = new CarFragAdapter(cartResBean.getContent(), getActivity(), CartFrag.this);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        recyclerView.addItemDecoration(new CustomItemDecoration(getActivity(), getContext().getResources().getDimensionPixelSize(R.dimen.main_big_divider_height)));
                        recyclerView.setAdapter(adapter);
                        setIsAllSelected();
                    } else {

                    }


                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        });


    }

    private void setIsAllSelected() {
        boolean isAllChecked = true;
        for (CartBean bean : cartResBean.getContent()) {
            if (!bean.getChecked()) {
                isAllChecked = false;
                break;
            }
        }
        mCheckBox.setChecked(isAllChecked);
    }

    @OnClick(R.id.edit)
    protected void editCart(View view) {

        TextView edit = (TextView) view;
        if (edit.getText().equals(getString(R.string.edit))) {
            viewFlipper.showNext();
            edit.setText(R.string.complete);
            isEditMode = true;
            adapter.getEditCartList().clear();
            adapter.setEditMode(true);
            mCheckBox4Edit.setChecked(false);

        } else {
            viewFlipper.showPrevious();
            edit.setText(R.string.edit);
            isEditMode = false;
            adapter.setEditMode(false);
        }
        adapter.notifyDataSetChanged();

    }


    @OnClick({R.id.select_all, R.id.select_all_})
    public void checkAll(View view) {
        CheckBox checkBox = (CheckBox) view;
        boolean isChecked = checkBox.isChecked();
        if (!isEditMode) {
            for (CartBean bean : cartResBean.getContent()) {
                bean.setChecked(isChecked);
            }
            cartUpdate(0, -1, isChecked, null);
            adapter.notifyDataSetChanged();
        } else {
            if (isChecked) {
                adapter.getEditCartList().clear();
                adapter.getEditCartList().addAll(cartResBean.getContent());
            } else {
                adapter.getEditCartList().clear();
            }
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.delete_from_cart)
    public void deleteFromCart(View view) {
        builderEx.clear();
        for (int i = 0; i < adapter.getEditCartList().size(); i++) {
            long id = adapter.getEditCartList().get(i).getId();
            builderEx.append(id).append(",");
        }
        builderEx.delete(builderEx.length() - 1, builderEx.length());
        apiHandler.delFromCart(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    showToast(NetUtil.getMessage(response));
                    for (int i = 0; i < adapter.getEditCartList().size(); i++) {
                        int position = cartResBean.getContent().indexOf(adapter.getEditCartList().get(i));
                        cartResBean.getContent().remove(position);
                        adapter.notifyItemRemoved(position);
                    }
                    adapter.getEditCartList().clear();
                } else {
                    showToast(NetUtil.getMessage(response));
                }
            }
        }, builderEx.toString());
    }


    @Override
    public void OnItemClicke(int commond, RecyclerView.ViewHolder holder, Object extra) {

        int position = holder.getAdapterPosition();
        CartBean bean = cartResBean.getContent().get(position);
        //check state changed
        if (commond == COMMOND_CHECK_STATE_CHANGED) {

            boolean isChecked = (boolean) extra;
            cartUpdate(bean.getId(), bean.getNumber(), isChecked, holder);
            //num changed
        } else if (commond == COMMOND_NUM_CHANGED) {
            int num = (int) extra;
            cartUpdate(bean.getId(), num, bean.getChecked(), holder);
        }
    }


    private void cartUpdate(long cartId, int num, final boolean isChecked, final RecyclerView.ViewHolder holder) {
        apiHandler.updateCart(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if (NetUtil.isSucced(response)) {
                    if (holder != null) {
                        adapter.setCheckedState(holder, isChecked);
                    }
                }
                showToast(NetUtil.getMessage(response));
            }
        }, String.valueOf(cartId), num, isChecked);
    }

    @OnClick(R.id.move_to_collect)
    public  void add2Collect(View view){

        if (!adapter.getEditCartList().isEmpty()){

            builderEx.clear();
            for (int i = 0; i < adapter.getEditCartList().size(); i++) {
                long id = adapter.getEditCartList().get(i).getId();
                builderEx.append(id).append(",");
            }
            builderEx.delete(builderEx.length() - 1, builderEx.length());
            apiHandler.move2Collect(new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (NetUtil.isSucced(response)) {
                        showToast(NetUtil.getMessage(response));
                        for (int i = 0; i < adapter.getEditCartList().size(); i++) {
                            int position = cartResBean.getContent().indexOf(adapter.getEditCartList().get(i));
                            cartResBean.getContent().remove(position);
                            adapter.notifyItemRemoved(position);
                        }
                        adapter.getEditCartList().clear();
                    } else {
                        showToast(NetUtil.getMessage(response));
                    }
                }
            }, builderEx.toString());
        }else{
            showToast("至少选择一个项目");
        }

    }
}
