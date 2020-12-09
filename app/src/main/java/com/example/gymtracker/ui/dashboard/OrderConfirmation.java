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
    String endDate;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_confirmation);
        TextView end = (TextView) findViewById(R.id.end);
        DBHelper db;
        db = new DBHelper(getApplicationContext());
        try {
            db.createDatabase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
//        Bundle bundle= getIntent().getExtras();
//        String branch= bundle.getString("Branch");
        //endDate = db.getEndDate(branch);
   //     if(endDate.equals("null")){
   //         end.setText("Not there");
   //     }
   //     else{
    //        end.setText(endDate);
    //    }


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
        startActivity(intent);
    }
}