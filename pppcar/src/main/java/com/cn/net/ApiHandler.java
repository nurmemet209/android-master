package com.cn.net;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.cn.entity.CitySelectPage;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nurmemet on 2015/8/29.
 */
public class ApiHandler implements CookieHandler, Response.ErrorListener {


    protected Context mContext;

    private static ApiHelper apiHelper;
    public final static String HOST = "http://192.168.1.101:8080/musicplayer";
    public final static String API_STRING_PRE_REMOTE = "http://job.erqal.com/api.php?m=";
    private static int appVersion;
    private final static String LG_UG = "ug";
    private final static String LG_ZH = "zh";

    private final static String COOKIE_PREFIX = "ErCookie_";

    private static ApiHandler handler;

    private ApiHandler(Context context) {
        this.mContext = context;
        apiHelper=new ApiHelper(context);
    }

    public static ApiHandler getInstance(Context context) {
        if (handler == null) {
            handler = new ApiHandler(context);

        }
        return handler;
    }

    @Override
    public Map<String, String> addCookie() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("User-Agent", "android-agent");
        return headers;
    }

    @Override
    public void saveCookie(Map<String, String> headers) {

    }


    public static void setAppVersion(int appVer) {
        appVersion = appVer;
    }


    protected StringBuilder getRootApi() {
        StringBuilder builder = new StringBuilder();
        builder.append(HOST);
        return builder;
    }


    public void login(Response.Listener listener, final String userName, final String pasword) {
        String url = getRootApi().append("/user&a=login").toString();
        StringRequest request = new StringRequest(Request.Method.POST, url, listener, null) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = getDefaultPosData();
                params.put("username", userName);
                params.put("password", pasword);
                return params;
            }

        };
        addToRequestQueue(request);
    }

    public void getAllCityList(Response.Listener<CitySelectPage> listener,Response.ErrorListener errorListener) {
        String url = getRootApi().append("/citySelectPage/getAllCities").toString();
        GsonRequest request = new GsonRequest<>(url, CitySelectPage.class, null, listener, errorListener);
        addToRequestQueue(request);
    }

    public void getAllCity(Response.Listener<String> listener,Response.ErrorListener errorListener) {
        String url = getRootApi().append("/citySelectPage/getAllCities").toString();
        StringRequest request = new StringRequest(url, listener, errorListener);
        addToRequestQueue(request);
    }


    protected void addToRequestQueue(Request req) {
        JsonRequest.setCookieHandler(this);
        apiHelper.addToRequestQueue(req);
    }

    protected HashMap<String, String> getDefaultPosData() {
        HashMap params = new HashMap();
        {
            params.put("lg", LG_ZH);
        }
        params.put("version", Integer.toString(appVersion));
        params.put("os", "android");
        return params;
    }


    @Override
    public void onErrorResponse(VolleyError error) {

        System.out.print(error.getMessage());
    }


    private static <T> T toObject(JSONObject str, Class<T> claxx) {

        if (str != null) {
            Gson gson = new Gson();
            try {
                return gson.fromJson(str.toString(), claxx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    private static <T> T toObject(String str, Class<T> claxx) {

        if (str != null) {
            Gson gson = new Gson();
            try {
                return gson.fromJson(str.toString(), claxx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String toJson(Object obj) {
        Gson gson = new Gson();
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static interface Listener<T> {
        /**
         * Called when a response is received.
         */
        public void onResponse(T response);
    }


}
