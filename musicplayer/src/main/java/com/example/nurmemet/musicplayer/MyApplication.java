package com.example.nurmemet.musicplayer;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by nurmemet on 2016/3/14.
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this.getApplicationContext());
    }
}
