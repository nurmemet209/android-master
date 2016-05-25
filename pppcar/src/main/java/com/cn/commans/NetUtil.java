package com.cn.commans;

import com.cn.entity.ReturnBean;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nurmemet on 2016/4/29.
 */
public class NetUtil {
    public static boolean isSucced(JSONObject rt) {

        if (rt != null) {
            try {
                String msg = rt.getString("returnCode");
                if (msg != null && msg.equals("000000")) {
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String getMessage(JSONObject rt) {
        if (rt != null) {
            try {
                String msg = rt.getString("returnMsg");
                if (msg != null && !"".equals(msg)) {
                    return msg;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "网络错误";
    }

    public static String getData(JSONObject rt) {
        if (rt != null) {
            try {
                JSONObject data = rt.getJSONObject("data");
                if (data != null && !"".equals(data)) {
                    return data.toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String getArrayData(JSONObject rt) {
        if (rt != null) {
            try {
                JSONArray data = rt.getJSONArray("data");
                if (data != null && !"".equals(data)) {
                    return data.toString();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static JSONArray getJsonArray(JSONObject rt) {
        if (rt != null) {
            try {
                JSONArray data = rt.getJSONArray("data");
                if (data != null && !"".equals(data)) {
                    return data;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return new JSONArray();
    }


}
