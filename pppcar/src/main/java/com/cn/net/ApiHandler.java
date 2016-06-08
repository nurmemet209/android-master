package com.cn.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.cn.entity.AppUserInfo;
import com.cn.entity.CitySelectPage;
import com.cn.entity.Consignee;
import com.cn.localutils.MD5;
import com.cn.util.MyLogger;
import com.google.gson.Gson;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nurmemet on 2015/8/29.
 */
public class ApiHandler implements CookieHandler, Response.ErrorListener {

    private static String PAGE_SIZE = "10";
    public static AppUserInfo appUserInfo;
    public static String code = "";
    public static String msg = "";
    protected Context mContext;

    private static ApiHelper apiHelper;
//     public final static String HOST = "http://pppcar.f3322.net:8088";

    //    public final static String HOST = "http://192.168.0.212:8081";
    public final static String HOST = "http://192.168.0.219:8081";
    //    public final static String HOST = "http://192.168.0.59:8081";
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
        Map<String, String> headers = new HashMap<>();
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


    public void login(Response.Listener<JSONObject> listener, final String userName, final String pasword, final String verifycationCode, final String varifycationCodeTk) {
        StringBuilder builder = getRootApi().append("/v1/user/login");
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = getDefaultPosData();
//                params.put("username", "testb");
//                params.put("password", "123456");
                //东东
//                params.put("username", "15520138623");
//                params.put("password", "123456");
                //刘文中
                params.put("username", "15021917166");
                params.put("password", "15021917166");
                params.put("captcha", "123456");
                params.put("captchaToken", "123456");
//                params.put("captcha", verifycationCode);
//                params.put("captchaToken", varifycationCodeTk);
                return params;
            }

        };
        addToRequestQueue(request);
    }

//    public void login(Response.Listener<JSONObject> listener, final String userName, final String pasword, final String verifycationCode, final String varifycationCodeTk) {
//        StringBuilder builder = getRootApi().append("/v1/user/login");
//        HashMap<String, String> params = new HashMap<>();
//        params.put("username", userName);
//        params.put("password", pasword);
//        params.put("captcha", verifycationCode);
//        params.put("captchaToken", varifycationCodeTk);
//        //JSONObject object = new JSONObject(params);
//        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), params, listener, this);
//        addToRequestQueue(request);
//    }

    /**
     * 收藏及取消收藏
     *
     * @param listener
     * @param id
     */
    public void collect(Response.Listener<JSONObject> listener, final long id) {
        //v1/account/auth/

        StringBuilder builder = getRootApi().append("/v1/account/auth/updateFavorites?");
        Map<String, String> map = new HashMap<>();
        map.put("proId", String.valueOf(id));
        setSign(builder, map);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, builder.toString(), null, listener, this);
        addToRequestQueue(request);
    }

    /**
     * 收藏列表
     *
     * @param listener
     */
    public void collectList(Response.Listener<JSONObject> listener, int page) {

        StringBuilder builder = getRootApi().append("/v1/account/auth/queryFavorites?");
        Map<String, String> map = new HashMap();
        map.put("page", String.valueOf(page));
        map.put("size", PAGE_SIZE);
        setSign(builder, map);
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.GET, builder.toString(), listener, this);
        addToRequestQueue(request);
    }
//
//    public void unCollect(Response.Listener<JSONObject> listener, final int id) {
//        //v1/account/auth/
//        StringBuilder builder = getRootApi().append("/v1/account/auth/delFavorites");
//        final CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, this) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params=new HashMap<>();
//                params.put("favId",String.valueOf(id));
//                params.put("sign",getSign4Post(params));
//                return super.getParams();
//            }
//        };
//        addToRequestQueue(request);
//    }


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

    /**
     * @param listener
     * @param orderType 1 普通订单，2 预订单
     */
    public void getMyOrder(Response.Listener<JSONObject> listener, int orderType, String state, String page, Response.ErrorListener errorListener) {
        StringBuilder builder = getRootApi().append("/v1/account/auth");
        Map<String, String> param = new HashMap<>();
        param.put("page", page);
        if (orderType == 1) {
            builder.append("/order/list?");
            if (state != null) {
                param.put("state", state);
            }
        } else {
            builder.append("/advanceOrder/list?");
        }
        setSign(builder, param);
        CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, errorListener);
        addToRequestQueue(request);
    }

    public void getIntegralProduct(Response.Listener<JSONObject> listener, Map<String, String> param) {
        StringBuilder builder = getRootApi().append("/v1/integral/auth/queryIntegralProductall?");
        setSign(builder, param);
        CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, this);
        addToRequestQueue(request);
    }

    public void getIntegralProductDetail(Response.Listener<JSONObject> listener, String proId, Response.ErrorListener errorListener) {
        StringBuilder builder = getRootApi().append("/v1/integral/auth/queryIntegralProductDetailById?");
        Map<String, String> param = new HashMap<>();
        param.put("proId", proId);
        setSign(builder, param);
        CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, this);
        addToRequestQueue(request);
    }

    public void getIntegralProductPaySettlementPage(Response.Listener<JSONObject> listener, final String proId, Response.ErrorListener errorListener) {
        StringBuilder builder = getRootApi().append("/v1/integral/auth/exchange?");

        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("proId", proId);
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);
    }

    public void getProductDetail(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, long id) {
        String url = getRootApi().append("/v1/app/product/queryProductbyId?proId=").append(id).toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, errorListener);
        addToRequestQueue(request);
    }

    public void getProductList(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener, Map param) {
        //, String keyWord, String sortType, String page,
//        StringBuilder builder = getRootApi().append("/v1/search/product/list?search_searchContent=").append(keyWord);
//        if (sortType != null && !"".equals(sortType)) {
//            builder.append("&search_sortType=").append(sortType);
//        }

        StringBuilder builder = getRootApi().append("/v1/search/product/list");
        setParam4NoLogin(builder, param);
        JsonObjectRequest request = new JsonObjectRequest(builder.toString(), null, listener, errorListener);
        addToRequestQueue(request);
    }

    private void setParam4NoLogin(StringBuilder builder, Map<String, String> param) {
        if (param != null && !param.isEmpty()) {
            builder.append("?");
            for (String key : param.keySet()) {
                builder.append(key).append("=").append(param.get(key)).append("&");
            }
            builder.replace(builder.length() - 1, builder.length(), "");
        }
        MyLogger.showLog(builder.toString());

    }

    public static class ParamBuilder {
        Map<String, String> paramContainer;

        public ParamBuilder() {
            paramContainer = new HashMap<>();
        }

        public ParamBuilder putParam(String key, String value) {
            if (StringUtils.isNotEmpty(value)) {
                paramContainer.put(key, value);
            }
            return this;
        }

        public Map<String, String> getMap() {
            return paramContainer;
        }
    }

    public ParamBuilder getParamBuilder() {
        return new ParamBuilder();
    }


    /**
     * @param listener
     * @param id       套餐id或者产品id
     * @param num
     * @param flag     1表示普通商品放入购物车，2表示套餐商品放入购物车
     */
    public void add2Cart(Response.Listener<JSONObject> listener, final long id, final int num, final int flag) {
        String url = "";
        if (flag == 1) {
            //普通商品加入购物车
            url = getRootApi().append("/v1/cart/auth/addGoods").toString();
        } else if (flag == 2) {
            //套餐商品加入购物策划
            url = getRootApi().append("/v1/cart/auth/addFittingPro").toString();
        }
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, url, listener, this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                if (flag == 1) {
                    params.put("proId", String.valueOf(id));
                    params.put("number", String.valueOf(num));
                } else if (flag == 2) {
                    params.put("groupId", String.valueOf(id));
                }
                setSign4Post(params);
                return params;
            }
        };
        addToRequestQueue(request);
    }

    /**
     * @param listener
     * @param year
     */
    public void getAuctionList(Response.Listener<JSONObject> listener, String year) {
        String url = getRootApi().append("/v1/auction/queryAuctionList?auctionYear=").append(year).toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }

    /**
     * 拍卖出价页面
     *
     * @param listener
     * @param id       拍卖id
     */
    public void getAuctionBidDetail(Response.Listener<JSONObject> listener, String id) {
        String url = getRootApi().append("/v1/auction/queryAuctionList?auctionId=").append(id).toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }

    /**
     * 拍卖出价
     *
     * @param listener
     * @param id       拍卖id
     * @param price    出价
     */
    public void auctionBid(Response.Listener<JSONObject> listener, final long id, final double price) {
        String url = getRootApi().append("/v1/auction/auth/offerAuctionPrice").append(id).toString();
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, null, listener, this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = new HashMap();

                params.put("auctionId", String.valueOf(id));
                params.put("bidPrice ", String.valueOf(price));
                return params;
            }

        };
        addToRequestQueue(request);
    }

    /**
     * 一口价拍下
     *
     * @param listener
     * @param id       拍卖id
     * @param price    一口价
     */
    public void auctionByAPrice(Response.Listener<JSONObject> listener, final long id, final double price) {
        String url = getRootApi().append("/v1/auction/auth/offerAuctionPrice").append(id).toString();
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, null, listener, this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> params = getDefaultPosData();
                params.put("auctionId", String.valueOf(id));
                params.put("bidPrice ", String.valueOf(price));
                return params;
            }

        };
        addToRequestQueue(request);
    }

    /**
     * 我的购物车
     *
     * @param listener
     */
    public void getCartPage(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        StringBuilder builder = getRootApi().append("/v1/cart/auth/list?");
        setSign(builder, null);
        CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, errorListener);
        addToRequestQueue(request);


    }

    public void getInvoiceInformationList(Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {

        StringBuilder builder = getRootApi().append("/v1/account/auth/invoiceCommon?");
        setSign(builder, null);
        CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, errorListener);
        addToRequestQueue(request);


    }

    /**
     * @param listener
     */
    public void getReceriverAddressList(Response.Listener<JSONObject> listener) {
        if (appUserInfo != null) {
            StringBuilder builder = getRootApi().append("/v1/account/auth/allConsignee?");
            setSign(builder, null);
            CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, this);
            addToRequestQueue(request);
        }

    }

    public void addModifyInvoiceCommon(Response.Listener<JSONObject> listener, final Map<String, String> param, Response.ErrorListener errorListener) {

        StringBuilder builder = getRootApi().append("/v1/account/auth/saveInvoiceTitle");
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);


    }

    /**
     * @param listener
     * @param param
     * @param errorListener
     */
    public void deleteInvoiceCommon(Response.Listener<JSONObject> listener, final Map<String, String> param, Response.ErrorListener errorListener) {

        StringBuilder builder = getRootApi().append("/v1/account/auth/delInvoiceCommon");
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);

    }

    /**
     * @param listener
     * @param param
     */
    public void getInvoiceCommon(Response.Listener<JSONObject> listener, final Map<String, String> param) {

        StringBuilder builder = getRootApi().append("/v1/account/auth/getInvoiceCommonById?");
        setSign(builder, param);
        CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, this);
        addToRequestQueue(request);

    }

    /**
     * 增值税 发票修改，添加
     *
     * @param listener
     * @param param
     * @param errorListener
     */
    public void addModifyInvoiceAddTax(Response.Listener<JSONObject> listener, final Map<String, String> param, Response.ErrorListener errorListener) {

        StringBuilder builder = getRootApi().append("/v1/account/auth/saveInvoiceVAT");
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);


    }

    public void getInvoiceAddTax(Response.Listener<JSONObject> listener) {
        if (appUserInfo != null) {
            StringBuilder builder = getRootApi().append("/v1/account/auth/invoiceVAT?");
            setSign(builder, null);
            CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, this);
            addToRequestQueue(request);
        }

    }

    /**
     * @param listener
     * @param id            收获地址ID
     * @param errorListener
     */
    public void deleteReceriverAddress(Response.Listener<JSONObject> listener, final String id, Response.ErrorListener errorListener) {

        StringBuilder builder = getRootApi().append("/v1/account/auth/delConsignee");
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("consigneeId", id);
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);


    }

    /**
     * @param listener
     * @param consignee
     * @param type      1 新增，2 编辑
     */
    public void addReceiveAddr(Response.Listener<JSONObject> listener, final Consignee consignee, final int type, Response.ErrorListener errorListener) {

        StringBuilder builder = getRootApi().append("/v1/account/auth/");
        if (type == 1) {
            builder.append("addConsignee");
        } else if (type == 2) {
            builder.append("updateConsignee");
        }
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                if (type == 2) {
                    map.put("id", String.valueOf(consignee.getId()));
                }
                map.put("consignee", consignee.getConsignee());
                map.put("address", consignee.getAddress());
                map.put("mobileNumber", consignee.getMobileNumber());
                map.put("telNumber", consignee.getTelNumber());
                map.put("isDefault", consignee.getIsDefault() ? "1" : "0");
                setSign4Post(map);
                return map;
            }
        };
        addToRequestQueue(request);


    }

    /**
     * '
     *
     * @param listener
     * @param cartItemId
     */
    public void delFromCart(Response.Listener<JSONObject> listener, final String cartItemId) {
        StringBuilder builder = getRootApi().append("/v1/cart/auth/delCartGoods");
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cartIds", cartItemId);
                setSign4Post(params);
                return params;
            }
        };
        addToRequestQueue(request);

    }

    /**
     * '
     *
     * @param listener
     * @param cartItemId
     */
    public void move2Collect(Response.Listener<JSONObject> listener, final String cartItemId) {
        StringBuilder builder = getRootApi().append("/v1/cart/auth/moveGoods2favorite");
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cartIds", cartItemId);
                setSign4Post(params);
                return params;
            }
        };
        addToRequestQueue(request);

    }


    /**
     * @param listener
     * @param param
     * @param orderType     2 预订单结算，1 普通订单结算
     * @param errorListener
     */
    public void getPaySettlementPage(Response.Listener<JSONObject> listener, Map<String, String> param, int orderType, Response.ErrorListener errorListener) {
        StringBuilder builder = null;
        if (orderType == 1) {
            builder = getRootApi().append("/v1/order/auth/generateOrder?");
        } else if (orderType == 2) {
            builder = getRootApi().append("/v1/reserve/auth/reserveGoodsOrderDetail?");
        }
        setSign(builder, param);
        CustomJSonRequest request = new CustomJSonRequest(builder.toString(), listener, errorListener);
        addToRequestQueue(request);
    }

    public void updateCart(Response.Listener<JSONObject> listener, final String cartId, final int num, final boolean isChecked) {
        StringBuilder builder = getRootApi().append("/v1/cart/auth/updateCart");
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, this) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cartId", cartId);

                params.put("checked", isChecked ? "1" : "0");
                if (num != -1) {
                    params.put("number", String.valueOf(num));
                }
                setSign4Post(params);
                return params;
            }
        };
        addToRequestQueue(request);
    }

    /**
     * 首页
     *
     * @param listener
     */
    public void getMainPage(Response.Listener<JSONObject> listener) {
        String url = getRootApi().append("/v1/index/queryIndexPublicElement").toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }

    public void getSearchPage(Response.Listener<JSONObject> listener, String keyWord) {
        String url = getRootApi().append("/v1/search/product/searchPrompt?searchVal=").append(keyWord).toString();
        JsonObjectRequest request = new JsonObjectRequest(url, null, listener, this);
        addToRequestQueue(request);
    }

    public void getImageValidationCode(Response.Listener<Bitmap> listener) {
        StringBuilder builder = getRootApi().append("/v1/code/captcha?captchaToken=123456789");
        ImageRequest request = new ImageRequest(builder.toString(), listener, 100, 100, ImageView.ScaleType.CENTER_INSIDE, null, this);
        addToRequestQueue(request);
    }

    /**
     * @param listener
     * @param orderType 1 普通订单，2 预订单
     */
    public void submitPreOrder(Response.Listener<JSONObject> listener, final int orderType, final Map<String, String> param, Response.ErrorListener errorListener) {
        StringBuilder builder = null;
        if (orderType == 2) {
            builder = getRootApi().append("/v1/reserve/auth/reserveGoods");
        } else if (orderType == 1) {
            builder = getRootApi().append("/v1/order/auth/submitOrder");
        }
        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

//                if (orderType == 2) {
//                    params.put("ruleId", ruleId);
//                }
//                params.put("invoiceType", invoiceType);
//                params.put("number", number);
//                params.put("consigneeId", consigneeId);
//                params.put("productId", productId);
//                params.put("remark", remark);
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);
    }

    public void cancelCommonOrder(Response.Listener<JSONObject> listener, final Map<String, String> param, Response.ErrorListener errorListener) {
        StringBuilder builder = getRootApi().append("/v1/account/auth/order/cancel");

        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);
    }

    public void cancelPreOrder(Response.Listener<JSONObject> listener, final Map<String, String> param, Response.ErrorListener errorListener) {
        StringBuilder builder = getRootApi().append("/v1/order/auth/submitOrder");

        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);
    }

    public void deletePreOrder(Response.Listener<JSONObject> listener, final Map<String, String> param, Response.ErrorListener errorListener) {
        StringBuilder builder = getRootApi().append("/v1/order/auth/submitOrder");

        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                setSign4Post(param);
                return param;
            }
        };
        addToRequestQueue(request);
    }

    public void deleteCommonOrder(Response.Listener<JSONObject> listener, final Map<String, String> param, Response.ErrorListener errorListener) {
        StringBuilder builder = getRootApi().append("/v1/account/auth/order/delete");

        CustomJSonRequest request = new CustomJSonRequest(Request.Method.POST, builder.toString(), listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                setSign4Post(param);
                return param;
            }
        };
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
        MyLogger.showError(error.getMessage());
    }


//    public <T> T toObject(JSONObject str, Class<T> claxx) {
//
//        if (str != null) {
//            Gson gson = new Gson();
//            try {
//                return gson.fromJson(str.toString(), claxx);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return null;
//    }

//    public <T> T toObject(String str, Class<T> claxx) {
//
//        if (str != null) {
//            Gson gson = new Gson();
//            try {
//                return gson.fromJson(str.toString(), claxx);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        return null;
//    }

    public <T> T toObject(JSONObject str, Class<T> claxx) {

        if (str != null) {
            try {
                return JSON.parseObject(str.toString(), claxx);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public <T> T toObject(String str, Class<T> claxx) {

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

    public interface Listener<T> {
        /**
         * Called when a response is received.
         */
        void onResponse(T response);
    }


    private void setSign4Post(Map<String, String> map) {
        if (map == null) {
            new IllegalStateException("map can not be null");
        }
        List<String> aa = new ArrayList<String>();
        map.put("appToken", appUserInfo.getAppToken());
        map.put("userId", String.valueOf(appUserInfo.getUserId()));
        for (String key : map.keySet()) {
            aa.add(map.get(key));
        }
        aa.add("29afef854578396a6a63d76a40e0d5ba");
        Collections.sort(aa);
        StringBuilder builder = new StringBuilder();
        for (String value : aa) {
            builder.append(value);
        }
        String newSig = MD5.md5Encode(builder.toString());
        map.put("sign", newSig);
        //url后面带上一个sig的值
        return;
    }

    private void setSign(StringBuilder builder, Map<String, String> map) {
        if (appUserInfo == null) {
            return;
        }
        List<String> aa = new ArrayList<String>();
        if (map == null) {
            map = new HashMap<>();
        }
        map.put("appToken", appUserInfo.getAppToken());
        map.put("userId", String.valueOf(appUserInfo.getUserId()));
        for (String key : map.keySet()) {
            aa.add(map.get(key));
            builder.append(key).append("=").append(map.get(key)).append("&");
        }

        aa.add("29afef854578396a6a63d76a40e0d5ba");
        Collections.sort(aa);
        StringBuilder bl = new StringBuilder();
        for (String value : aa) {
            bl.append(value);
        }
        String newSig = MD5.md5Encode(bl.toString());
        builder.append("sign=").append(newSig);
        //url后面带上一个sig的值
        return;
    }

    private void setParam(StringBuilder builder, Map<String, String> param) {
        if (!param.isEmpty()) {
            builder.append("?");
            for (String key : param.keySet()) {
                builder.append(key).append("=").append(param.get(key)).append("&");
            }
            builder.replace(builder.length() - 1, builder.length(), "");
        }


    }


}
