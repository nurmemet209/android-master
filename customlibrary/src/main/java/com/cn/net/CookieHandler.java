package com.cn.net;

import java.util.Map;

/**
 * Created by nurmemet on 2015/9/17.
 */
public interface CookieHandler {
    public void saveCookie(Map<String, String> headers);
    public Map<String, String> addCookie();
}
