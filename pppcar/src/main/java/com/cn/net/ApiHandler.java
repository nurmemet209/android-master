package com.cn.net;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.cn.entity.CitySelectPage;
import com.cn.entity.CollectAuctionDetailResBean;
import com.cn.entity.ReturnBean;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nurmemet on 2015/8/29.
 */
public class ApiHandler implements CookieHandler, Response.ErrorListener {


    public static String code = "";
    public static String msg = "";
    protected Context mContext;

    private static ApiHelper apiHelper;
    // public final static String HOST = "http://pppcar.f3322.net:8084";

    public final static String HOST = "http://192.168.0.183:8080";
    public final static String API_STRING_PRE_REMOTE = "http://job.erqal.com/api.php?m=";
    private static int appVersion;
    private final static String LG_UG = "ug";
    private final static String LG_ZH = "zh";

    private final static String COOKIE_PREFIX = "ErCookie_";

    private static ApiHandler handler;

    private ApiHandler(Context context) {
        this.mContext = context;
        apiHelper = new ApiHelper(context);
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

    public void getAllCityList(Response.Listener<CitySelectPage> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/citySelectPage/getAllCities").toString();
        GsonRequest request = new GsonRequest<>(url, CitySelectPage.class, null, listener, errorListener);
        addToRequestQueue(request);
    }

    public void getAllCity(Response.Listener<String> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/musicplayer/citySelectPage/getAllCities").toString();
        StringRequest request = new StringRequest(url, listener, this);
        addToRequestQueue(request);
    }

    public void getAuctionDetail(String id, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/v1/auction/queryAuctionDetail?auctionId=").append(id).toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }

    /**
     * 分类页面，ClassifycationFrag
     *
     * @param listener
     * @param errorListener
     */
    public void getClassifycation(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/v1/product/classify/parents").toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }

    /**
     * 获取品牌
     *
     * @param listener
     * @param errorListener
     */
    public void getAllBrand(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/v1/brandCenter/allbrand").toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }


    public void getMyOrder(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/v1/account/order/list").toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }

    public void getIntegralProduct(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/v1/integral/queryIntegralProductall").toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }

    public void getIntegralProductDetail(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/v1/integral/queryIntegralProductDetailById?proId=1").toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }
    public void getProductDetail(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/v1/app/product/queryProductbyId?proId=29").toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }
    public void getProductList(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        String url = getRootApi().append("/v1/search/product/list").toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
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


    public <T> T toObject(JSONObject str, Class<T> claxx) {

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

    public <T> T toObject(String str, Class<T> claxx) {

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

    public <T> T toObject_(String str, Class<T> claxx) {

        if (str != null) {
            try {
                return JSON.parseObject(str, claxx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public <T> List<T> JSONArray2List(JSONArray arr, Class<T> clazz) {
        List list = new ArrayList<T>();
        if (arr != null && arr.length() != 0) {

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = null;
                try {
                    obj = arr.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                list.add(toObject(obj, clazz));
            }
            return list;

        }
        return list;
    }

    public <T> List<T> JSONArray2List(String str, Class<T> clazz) {
        JSONArray arr = null;
        try {
            arr = new JSONArray(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List list = new ArrayList<T>();
        if (arr != null && arr.length() != 0) {

            for (int i = 0; i < arr.length(); i++) {
                JSONObject obj = null;
                try {
                    obj = arr.getJSONObject(i);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                list.add(toObject(obj, clazz));
            }
            return list;

        }
        return list;
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
