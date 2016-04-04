package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by nurmemet on 2016/4/4.
 */
public class StagePaymentAct extends Activity {

    private int selectedPayStageId=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_stage_paymet);

    }


    public void selectPayStage(View view){
        if (selectedPayStageId!=-1){
            View selected=findViewById(selectedPayStageId);
            selected.setSelected(false);
        }
        View selected=view.findViewWithTag("selector");
        selectedPayStageId=selected.getId();
        selected.setSelected(true);
    }
}
