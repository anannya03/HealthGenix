package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.gymtracker.R;

public class HomeWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_workout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}