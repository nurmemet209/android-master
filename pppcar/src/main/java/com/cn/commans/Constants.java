package com.cn.commans;

import java.net.PortUnreachableException;

/**
 * Created by nurmemet on 2016/3/28.
 */
public interface Constants {
    /**
     * 打开相册
     */
    public static final int REQUEST_OPEN_GALARY_FOR_HEAD_PORTRAIT=1;
    public static final int REQUEST_OPEN_CAMERA_FOR_HEAD_PORTRAIT=2;
    public static final int REQUEST_OPEN_GALARY_FOR_BUSINESS_LICENSE=3;
    public static final int REQUEST_OPEN_CAMERA_FOR_BUSINESS_LICENSE=4;
    public static final int REQUEST_OPEN_GALARY_FOR_SHOP_IMG=5;
    public static final int REQUEST_OPEN_CAMERA_FOR_SHOP_IMG=6;

    public static final String DATE_FORMAT_1="yy/MM/dd HH:mm:ss";
    public static final String DATE_FORMAT_2="yy/MM/dd HH:mm:ss pm";
    public static final String DATE_FORMAT_3="yy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_4="yy-MM-dd HH:mm:ss am";

    public static final String LIST_IMG_SIZE="/300/225/80";

    //正在拍卖
    public static final  int AUCTION_STATE_ING=50011;
    //未开始
    public static final  int AUCTION_STATE_WAIT=50010;
    //拍卖结束
    public static final  int AUCTION_STATE_OVER=50012;
}
