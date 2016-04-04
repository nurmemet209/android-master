package com.cn.pppcar;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import okhttp3.OkHttpClient;

/**
 * Created by nurmemet on 2016/3/14.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        ImagePipelineConfig frescoConfig = OkHttpImagePipelineConfigFactory
//                .newBuilder(this, new OkHttpClient())
//                .build();
        Fresco.initialize(this.getApplicationContext());
    }
}
