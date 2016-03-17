package com.cn.util;

import com.orhanobut.logger.Logger;

/**
 * Created by nurmemet on 2015/8/19.
 */
public class MyLogger {

    public final static String ERQAL_CAREER="erqal_career";

    public static void showLog(String str){
        if (str!=null){
            Logger.init(ERQAL_CAREER).setMethodCount(3);
            Logger.d(str);
        }


    }

    public static void showJson(String str){
        if (str!=null){
            Logger.init(ERQAL_CAREER).setMethodCount(5);
            Logger.json(str);
        }



    }

    public static  void showError(String error){
        if (error!=null){
            Logger.init(ERQAL_CAREER).setMethodCount(5);
            Logger.e(error);
        }
    }



}
