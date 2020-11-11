package com.example.gymtracker.db_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gymtracker.db_classes.User;

import androidx.annotation.Nullable;

import java.util.Random;

public class User_dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="GymTracker.db";
    public static final int DATABASE_VERSION=1;
    public long user_id;
    public User_dbhelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table User"+"(user_id integer primary key, fname text, lname text, email text, pwd text, age integer, gender char, phno text, weight decimal, height decimal, bmi decimal, mem_start date, mem_end date, gym_id integer)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists User");
        onCreate(db);
    }
    public boolean createUser(User user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        Random rnd = new Random();
        user_id = rnd.nextInt(999999);
        user.setUser_id(user_id);
        contentValues.put("User_id", user.getUser_id());
        contentValues.put("Fname", user.getFname());
        contentValues.put("Lname", user.getLname());
        contentValues.put("email", user.getEmail());
        contentValues.put("Pwd",user.getPwd());
        contentValues.put("Age", user.getAge());
        db.insert("User", null, contentValues);
        return(true);
    }
    public boolean userExist(String emailid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res= db.rawQuery("Select user_id from User where email= ?", new String[]{emailid});
        res.moveToFirst();
        int count=0;
        while(res.isAfterLast()==false)
        {
            count++;
        }
        if(count>0)
        {
            return(true);
        }
        return(false);
    }
}
