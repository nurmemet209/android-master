package com.cn.net;

import android.content.Context;
import android.os.Debug;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;
import com.cn.customlibrary.BuildConfig;
import com.cn.customlibrary.R;


import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;


/**
 * Created by nurmemet on 2015/8/29.
 */
public class ApiHelper {

    private final static int rTimeOut = 50000;// request time out
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
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.connectTimeout(rTimeOut, TimeUnit.MILLISECONDS);
            OkHttpClient client = builder.build();


            mRequestQueue = Volley.newRequestQueue(mContext, new CustomStack(client, null));
        }
        return mRequestQueue;
    }

    public void addToRequestQueue(Request req, String tag) {
        getRequestQueue().add(req);
    }


    public void addToRequestQueue(Request req) {
        req.setRetryPolicy(new DefaultRetryPolicy(sTimeOut,//默认超时时间，应设置一个稍微大点儿的，例如本处的500000
                1,//默认最大尝试次数
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        req.setTag(TAG);
        getRequestQueue().add(req);
    }


    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }

    }

    private void initClient() {

    }


//    private HurlStack getSslStack(){
//        String[] hosts = {"kyfw.12306.cn"};
//        int[] certRes = {1};//R.raw.kyfw
//        String[] certPass = {"asdfqaz"};
//        Map socketFactoryMap = new Hashtable<>(hosts.length);
//
//        for (int i = 0; i < certRes.length; i++) {
//            int res = certRes[i];
//            String password = certPass[i];
//            SSLSocketFactory sslSocketFactory=null ;//= createSSLSocketFactory(mContext, res, password);
//            socketFactoryMap.put(hosts[i], sslSocketFactory);
//        }
//
//        HurlStack stack = new SelfSignSslOkHttpStack(socketFactoryMap);
//
//        return stack;
//    }

    public SSLSocketFactory createSslFactory() {
        try {

            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            // From https://www.washington.edu/itconnect/security/ca/load-der.crt
            InputStream caInput = new BufferedInputStream(new FileInputStream("load-der.crt"));
            Certificate ca = null;
            try {
                ca = cf.generateCertificate(caInput);
                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                caInput.close();
            }
            // Create a KeyStore containing our trusted CAs
            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);
            // Create a TrustManager that trusts the CAs in our KeyStore
            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);
            // Create an SSLContext that uses our TrustManager
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);
            return context.getSocketFactory();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
