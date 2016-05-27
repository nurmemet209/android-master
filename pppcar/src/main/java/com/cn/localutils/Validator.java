package com.cn.localutils;

/**
 * Created by nurmemet on 5/27/2016.
 */
public class Validator {

    public boolean isInvalidEmail(String str){
        if (str.contains("@")){
            return true;
        }
        return false;
    }
}
