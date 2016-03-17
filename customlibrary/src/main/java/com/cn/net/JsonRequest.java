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
//        ApiHelper.headers = (HashMap<String,String>) response.headers.get("Set-Cookie");
//
//        Header[] headers =response.headers.get("Set-Cookie");
//
//
//        if (headers.toString() == null)
//            return ;
//        // boolean isLogin = false;
//        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < headers.length; i++) {
//            String cookie = headers[i].getValue();
//            String[] cookievalues = cookie.split(";");
//            for (int j = 0; j < cookievalues.length; j++) {
//
//                String[] keyPair = cookievalues[j].split("=");
//                String key = keyPair[0].trim();
//                String value = keyPair.length > 1 ? keyPair[1].trim() : "";
//                //
//                CookieContiner.put(key, value);
//                sb.append(key).append(":\n").append(value).append("\n\n");
//                if (key.equals(COOKIE_PREFIX + "auth") && !value.trim().equals("")) {
//                    if (controller.getUser() == null) {
//                        User user = new User();
//                        user.setAuth(value);
//                        controller.setUser(user);
//                    }
//
//                }
//                if (key.equals(COOKIE_PREFIX + "name") && !value.trim().equals("") && controller.getUser() != null) {
//                    try {
//                        String name = URLDecoder.decode(value, "utf-8");
//                        controller.getUser().setName(value);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//                if (key.equals(COOKIE_PREFIX + "isEnable") && !value.trim().equals("") && controller.getUser() != null) {
//                    if (value.equals("1"))
//                        controller.getUser().setEnable(true);
//                    else if (value.equals("0"))
//                        controller.getUser().setEnable(false);
//                }
//                if (key.equals(COOKIE_PREFIX + "imgUrl") && !value.trim().equals("") && controller.getUser() != null) {
//                    controller.getUser().setHeadUrl(value);
//                }
//                if (key.equals(COOKIE_PREFIX + "ID") && !value.trim().equals("") && controller.getUser() != null) {
//                    controller.getUser().setPageId(value);
//                }
//                if (key.equals(COOKIE_PREFIX + "hasJobintention") && !value.trim().equals("") && controller.getUser() != null) {
//                    if (value.equals("1")) {
//                        controller.getUser().setHasJobIntesion(true);
//                    } else if (value.equals("0")) {
//                        controller.getUser().setHasJobIntesion(false);
//                    }
//
//
//                }
//                if (key.equals(COOKIE_PREFIX + "location") && !value.trim().equals("") && controller.getUser() != null) {
//                    controller.getUser().setLocation(value);
//                }
//
//
//            }
//
//            MyLogger.showLog(cookie);

    }
}
