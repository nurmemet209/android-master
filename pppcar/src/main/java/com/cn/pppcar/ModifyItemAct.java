package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by nurmemet on 2016/3/27.
 */
public class ModifyItemAct extends Activity {
    public final static String TITILE="title";
    public final static String HINT_TEXT="hint_text";
    /**
     * 标题
     */
    private String mTitle;
    /**
     * 提示语
     */
    private String mHint;
    protected TextView mTitleView;
    protected TextView mHintView;
    protected EditText mEidtItem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        readArguments(savedInstanceState);

    }
    private void readArguments(Bundle bundle){
        if (bundle!=null){
            mTitle=bundle.getString(TITILE,"");
            mHint=bundle.getString(HINT_TEXT,"");

        }
    }

    /**
     * 提交修改内容
     * @param view
     */
    public void saveItem(View view){

    }
}
