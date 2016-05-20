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

//    public static String getHour(long milliSecond) {
//        int hour = (int) milliSecond / (1000 * 60 * 60*60);
//
//        return String.format("%02d",hour);
//    }
//
//    public static String getMinute(long milliSecond) {
//        int minute = (int) ((milliSecond / (1000 * 60 * 60))%60);
//        return String.format("%02d",minute);
//    }
//
//    public static String getSecond(long millisecond) {
//
//        int second = (int) ((millisecond / 1000) % 60);
//        return String.format("%02d",second);
//
//    }

    public static AuctionTime getAuctionTime(long delta) {
        AuctionTime auctionTime = new AuctionTime();
        auctionTime.hour = (int) (delta / (1000 * 60 * 60));
        auctionTime.minute = (int) (delta / (1000 * 60)) - auctionTime.hour * 60;
        auctionTime.second = (int) (delta / (1000)) - auctionTime.hour * 60 * 60 - auctionTime.minute * 60;

        auctionTime.day = auctionTime.hour / 24;
        auctionTime.hour = auctionTime.hour - auctionTime.day * 24;

        if (auctionTime.day != 0) {
            auctionTime.dayStr = String.format("%02d天", auctionTime.day);
        }
        auctionTime.hourStr = String.format("%02d", auctionTime.hour);
        auctionTime.minuteStr = String.format("%02d", auctionTime.minute);
        auctionTime.secondStr = String.format("%02d", auctionTime.second);
        return auctionTime;
    }


    static class AuctionTime {
        public int day;
        public String dayStr;
        public int hour;
        public String hourStr;
        public int minute;
        public String minuteStr;
        public int second;
        public String secondStr;

    }
}
