package com.example.gymtracker.db_classes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Workouts_dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="HealthGenix.db";
    public static final int DATABASE_VERSION=1;
    public int workout_id=401101;
    Workouts_dbhelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Workout (workout_id integer primary key, work_name String, work_date date, type text, booked integer, capacity integer,foreign key(gym_id) references Gym(gym_id))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists Workout");
        onCreate(db);
    }
}
