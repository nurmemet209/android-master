package com.example.nurmemet.musicplayer;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;


import butterknife.Bind;
import butterknife.ButterKnife;

public class WelcomeActivity extends AppCompatActivity {
    private Handler mHandler=new Handler();

    @Bind(R.id.advertize)
    public SimpleDraweeView advertizeImg;

    @Bind(R.id.welcome)
    ImageView mWelcomeImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        ButterKnife.bind(this);

        advertizeImg.setImageURI(Uri.parse("http://c.hiphotos.bdimg.com/imgad/pic/item/00e93901213fb80ec98291ed31d12f2eb93894bd.jpg"));
        delaySwitch();
    }


    private void delaySwitch(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it=new Intent(WelcomeActivity.this,MainActivity.class);
                WelcomeActivity.this.startActivity(it);

                WelcomeActivity.this.overridePendingTransition(R.anim.activity_fade_in,R.anim.activity_fade_out);
                WelcomeActivity.this.finish();
            }
        },2000);

    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
