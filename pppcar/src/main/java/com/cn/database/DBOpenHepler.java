package com.cn.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHepler extends SQLiteOpenHelper {




    public DBOpenHepler(Context context, int version) {
        super(context, context.getFilesDir()+"/databases/place.db", null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
//        db.execSQL("create table if not exists downloadlist (id varchar(20),cid varchar(20),name varchar(100),vid varchar(20),hits varchar(100),length varchar(10),thumb varchar(200),sid varchar(20),filesize varchar(10),bitrate varchar(4),percent int ,primary key (vid))");
//        db.execSQL("create table if not exists collection (id varchar(20),title varchar(100),money varchar(20),teacher varchar(100),totaltime varchar(50),thumb varchar(200))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}
