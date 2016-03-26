package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;

import com.cn.fragment.BaseStateFragment;
import com.cn.fragment.StateOneFrag;
import com.cn.util.IOUtils;

/**
 * Created by nurmemet on 2016/3/19.
 */
public class PlaceSelectAct extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_select);
        IOUtils.copyAssetFile2ContextDir(this.getApplicationContext(),"place.db","databases");
        getFragmentManager().beginTransaction().add(R.id.container,StateOneFrag.getInstance(null)).commit();
    }
}
