package com.example.gymtracker.db_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.gymtracker.db_classes.User;

import androidx.annotation.Nullable;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Random;
public class User_dbhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="HealthGenix.db";
    public static final int DATABASE_VERSION=7;
    public long user_id;
    public int entry_id;
    public User_dbhelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table Users(user_id integer, fname text, lname text, email text, pwd text, age integer, gender text, phno text, weight decimal, height decimal, bmi decimal, mem_start date, mem_end date, gym_id integer, fitness_goal text, primary key(email))");
        db.execSQL("create table UserLog(entry_id integer primary key, email_id text, date_tracked date, consumed_cal decimal,water_tracked integer, burnt_cal decimal)");
        db.execSQL("create table Gym (gym_id integer primary key, city text, area text, pincode text, full_add text, details text)");
        db.execSQL("create table Workout (workout_id integer primary key, work_name String, work_date date, type text, booked integer, capacity integer,foreign key(gym_id) references Gym(gym_id))");
        db.execSQL("create table Workout_booking(user_id integer,workout_id integer, gym_id integer,primary key(user_id, workout_id, gym_id), foreign key(user_id) references User(user_id), foreign key(gym_id) references Gym(gym_id),foreign key(work_id) references Workout(work_id))");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists Users");
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
        db.insert("Users", null, contentValues);
        db.close();
        return(true);
    }
    public boolean enterGender(String gen, String emailid)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("Update Users set gender = ? where email=?", new String[]{gen,emailid});
        return(true);
    }
    public boolean enterFitnessGoal(String fit_goal, String emailid)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("Update Users set fitness_goal = ? where email=?", new String[]{fit_goal,emailid});
        return(true);
    }
    public boolean userExist(String emailid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res= db.rawQuery("Select * from Users where email= ?", new String[]{emailid});
        if(res.getCount()>0)
        {
            return(true);
        }
        return(false);
    }
    public boolean passWordMatch(String pass, String emailid)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("Select pwd from Users where email=?", new String[]{emailid});
        res.moveToFirst();
        String dbpass= res.getString(0);
        if(pass.equals(dbpass))
        {
            return(true);
        }
        else
        {
            return(false);
        }
    }
    public boolean entryExist(String emailid, String track_date)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res= db.rawQuery("Select * from UserLog where email_id= ? and date_tracked=?", new String[]{emailid, track_date});
        if(res.getCount()>0)
        {
            return(true);
        }
        return(false);
    }
    public int enterWater(String emailid, String track_date, int glasses)
    {
        int total_glasses, existing_glasses=0;
        SQLiteDatabase db= this.getWritableDatabase();
        if(entryExist(emailid, track_date))
        {
            Cursor res= db.rawQuery("Select water_tracked from UserLog where email_id=? and date_tracked=?", new String[]{emailid, track_date});
            res.moveToFirst();
            existing_glasses= res.getInt(0);
            total_glasses=existing_glasses+glasses;
            db.execSQL("Update UserLog set water_tracked=? where email_id=? and date_tracked=?",new String[]{String.valueOf(total_glasses), emailid,track_date});
            return(total_glasses);
        }
        else
        {
            ContentValues contentValues= new ContentValues();
            Random rnd = new Random();
            entry_id = rnd.nextInt(999999);
            contentValues.put("entry_id", entry_id);
            contentValues.put("email_id", emailid);
            contentValues.put("date_tracked", track_date);
            contentValues.put("water_tracked",glasses);
            db.insert("UserLog", null, contentValues);
            db.close();
            return(glasses);
        }
    }

}
