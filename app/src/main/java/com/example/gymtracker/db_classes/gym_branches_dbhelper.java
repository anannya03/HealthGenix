package com.example.gymtracker.db_classes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class gym_branches_dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="HealthGenix.db";
    public static final int DATABASE_VERSION=4;
    public int gym_id=201101;

    gym_branches_dbhelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Gym (gym_id integer primary key, city text, area text, pincode text, full_add text, details text)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists Gym");
        onCreate(db);
    }

}
