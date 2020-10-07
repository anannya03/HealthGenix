package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

    }
    private Button loginbutton;
    public void enterDashboard(View view)
    {
        loginbutton=(Button)findViewById(R.id.loginbutton);
        Intent intent= new Intent(LoginPage.this, NavigationMainActivity.class);
        startActivity(intent);
    }
}