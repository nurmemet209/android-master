package com.cn.commans;

import android.provider.SyncStateContract;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by nurmemet on 2016/4/21.
 */
public class DateUtil {

    public static String convertTime(long timeInMill) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeInMill);
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_3);
        String t = formatter.format(calendar.getTime());
        return t;
    }

    public static String convertTime(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT_3);
        String t = formatter.format(date.getTime());
        return t;
    }

    public static String getHour(long milliSecond) {
        int hour = (int) milliSecond / (1000 * 60 * 60*60);

        return String.format("%02d",hour);
    }

    public static String getMinute(long milliSecond) {
        int minute = (int) ((milliSecond / (1000 * 60 * 60))%60);
        return String.format("%02d",minute);
    }

    public static String getSecond(long millisecond) {

        int second = (int) ((millisecond / 1000) % 60);
        return String.format("%02d",second);

    }
}
