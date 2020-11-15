package com.example.gymtracker.db_classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Userlog_dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="HealthGenix.db";
    public static final int DATABASE_VERSION=4;
    public int entry_id=301101;
    Userlog_dbhelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User_log (entry_id integer primary key, user_id integer, date_tracked date, consumed_cal decimal,water_tracked integer, burnt_cal decimal)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists User_log");
        onCreate(db);

    }
}
