package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;

public class OrderConfirmation extends AppCompatActivity {
    String endDate, startDate;
    String email;
    Button button;
    TextView end;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
         end = (TextView) findViewById(R.id.end);
        Bundle bundle= getIntent().getExtras();
        email= bundle.getString("email");
        startDate= bundle.getString("date_start");
        endDate=bundle.getString("date_end");
        DBHelper db;
        db = new DBHelper(getApplicationContext());
        try {
            db.createDatabase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        db.updateUsersSetMemDate(startDate, endDate, email);
        end.setText("Your membership ends on "+ endDate);
        button = (Button) findViewById(R.id.goBack);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProcessingPage();
            }
        });
    }
    private void ProcessingPage() {
        Intent intent = new Intent(OrderConfirmation.this, NavigationMainActivity.class);
        intent.putExtra("email", email);
        startActivity(intent);
    }
}