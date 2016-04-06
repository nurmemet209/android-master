package com.cn.pppcar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.cn.widget.TimeLine;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by nurmemet on 2016/4/5.
 */
public class Logistic extends Activity {

    @Bind(R.id.time_line)
    protected TimeLine timeLine;

    @Bind(R.id.order_number)
    protected TextView orderNumber;

    @Bind(R.id.logistic_compay)
    protected TextView logisticCompany;

    private ArrayList<String> timeList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_logistic);
        ButterKnife.bind(this);

        timeList.add("2016年2月19日 上午1:31:47 快件离开【西安雁塔集散中心】,正发往 【杭州上城集散中心】");
        timeList.add("2016年2月19日 上午1:15:31 快件到达 【西安雁塔集散中心】");
        timeList.add("2016年2月17日 上午3:26:21 快件离开【乌鲁木齐集散中心】,正发往 【西安雁塔集散中心】");
        timeList.add("2016年2月16日 下午5:35:16 快件到达 【乌鲁木齐集散中心】");
        timeList.add("2016年2月16日 下午4:03:22 快件离开【乌鲁木齐天山区大湾营业点】,正发往 【乌鲁木齐集散中心】");
        timeList.add("2016年2月16日 上午12:12:36 顺丰速运 已收取快件");

        timeLine.addEvent(timeList);
    }
}
