package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;

import com.cn.util.IOUtils;

/**
 * Created by nurmemet on 2016/3/29.
 */
public class TestAct extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IOUtils.copyAssetFile2ContextDir(this.getApplicationContext(),"place.db","databases");
        setContentView(R.layout.act_test);
    }
}
