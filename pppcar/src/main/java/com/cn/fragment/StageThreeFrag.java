package com.cn.fragment;

import android.os.Bundle;
import android.view.View;

import com.cn.database.DBservice;
import com.cn.entity.Area;
import com.cn.entity.BaseEntity;

import java.util.ArrayList;

/**
 * Created by nurmemet on 2016/3/25.
 */
public class StageThreeFrag extends BaseStateFragment {


    public static BaseStateFragment getInstance(BaseEntity selectedEntity){
        StateOneFrag frag=new StateOneFrag();
        if (selectedEntity!=null){
            Bundle bundle=new Bundle();
            bundle.putSerializable(SELECTED,selectedEntity);
        }
        return frag;
    }

    @Override
    protected ArrayList<BaseEntity> pullData() {
        DBservice dBservice  = new DBservice(this.getActivity());
        ArrayList<BaseEntity> tempList= (ArrayList<BaseEntity>) dBservice.getAllProvince();
        return tempList;
    }

    @Override
    public void OnSel(BaseEntity selectedEntity) {

    }
}
