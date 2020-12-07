package com.example.gymtracker.ui.dashboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gymtracker.R;

public class Fullbodyworkout extends AppCompatActivity {
    TextView fb1, fb2, fb3;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullbodyworkout);
        fb1= (TextView)findViewById(R.id.fullbody1);
        fb2= (TextView)findViewById(R.id.fullbody2);
        fb3= (TextView)findViewById(R.id.fullbody3);
        fb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=UBMk30rjy0o"));
                try {
                    Fullbodyworkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
        fb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=g72cA4xccPs&t=1139s"));
                try {
                    Fullbodyworkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });

        fb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=CGmr02bfHUo"));
                try {
                    Fullbodyworkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });


    }
}