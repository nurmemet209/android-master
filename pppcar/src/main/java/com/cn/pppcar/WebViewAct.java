package com.cn.pppcar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.widget.TextView;

import butterknife.Bind;

/**
 * Created by nurmemet on 2016/4/25.
 */
public class WebViewAct extends BaseAct {

    @Bind(R.id.web_view)
    protected WebView webView;
    @Bind(R.id.title)
    protected TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {

        //title.setText("");

    }


}
