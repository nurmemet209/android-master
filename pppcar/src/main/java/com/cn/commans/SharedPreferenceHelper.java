package com.cn.commans;

import android.content.Context;
import android.content.SharedPreferences;

import com.cn.entity.AppUserInfo;

/**
 * Created by nurmemet on 2016/5/24.
 */
public class SharedPreferenceHelper {

    private final static String NAME = "pppcar_";

    private Context mContext;

    public SharedPreferenceHelper(Context mContext) {
        this.mContext = mContext;
    }

    public void saveToken(AppUserInfo userInfo) {
        SharedPreferences preferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong("userId", userInfo.getUserId());
        editor.putInt("userType", userInfo.getUserType());
        editor.putString("appToken", userInfo.getAppToken());
        editor.commit();

    }

    public AppUserInfo readToken() {
        AppUserInfo userInfo = new AppUserInfo();
        SharedPreferences preferences = mContext.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        long userId = preferences.getLong("userId", -1);
        int userType = preferences.getInt("userType", -1);
        String appToken = preferences.getString("appToken", null);
        if (userId != -1 & userType != -1 & appToken != null) {
            userInfo.setUserId(userId);
            userInfo.setAppToken(appToken);
            userInfo.setUserType(userType);
            return userInfo;
        }
        return null;
    }


}
