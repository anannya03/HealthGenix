package com.example.gymtracker.db_classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
public class  DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="Health.db";
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
                    = SQLiteDatabase.openDatabase(
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

    public void createUser(User user)
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
    }
    public void enterGender(String gen, String emailid)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("Update Users set gender = ? where email=?", new String[]{gen,emailid});
    }
    public void enterFitnessGoal(String fit_goal, String emailid)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        db.execSQL("Update Users set fitness_goal = ? where email=?", new String[]{fit_goal,emailid});
    }
    public boolean userExist(String emailid)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res= db.rawQuery("Select * from Users where email= ?", new String[]{emailid});
        if(res.getCount()>0)
        {
            return(true);
        }
        res.close();
        db.close();
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
        res.close();

        return(false);
    }
    public boolean membershipExists(String emailid) throws ParseException {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res= db.rawQuery("Select mem_end_date from users where email=?", new String[]{emailid});
        res.moveToFirst();
        String endingdate= res.getString(0);
        if(endingdate!=null)
        {
            String today= "2020-12-11";
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            Date dt1= sdf.parse(today);
            Date dt2= sdf.parse(endingdate);
            assert dt2 != null;
            if(dt2.after(dt1) || dt1.equals(dt2))
            {
                return(true);
            }
            if(dt2.before(dt1))
            {
                return(false);
            }
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
    public List<Workouts> getWorkouts(String branch)
    {
        List<Workouts> works= new ArrayList<Workouts>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("select   workout.workout_id, workout.work_name, workout.work_date, workout.work_time, Gym_details.branch_name,workout.booked, workout.capacity from Gym_details, workout where Gym_details.gym_id= workout.gym_id and  Gym_details.branch_name=?", new String[]{branch});
        res.moveToFirst();
        while(!res.isAfterLast())
        {
            Workouts work= new Workouts();
            work.setWork_id(res.getInt(0));
            work.setWork_name(res.getString(1));
            work.setDate(res.getString(2));
            work.setTime(res.getString(3));
            work.setBranch(res.getString(4));
            work.setBooked(res.getInt(5));
            work.setCap(res.getInt(6));
            works.add(work);
            res.moveToNext();
        }
        return(works);
    }
    public void updateBooking(int workout_id, int booked)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update Workout set booked = ? where workout_id=?", new String[]{String.valueOf(booked),String.valueOf(workout_id)});
    }

    public String getAddress(String branch){
        String address;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("Select full_add from Gym_details where branch_name=?", new String[]{branch});
        res.moveToFirst();
        address = res.getString(0);
        res.close();
        return address;
    }
    public boolean workoutBooked(String email, int work_id)
    {
        SQLiteDatabase dbReadable=this.getReadableDatabase();
        Cursor res = dbReadable.rawQuery("Select email, work_id from Workout_booking where email=? and work_id=?", new String[]{email, String.valueOf(work_id)});
        if(res.getCount()>0)
        {
            return(true);
        }
        return(false);
    }
    public void insertWorkoutBooking(String emailid, int work_id, String branch)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Random rnd = new Random();
        entry_id = rnd.nextInt(999999);
        contentValues.put("work_id", work_id);
        contentValues.put("email", emailid);
        contentValues.put("gym_branch", branch);
        db.insert("Workout_booking", null, contentValues);
        db.close();
    }
    public void updateUsersSetMemDate(String memStart, String memEnd, String emailId)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("Update Users set mem_start_date  = ? , mem_end_date = ? where email=?",
                new String[]{memStart, memEnd , emailId});
    }
    public List<User> getProfileDetails(String emailid)
    {
        List<User> users= new ArrayList<User>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res= db.rawQuery("select fname, lname, age, gender, fitness_goal from Users where email=?", new String[]{emailid});
        res.moveToFirst();
        while(!res.isAfterLast())
        {
            User user= new User();
            user.setFname(res.getString(0));
            user.setLname(res.getString(1));
            user.setAge(res.getInt(2));
            user.setGen(res.getString(3));
            user.setFitness(res.getString(4));
            users.add(user);
            res.moveToNext();
        }
        return(users);
    }

    public String getFname(String emailid){
        String fname;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res = db.rawQuery("Select fname from Users where email=?", new String[]{emailid});
        res.moveToFirst();
        fname = res.getString(0);
        return fname;
    }

}




