package com.cn.net;

import com.android.volley.toolbox.HurlStack;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.Proxy;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.OkHttpClient;
import okhttp3.internal.huc.HttpURLConnectionImpl;
import okhttp3.internal.huc.HttpsURLConnectionImpl;

/**
 * Created by nurmemet on 2016/3/23.
 */
public class CustomStack extends HurlStack {

    private OkHttpClient okHttpClient;

    /**
     * Create a OkHttpStack with default OkHttpClient.
     */
    public CustomStack(SSLSocketFactory factory) {
        this(new OkHttpClient(),null,factory);
    }

    /**
     * Create a OkHttpStack with a custom OkHttpClient
     * @param okHttpClient Custom OkHttpClient, NonNull
     */
    public CustomStack(OkHttpClient okHttpClient,UrlRewriter urlRewriter, SSLSocketFactory sslSocketFactory) {
        super(urlRewriter,sslSocketFactory);
        this.okHttpClient = okHttpClient;

    }

    @Override
    protected HttpURLConnection createConnection(URL url) throws IOException {
        return open(url,okHttpClient.proxy());
    }




   private HttpURLConnection open(URL url, Proxy proxy) {
        String protocol = url.getProtocol();
        OkHttpClient copy = okHttpClient.newBuilder().proxy(proxy).build();
        if (protocol.equals("http")){
            HttpURLConnection conn=new HttpURLConnectionImpl(url, copy);
            return conn;
        }
        if (protocol.equals("https")){
            HttpURLConnection conn=new HttpsURLConnectionImpl(url, copy);
            return conn;
        }
        throw new IllegalArgumentException("Unexpected protocol: " + protocol);
    }


}
