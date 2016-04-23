package com.cn.commans;

import android.provider.SyncStateContract;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nurmemet on 2016/4/21.
 */
public class DateUtil {

    public static  String convertTime(long timeInMill){
        Calendar calendar=Calendar.getInstance();
        calendar.setTimeInMillis(timeInMill);
        SimpleDateFormat formatter=new SimpleDateFormat(Constants.DATE_FORMAT_3);
        String t=formatter.format(calendar.getTime());
        return t;
    }
}
