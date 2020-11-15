package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStartedJoin extends AppCompatActivity {
    private Button button;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_join);
        Bundle bundle= getIntent().getExtras();
        email=bundle.getString("email");
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openGender();
            }
        });
    }
    public void openGender(){

        Intent intent = new Intent(this, Gender.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }

}