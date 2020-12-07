package com.example.gymtracker.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;


public class GymBrowseRaipur extends AppCompatActivity {
    //private static final String TAG = GymBrowseRaipur.class.getSimpleName();
    TextView textView;
    String branch;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gym_browse_raipur);

        // Payment button created by you in XML layout
        Button button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView14);
        Bundle bundle= getIntent().getExtras();
        branch= bundle.getString("Branch");
        DBHelper db;
        db = new DBHelper(getApplicationContext());
        try {
            db.createDatabase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String address = db.getAddress(branch);
        textView.setText(address);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentPage();
            }
        });

    }

    void PaymentPage() {
        Intent intent = new Intent(this, PaymentActivityOneMonth.class);
        intent.putExtra("Branch", branch);
        startActivity(intent);
    }
}