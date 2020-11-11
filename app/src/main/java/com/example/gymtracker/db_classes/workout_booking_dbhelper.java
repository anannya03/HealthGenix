package com.example.gymtracker.db_classes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class workout_booking_dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="GymTracker.db";
    public static final int DATABASE_VERSION=3;
    workout_booking_dbhelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Workout_booking"+"(user_id integer,workout_id integer, gym_id integer,primary key(user_id, workout_id, gym_id), foreign key(user_id) references User(user_id), foreign key(gym_id) references Gym(gym_id),foreign key(work_id) references Workout(work_id))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists Workout_booking");
        onCreate(db);
    }
}
