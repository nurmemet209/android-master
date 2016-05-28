package com.cn.pppcar;

import android.os.Bundle;
import android.widget.EditText;

import com.android.volley.Response;
import com.cn.commans.NetUtil;
import com.cn.entity.Consignee;
import com.cn.localutils.EventBusEv;
import com.cn.pppcar.widget.SelectableLinearLayoutItem;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nurmemet on 2016/4/6.
 */
public class ReceiveAddressEditAct extends BaseAct {

    @Bind(R.id.receiver_name)
    EditText receiverName;
    @Bind(R.id.phone_num)
    EditText phoneNum;
    @Bind(R.id.tel_num)
    EditText telNum;
    @Bind(R.id.detailed_address)
    EditText detailedAddress;
    @Bind(R.id.is_default_address)
    SelectableLinearLayoutItem isDefaultAddress;

    /**
     * 1 新增，2 修改
     */
    private int type;

    private Consignee consignee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.act_receive_address_edit);
        ButterKnife.bind(this);
        bindData();
    }

    private void bindData() {
        if (consignee == null) {
            type = 1;
            consignee = new Consignee();
            return;
        }
        type = 2;
        receiverName.setText(consignee.getConsignee());
        phoneNum.setText(consignee.getMobileNumber());
        telNum.setText(consignee.getTelNumber());
        detailedAddress.setText(consignee.getAddress());
        isDefaultAddress.setSelected(consignee.getIsDefault());

    }


    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getEvent(EventBusEv eventBusEv) {
        if (eventBusEv != null && "consignee".equals(eventBusEv.getEvent())) {
            consignee = (Consignee) eventBusEv.getData();
            EventBus.getDefault().removeStickyEvent(eventBusEv);
        }
    }

    @OnClick(R.id.submit)
    public void onSumbit() {
        if (!formValidate()) {
            return;
        }
        packageData();
        showProgressDlg();
        apiHandler.addReceiveAddr(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                dismissProgressDlg();
                showToast(NetUtil.getMessage(response));
                if (NetUtil.isSucced(response)) {
                    EventBus.getDefault().post(new EventBusEv("refresh", null));
                    finish();
                }
            }
        }, consignee, type, this);
    }

    private void packageData() {

        consignee.setAddress(detailedAddress.getText().toString());
        consignee.setConsignee(receiverName.getText().toString());
        consignee.setIsDefault(isDefaultAddress.isSelected());
        consignee.setMobileNumber(phoneNum.getText().toString());
        consignee.setTelNumber(telNum.getText().toString());

    }

    private boolean formValidate() {

        return true;
    }

}
