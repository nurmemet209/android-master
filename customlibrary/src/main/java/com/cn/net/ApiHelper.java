package com.cn.net;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.squareup.okhttp.OkHttpClient;


/**
 * Created by nurmemet on 2015/8/29.
 */
public class ApiHelper {

    private final static int rTimeOut = 15000;// request time out
    private final static int sTimeOut = 15000;// data response time out
    public static final String TAG = "VolleyPatterns";
    private Context mContext;

    private RequestQueue mRequestQueue;

    public ApiHelper(Context context) {
        this.mContext = context;
        getRequestQueue();
    }


    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            OkHttpClient client= new OkHttpClient();


            OkHttpStack stack = new OkHttpStack(client);


            mRequestQueue = Volley.newRequestQueue(mContext, stack);

        }
        return mRequestQueue;
    }

    public void addToRequestQueue(Request req, String tag) {
        getRequestQueue().add(req);
    }


    public void addToRequestQueue(Request req) {
        req.setTag(TAG);

        getRequestQueue().add(req);
    }


    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }

    }


}
