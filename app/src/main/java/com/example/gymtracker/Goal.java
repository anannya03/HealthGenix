package com.example.gymtracker;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.gymtracker.db_classes.User_dbhelper;

public class Goal extends AppCompatActivity {
    Button fat;
    Button fit;
    Button muscle;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        Bundle bundle=getIntent().getExtras();
        email= bundle.getString("email");

        fat = (Button) findViewById(R.id.loseWeight);
        fat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openLoseWeight();
            }
        });

        fit = (Button) findViewById(R.id.fit);
        fit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openFit();
            }
        });

        muscle = (Button) findViewById(R.id.muscle);
        muscle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openMuscle();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void openLoseWeight(){
        Toast.makeText(this, "Your goal is to lose Weight", Toast.LENGTH_SHORT).show();
        User_dbhelper db= new User_dbhelper(getApplicationContext());
        db.enterFitnessGoal("Lose Weight", email);
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
    public void openFit(){
        Toast.makeText(this, "Your goal is to get fitter", Toast.LENGTH_SHORT).show();
        User_dbhelper db= new User_dbhelper(getApplicationContext());
        db.enterFitnessGoal("Get Fit", email);

        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
    public void openMuscle(){
        Toast.makeText(this, "Your goal is to gain muscles", Toast.LENGTH_SHORT).show();
        User_dbhelper db= new User_dbhelper(getApplicationContext());
        db.enterFitnessGoal("Gain Muscle", email);
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
}