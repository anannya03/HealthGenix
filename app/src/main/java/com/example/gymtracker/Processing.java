package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Processing extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private Handler handler = new Handler();
    private android.widget.ImageView ImageView;
    private int progress;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        Bundle bundle= getIntent().getExtras();
        email= bundle.getString("email");

        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < progressBar.getMax()) {
                    progressStatus += 5;
                    //Update progress bar with completion of operation
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        // Sleep for 300 milliseconds.
                        //Just to display the progress slowly
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.post(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(Processing.this, NavigationMainActivity.class);
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }
                });
            }
        }).start();

    }
}