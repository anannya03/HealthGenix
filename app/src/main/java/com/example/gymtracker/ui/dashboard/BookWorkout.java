package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;
import com.example.gymtracker.db_classes.Workouts;

import java.util.ArrayList;
import java.util.List;

public class BookWorkout extends AppCompatActivity {
    static ArrayList<workout_desc> workouts;
    List<Workouts> tempworks;
    ListView lv;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_workout);
        email= NavigationMainActivity.login_email;
        //need to extract gym id and display those workouts
        DBHelper db;
        db = new DBHelper(getApplicationContext());
        try {
            db.createDatabase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        tempworks= db.getWorkouts(email);
        workouts= new ArrayList<workout_desc>();
        for( Workouts ob:tempworks)
        {
            String name= ob.getWork_name();
            String date= ob.getDate();
            String time= ob.getTime();
            String  branch= ob.getBranch();
            workouts.add(new workout_desc(name, "Branch"+branch+"\n Date:"+date+"\nTime:"+time));
        }
        lv = findViewById(R.id.lv);
        CustomAdapter adapter = new CustomAdapter(this, workouts);
        lv.setAdapter(adapter);

    }
}