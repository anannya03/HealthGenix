package com.example.gymtracker.db_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.gymtracker.db_classes.User;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="HealthGenix.db";
    public static final int DATABASE_VERSION=1;
    public static String DB_PATH="";
    public long user_id;
    public int entry_id;
    private SQLiteDatabase myDataBase;
    private SQLiteOpenHelper sqLiteOpenHelper;
    private final Context myContext;
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null,DATABASE_VERSION );
        myContext=context;
        DB_PATH=context.getDatabasePath(DATABASE_NAME).toString();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        }
    private boolean checkDataBase()
    {
        SQLiteDatabase checkDB = null;
        try {
            String myPath = DB_PATH;
            checkDB
                    = SQLiteDatabase
                    .openDatabase(
                            myPath, null,
                            SQLiteDatabase.OPEN_READONLY);
        }
        catch (SQLiteException e) {
            Log.e("message", "" + e);
        }
        if (checkDB != null) {
            checkDB.close();
        }
        return checkDB != null;
    }
    private void copyDataBase()
            throws IOException
    {
        // Open your local db as the input stream
        InputStream myInput
                = myContext.getAssets()
                .open(DATABASE_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH;

        // Open the empty db as the output stream
        OutputStream myOutput
                = new FileOutputStream(outFileName);

        // transfer bytes from the
        // inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public void createDatabase() throws IOException
    {
        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
        }
        else {
            this.getWritableDatabase();
            try {
                copyDataBase();
            }
            catch (IOException e) {
                throw new Error(
                        "Error copying database");
            }
        }
    }
    public void openDataBase()
            throws SQLException
    {
        // Open the database
        String myPath = DB_PATH;
        myDataBase = SQLiteDatabase
                .openDatabase(
                        myPath, null,
                        SQLiteDatabase.OPEN_READONLY);
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
    public int enterWater(String emailid, String track_date, int glasses) {
        int total_glasses, existing_glasses = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        if (entryExist(emailid, track_date)) {
            Cursor res = db.rawQuery("Select water_tracked from UserLog where email_id=? and date_tracked=?", new String[]{emailid, track_date});
            res.moveToFirst();
            existing_glasses = res.getInt(0);
            total_glasses = existing_glasses + glasses;
            db.execSQL("Update UserLog set water_tracked=? where email_id=? and date_tracked=?", new String[]{String.valueOf(total_glasses), emailid, track_date});
            return (total_glasses);
        } else {
            ContentValues contentValues = new ContentValues();
            Random rnd = new Random();
            entry_id = rnd.nextInt(999999);
            contentValues.put("entry_id", entry_id);
            contentValues.put("email_id", emailid);
            contentValues.put("date_tracked", track_date);
            contentValues.put("water_tracked", glasses);
            db.insert("UserLog", null, contentValues);
            db.close();
            return (glasses);
        }
    }
        public double enterCalories(String emailid, String track_date, double cals)
        {
            double total_cals, existing_cals = 0;
            SQLiteDatabase db = this.getWritableDatabase();
            if (entryExist(emailid, track_date)) {
                Cursor res = db.rawQuery("Select burnt_cal from UserLog where email_id=? and date_tracked=?", new String[]{emailid, track_date});
                res.moveToFirst();
                existing_cals = res.getInt(0);
                total_cals = existing_cals + cals;
                db.execSQL("Update UserLog set burnt_cal=? where email_id=? and date_tracked=?", new String[]{String.valueOf(total_cals), emailid, track_date});
                return (total_cals);
            } else {
                ContentValues contentValues = new ContentValues();
                Random rnd = new Random();
                entry_id = rnd.nextInt(999999);
                contentValues.put("entry_id", entry_id);
                contentValues.put("email_id", emailid);
                contentValues.put("date_tracked", track_date);
                contentValues.put("burnt_cal", cals);
                db.insert("UserLog", null, contentValues);
                db.close();
                return (cals);
            }
        }
    public String extractGoal(String emailid){
        String goal;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("Select fitness_goal from Users where email=?", new String[]{emailid});
        res.moveToFirst();
        goal = res.getString(0);
        return goal;
    }
    public List<Workouts> getWorkouts(String emailid)
    {
        List<Workouts> works= new ArrayList<Workouts>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("select workout.work_name, workout.work_date, workout.work_time, Gym_details.branch_name from Gym_details, workout, users where Gym_details.gym_id= workout.gym_id and users.gym_id= workout.gym_id and users.email= ?;", new String[]{emailid});
        res.moveToFirst();
        while(!res.isAfterLast())
        {
            Workouts work= new Workouts();
            work.setWork_name(res.getString(0));
            work.setDate(res.getString(1));
            work.setTime(res.getString(2));
            work.setBranch(res.getString(3));
            works.add(work);
            res.moveToNext();
        }
        return(works);
    }
    }


