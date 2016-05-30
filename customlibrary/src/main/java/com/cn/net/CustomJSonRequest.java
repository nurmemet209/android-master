package com.cn.net;

import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.cn.util.MyLogger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by erqal on 2015/10/10.
 */
public class CustomJSonRequest extends Request<JSONObject> {

    interface OnBackListener {
        void OnBack();
    }

    private OnBackListener onBackListener;
    /**
     * Default charset for JSON request.
     */
    protected static final String PROTOCOL_CHARSET = "utf-8";
    private Response.Listener<JSONObject> mListener;

    public CustomJSonRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.mListener = listener;
    }

    public CustomJSonRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, OnBackListener onBackListener) {
        super(method, url, errorListener);
        this.mListener = listener;
        this.onBackListener = onBackListener;
    }

    public CustomJSonRequest(String url, Response.Listener<JSONObject> listener,
                             Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.mListener = listener;
    }


    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        if (onBackListener != null) {
            onBackListener.OnBack();
        }
        String jsonString = "";
        try {
            jsonString = new String(response.data,
                    PROTOCOL_CHARSET);
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            MyLogger.showJson(jsonString);
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            MyLogger.showJson(jsonString);
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(JSONObject response) {
        mListener.onResponse(response);
    }

    @Override
    public void deliverError(VolleyError error) {
        super.deliverError(error);
    }


}
