package com.cn.net;

/**
 * Created by nurmemet on 2015/8/29.
 */
public class VolleyErrorHelper {

//    public static String getMessage(Object error, Context context) {
//
//        if (error instanceof TimeoutError) {
//
//            return context.getResources().getString(R.string.generic_server_down);
//
//        }
//
//        else if (isServerProblem(error)) {
//
//            return handleServerError(error, context);
//
//        }
//
//        else if (isNetworkProblem(error)) {
//
//            return context.getResources().getString(R.string.no_internet);
//
//        }
//
//        return context.getResources().getString(R.string.generic_error);
//
//    }
//
//
//
//
//
//    private static boolean isNetworkProblem(Object error) {
//
//        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
//
//    }
//
//
//
//    private static boolean isServerProblem(Object error) {
//
//        return (error instanceof ServerError) || (error instanceof AuthFailureError);
//
//    }
//
//
//
//    private static String handleServerError(Object err, Context context) {
//
//        VolleyError error = (VolleyError) err;
//
//
//
//        NetworkResponse response = error.networkResponse;
//
//
//
//        if (response != null) {
//
//            switch (response.statusCode) {
//
//                case 404:
//
//                case 422:
//
//                case 401:
//
//                    try {
//
//                        // server might return error like this { "error": "Some error occured" }
//
//                        // Use "Gson" to parse the result
//
//                        HashMap result = new Gson().fromJson(new String(response.data),
//
//                                new TypeToken>() {
//
//                        }.getType());
//
//
//                        if (result != null && result.containsKey("error")) {
//
//                            return result.get("error");
//
//                        }
//
//
//                    } catch (Exception e) {
//
//                        e.printStackTrace();
//
//                    }
//
//                    // invalid request
//
//                    return error.getMessage();
//
//
//                default:
//
//                    return context.getResources().getString(R.string.generic_server_down);
//
//            }
//
//        }
//
//        return context.getResources().getString(R.string.generic_error);
//
//    }
}
