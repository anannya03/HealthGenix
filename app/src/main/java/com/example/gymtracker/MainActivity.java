package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    private Button loginbutton;
    public void enterDashboard(View view)
    {
        loginbutton=(Button)findViewById(R.id.loginbutton);
        Intent intent= new Intent(MainActivity.this, Dashboard.class);
        startActivity(intent);
    }
}