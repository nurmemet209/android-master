package com.cn.localutils;

import android.content.Context;
import android.os.Message;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * Created by nurmemet on 2016/3/30.
 */
public class AutoPosition implements AMapLocationListener {

    /**
     *  开始定位
     */
    public final static int MSG_LOCATION_START = 0;
    /**
     * 定位完成
     */
    public final static int MSG_LOCATION_FINISH = 1;
    /**
     * 停止定位
     */

    private AMapLocationClient locationClient = null;
    private AMapLocationClientOption locationOption = null;

    private PositionListener positionListener;

    public static interface PositionListener{
        public void onPosition(int code,String message);
    }

    public AutoPosition(Context applicationContext,PositionListener positionListener){
        locationClient = new AMapLocationClient(applicationContext);
        locationOption = new AMapLocationClientOption();
        this.positionListener=positionListener;
        // 设置定位模式为仅设备模式
        //locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
        locationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        // 设置定位监听
        locationClient.setLocationListener(this);

        //单次定位
        locationOption.setOnceLocation(true);
    }



    public void startPosition(){
        // 设置定位参数
        locationClient.setLocationOption(locationOption);
        // 启动定位
        locationClient.startLocation();
       // mHandler.sendEmptyMessage(MSG_LOCATION_START);
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        int code=-1;
        String location="";
        if (null != aMapLocation) {
            code=aMapLocation.getErrorCode();
            location=aMapLocation.getCity();
        }else{
            code=-1;
            location="定位错误";
        }
        if(positionListener!=null){
            positionListener.onPosition(code,location);
        }
    }

    public void destroy(){
        if (null != locationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            locationClient.onDestroy();
            locationClient = null;
            locationOption = null;
        }
    }





    /**
     * 根据定位结果返回定位信息的字符串
     * @param loc
     * @return
     */
    public synchronized static String parseLocation(AMapLocation location){
        if(null == location){
            return null;
        }
        StringBuffer sb = new StringBuffer();
        //errCode等于0代表定位成功，其他的为定位失败，具体的可以参照官网定位错误码说明
        if(location.getErrorCode() == 0){
            sb.append("定位成功" + "\n");
            sb.append("定位类型: " + location.getLocationType() + "\n");
            sb.append("经    度    : " + location.getLongitude() + "\n");
            sb.append("纬    度    : " + location.getLatitude() + "\n");
            sb.append("精    度    : " + location.getAccuracy() + "米" + "\n");
            sb.append("提供者    : " + location.getProvider() + "\n");

            if (location.getProvider().equalsIgnoreCase(
                    android.location.LocationManager.GPS_PROVIDER)) {
                // 以下信息只有提供者是GPS时才会有
                sb.append("速    度    : " + location.getSpeed() + "米/秒" + "\n");
                sb.append("角    度    : " + location.getBearing() + "\n");
                // 获取当前提供定位服务的卫星个数
                sb.append("星    数    : "
                        + location.getSatellites() + "\n");
            } else {
                // 提供者是GPS时是没有以下信息的
                sb.append("国    家    : " + location.getCountry() + "\n");
                sb.append("省            : " + location.getProvince() + "\n");
                sb.append("市            : " + location.getCity() + "\n");
                sb.append("城市编码 : " + location.getCityCode() + "\n");
                sb.append("区            : " + location.getDistrict() + "\n");
                sb.append("区域 码   : " + location.getAdCode() + "\n");
                sb.append("地    址    : " + location.getAddress() + "\n");
                sb.append("兴趣点    : " + location.getPoiName() + "\n");
            }
        } else {
            //定位失败
            sb.append("定位失败" + "\n");
            sb.append("错误码:" + location.getErrorCode() + "\n");
            sb.append("错误信息:" + location.getErrorInfo() + "\n");
            sb.append("错误描述:" + location.getLocationDetail() + "\n");
        }
        return sb.toString();
    }
}
