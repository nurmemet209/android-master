package com.cn.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cn.entity.Area;
import com.cn.entity.BaseEntity;
import com.cn.entity.City;
import com.cn.entity.Province;

import java.util.ArrayList;

public class DBservice {
    private static final String TAG = "DBservice";
    private DBOpenHepler dbOpenHepler;

    public DBservice(Context context) {
        // 1 -> database version
        dbOpenHepler = new DBOpenHepler(context, 8);
    }

    /**
     * 查询所有的省份
     * @return
     */
    public ArrayList<? extends BaseEntity> getAllProvince(){
        ArrayList<Province> provinceList=new ArrayList<>();
        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
        String sql="select id,province_id,province from pca_provinces";
        String str="select name from sqlite_master where type='table' ";
//        Cursor cursor=db.rawQuery(str,null);
//        while (cursor.moveToNext()){
//            String s=cursor.getString(cursor.getColumnIndex("name"));
//            System.out.println(s);
//        }
        Cursor cursor=db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex("id"));
            long provinceId=cursor.getLong(cursor.getColumnIndex("province_id"));
            String name=cursor.getString(cursor.getColumnIndex("province"));
            Province province=new Province();
            province.setId(id);
            province.setName(name);
            province.setProvinceId(provinceId);
            provinceList.add(province);
        }

        return provinceList;
    }

    /**
     * 根据省份查询城市
     * @param provinceId
     * @return
     */
    public ArrayList<City> getCitiesByProvinceId(long provinceId){
        ArrayList<City> cityList=new ArrayList<>();
        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
        String sql="select id,city_id,city from pca_cities where province_id=?";
        Cursor cursor=db.rawQuery(sql,new String[]{Long.toString(provinceId)});
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex("id"));
            long cityId=cursor.getLong(cursor.getColumnIndex("city_Id"));
            String name=cursor.getString(cursor.getColumnIndex("city"));
            City city=new City();
            city.setId(id);
            city.setName(name);
            city.setCityId(cityId);
            city.setProvinceId(provinceId);
            cityList.add(city);
        }

        return cityList;
    }


    /**
     * 根据城市查询区域
     * @param cityId
     * @return
     */
    public ArrayList<Area> getAreasByCityId(long cityId){
        ArrayList<Area> areaList=new ArrayList<>();
        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
        String sql="select id,area_id,area from pca_areas where city_id=?";
        Cursor cursor=db.rawQuery(sql,new String[]{Long.toString(cityId)});
        while (cursor.moveToNext()){
            long id = cursor.getLong(cursor.getColumnIndex("id"));
            long areaId=cursor.getLong(cursor.getColumnIndex("area_Id"));
            String name=cursor.getString(cursor.getColumnIndex("area"));
            Area area=new Area();
            area.setId(id);
            area.setName(name);
            area.setCityId(cityId);
            area.setAreaId(areaId);
            areaList.add(area);
        }

        return areaList;
    }


//    public void addDownloadFile(VedioCollect info) {
//        if (isAdd(info))
//            return;
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "insert into downloadlist(id,cid,name,vid,hits,length,thumb,sid,filesize,bitrate,percent) values(?,?,?,?,?,?,?,?,?,?,?)";
//        db.execSQL(
//                sql,
//                new Object[]{info.getId(), info.getCid(), info.getName(),
//                        info.getVid(), info.getHits(), info.getLength(), info.getThumb(), info.getSid(), info.getFilesize(), info.getBitrate(), info.getPercent()});
//    }
//
//    public void deleteDownloadFile(VedioCollect info) {
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "delete from downloadlist where vid=?";
//        db.execSQL(sql, new Object[]{info.getVid()});
//    }
//
//    public void updatePercent(VedioCollect info, int percent) {
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "update downloadlist set percent=? where vid=?";
//        db.execSQL(
//                sql,
//                new Object[]{percent, info.getVid()});
//    }
//
//    public boolean isAdd(VedioCollect info) {
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "select id,cid,name,vid,hits,length,thumb,sid,filesize,bitrate,percent from downloadlist where vid=? ";
//        Cursor cursor = db.rawQuery(sql, new String[]{info.getVid()});
//        if (cursor.getCount() == 1) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public ArrayList<VedioCollect> getDownloadFiles() {
//        ArrayList<VedioCollect> infos = new ArrayList<>();
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "select  id,cid,name,vid,hits,length,thumb,sid,filesize,bitrate,percent from downloadlist";
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex("id"));
//            String cid = cursor.getString(cursor.getColumnIndex("cid"));
//            String name = cursor.getString(cursor.getColumnIndex("name"));
//            ;
//            String vid = cursor.getString(cursor.getColumnIndex("vid"));
//            String hits = cursor.getString(cursor.getColumnIndex("hits"));
//            String length = cursor.getString(cursor.getColumnIndex("length"));
//            String thumb = cursor.getString(cursor.getColumnIndex("thumb"));
//            String sid = cursor.getString(cursor.getColumnIndex("sid"));
//            String filesize = cursor.getString(cursor.getColumnIndex("filesize"));
//            String bitrate = cursor.getString(cursor.getColumnIndex("bitrate"));
//            int percent = cursor.getInt(cursor.getColumnIndex("percent"));
//
//            VedioCollect info = new VedioCollect();
//            info.setId(id);
//            info.setCid(cid);
//            info.setName(name);
//            info.setVid(vid);
//            info.setHits(hits);
//            info.setLength(length);
//            info.setThumb(thumb);
//            info.setSid(sid);
//            info.setFilesize(filesize);
//            info.setBitrate(bitrate);
//            info.setPercent(percent);
//
//            infos.add(info);
//        }
//        return infos;
//    }
//
//
//    /***
//     * 为了我保存收藏重写的
//     * id;
//     * title;
//     * money;
//     * thumb;
//     * totaltime;
//     * teacher;
//     */
//
//    public void addCollectFile(MainPgeListItem info) {
//        if (isAddCollect(info))
//            return;
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "insert into collection(id,title,money,thumb,totaltime,teacher) values(?,?,?,?,?,?)";
//        db.execSQL(
//                sql,
//                new Object[]{info.getId(), info.getTitle(), info.getMoney(),
//                        info.getThumb(), info.getTotaltime(), info.getTeacher()});
//    }
//
//    public void deleteCollectFile(MainPgeListItem info) {
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "delete from collection where id=?";
//        db.execSQL(
//                sql,
//                new Object[]{info.getId()});
//    }
//
//    public boolean isAddCollect(MainPgeListItem info) {
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "select id,title,money,thumb,totaltime,teacher from collection where id=? ";
//        Cursor cursor = db.rawQuery(sql, new String[]{info.getId()});
//        if (cursor.getCount() == 1) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public ArrayList<MainPgeListItem> getCollectFiles() {
//        ArrayList<MainPgeListItem> infos = new ArrayList<>();
//        SQLiteDatabase db = dbOpenHepler.getWritableDatabase();
//        String sql = "select  id,title,money,thumb,totaltime,teacher from collection";
//        Cursor cursor = db.rawQuery(sql, null);
//        while (cursor.moveToNext()) {
//            String id = cursor.getString(cursor.getColumnIndex("id"));
//            String title = cursor.getString(cursor.getColumnIndex("title"));
//            String money = cursor.getString(cursor.getColumnIndex("money"));
//            String thumb = cursor.getString(cursor.getColumnIndex("thumb"));
//            String totaltime = cursor.getString(cursor.getColumnIndex("totaltime"));
//            String teacher = cursor.getString(cursor.getColumnIndex("teacher"));
//
//            MainPgeListItem copyItem = new MainPgeListItem();
//            copyItem.setId(id);
//            copyItem.setTitle(title);
//            copyItem.setMoney(money);
//            copyItem.setThumb(thumb);
//            copyItem.setTotaltime(totaltime);
//            copyItem.setTeacher(teacher);
//
//            infos.add(copyItem);
//        }
//        return infos;
//    }


}
