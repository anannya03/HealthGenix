package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.gymtracker.ArmsAbsWorkout;
import com.example.gymtracker.R;

public class HomeWorkout extends AppCompatActivity {
    Button fullbody, couple, hiit, arms,  warmups;

    String [] couplearray= new String[] {" Couple Workout 1", "Couple Workout 2", "Couple Workout 3"};
    String [] armsarray= new String[]{"Arms Workout ", "Belly Fat Workout 1", "Belly Fat Workout 2"};
    String [] hiitarray=new String[]{"HIIT Workout 1", "HIIT Workout 2", "HIIT Workout 3"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_workout);

        fullbody= (Button)findViewById(R.id.fullbodybutton);
        couple= (Button)findViewById(R.id.coupleworkoutsbutton);
        hiit= (Button)findViewById(R.id.hiitbutton);
        arms= (Button)findViewById(R.id.armsabsglutesbutton);
        fullbody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeWorkout.this, Fullbodyworkout.class);
                startActivity(intent);
            }
        });
        arms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(HomeWorkout.this, ArmsAbsWorkout.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}