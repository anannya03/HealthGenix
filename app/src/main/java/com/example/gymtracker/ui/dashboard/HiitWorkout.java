package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gymtracker.R;

import org.w3c.dom.Text;

public class HiitWorkout extends AppCompatActivity {
    TextView h1, h2, h3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiit_workout);
        h1= (TextView)findViewById(R.id.hiit1);
        h2= (TextView)findViewById(R.id.hiit2);
        h3= (TextView)findViewById(R.id.hiit3);
        h1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=ml6cT4AZdqI"));
                try {
                    HiitWorkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
        h2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=bdCX8Nb_2Mg"));
                try {
                    HiitWorkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });

        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=Mvo2snJGhtM"));
                try {
                    HiitWorkout.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });

    }
}