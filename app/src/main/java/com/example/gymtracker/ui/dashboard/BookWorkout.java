package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.gymtracker.R;

import java.util.ArrayList;

public class BookWorkout extends AppCompatActivity {
    static ArrayList<workout_desc> workouts;
    ListView lv;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_workout);
        Bundle bundle= getIntent().getExtras();
        while(bundle!=null)
        {
            email=bundle.getString("email");
        }
        workouts = new ArrayList<workout_desc>();
        workouts.add(new workout_desc("KICK BOXING","Branch: HealthGenix Basavangudi Branch \n Time: 17:00-18:00 \n Date: 6-12-2020"));
        workouts.add(new workout_desc("KICK BOXING","Branch: HealthGenix Basavangudi Branch \n Time: 17:00-18:00 \n Date: 6-12-2020"));
        workouts.add(new workout_desc("KICK BOXING","Branch: HealthGenix Basavangudi Branch \n Time: 17:00-18:00 \n Date: 6-12-2020"));
        workouts.add(new workout_desc("KICK BOXING","Branch: HealthGenix Basavangudi Branch \n Time: 17:00-18:00 \n Date: 6-12-2020"));
        lv = findViewById(R.id.lv);
        CustomAdapter adapter = new CustomAdapter(this, workouts);
        lv.setAdapter(adapter);

    }
}