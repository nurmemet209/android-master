package com.cn.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nurmemet on 2015/8/30.
 */
public class JsonRequest<T> extends Request<T> {

    private Map<String, String> mHeaders = new HashMap<String, String>(1);
    Response.Listener mListener;
    Class<T> type = null;

//    public static interface CookieHandler {
//        public void saveCookie(Map<String, String> headers);
//        public Map<String, String> addCookie();
//    }

    public static CookieHandler cookieHandler;
    public static void setCookieHandler(CookieHandler handler){
        cookieHandler=handler;
    }

    public JsonRequest(int method, String url, Response.Listener<T> listener, Response.ErrorListener errorListener, Class<T> clx) {
        super(method, url, errorListener);
        mListener = listener;
        type = clx;
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        saveHeaders(response);
        T parsed = null;
        String str = "";
        try {
            str = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        parsed = toObject(str);
        return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));


    }


    public <T> T toObject(String str) {
        Gson gson = new Gson();
        try {

            return gson.fromJson(str, (Class<T>) type);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toJson(Object obj) {
        Gson gson = new Gson();
        try {
            return gson.toJson(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {

        if (cookieHandler!=null){
            return  cookieHandler.addCookie();
        }


        return super.getHeaders();
    }

    private void saveHeaders(NetworkResponse response) {
       if (cookieHandler!=null){
           cookieHandler.saveCookie(response.headers);
       }


    }
}
