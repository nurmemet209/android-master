package com.cn.commans;

import java.util.Calendar;

/**
 * Created by nurmemet on 2016/5/19.
 */
public class AuctionHelper {

    long currentTime;

    public AuctionHelper() {
        Calendar calendar = Calendar.getInstance();
        currentTime = calendar.getTimeInMillis();
    }

    public long getDelta() {
        Calendar calendar = Calendar.getInstance();
        long detal = calendar.getTimeInMillis() - currentTime;
        return detal;
    }

}
