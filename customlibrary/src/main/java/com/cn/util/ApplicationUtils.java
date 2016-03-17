package com.cn.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;

/**
 * Created by erqal on 2015/10/18.
 */
public class ApplicationUtils {
    public static String getCacheSize(Context mContext) {

        long fileSize = 0;
        String cacheSize = "0KB";
        try {
            // File filesDir = mContext.getFilesDir();
            File cacheDir = mContext.getCacheDir();

            // fileSize += FileUtils.getDirSize(filesDir);
            fileSize += FileUtils.getDirSize(cacheDir);
            // 2.2版本才有将应用缓存转移到sd卡的功能
            if (
                    isMethodsCompat(android.os.Build.VERSION_CODES.FROYO)) {
                File externalCacheDir = mContext.getExternalCacheDir();
                fileSize += FileUtils.getDirSize(externalCacheDir);
            }
            if (fileSize > 0)
                return cacheSize = FileUtils.formatFileSize(fileSize);
        } catch (Exception e) {
        }
        return cacheSize;
    }

    public static boolean isMethodsCompat(int VersionCode) {
        int currentVersion = android.os.Build.VERSION.SDK_INT;
        return currentVersion >= VersionCode;
    }


    public static boolean checkDataConnectionState(Context context, boolean defaultValue) {

        try {
            ConnectivityManager cwjManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = cwjManager.getActiveNetworkInfo();
            if (info != null && info.isAvailable())
                return true;
        } catch (Exception e) {
            return defaultValue;
        }
        return false;
    }
}
