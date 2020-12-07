package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class coupleworkouts extends AppCompatActivity {
    TextView c1, c2, c3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupleworkouts);
        c1= (TextView)findViewById(R.id.couple1);
        c2= (TextView)findViewById(R.id.couple2);
        c3= (TextView)findViewById(R.id.couple3);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=_PoBCFGRcZ8"));
                try {
                    coupleworkouts.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=TxfHWrEiX8w"));
                try {
                    coupleworkouts.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://www.youtube.com/watch?v=d4nZy4maiIg"));
                try {
                    coupleworkouts.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });

    }
}