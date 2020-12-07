package com.example.gymtracker.ui.dashboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gymtracker.R;

public class ArmsAbsWorkout extends AppCompatActivity {
    TextView aa1, aa2, aa3;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arms_abs_workout);
        aa1= (TextView)findViewById(R.id.armsabs1);
        aa2= (TextView)findViewById(R.id.armsabs2);
        aa3= (TextView)findViewById(R.id.armsabs3);
        aa1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=l0CwCvJbGZI"));
                try {
                    ArmsAbsWorkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
        aa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=yL_dE81O_mw"));
                try {
                    ArmsAbsWorkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });

        aa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=ohgLmY19jNg"));
                try {
                    ArmsAbsWorkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });

    }
}