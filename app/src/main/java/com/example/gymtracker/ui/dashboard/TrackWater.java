package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.User_dbhelper;


import java.util.Calendar;

public class TrackWater extends AppCompatActivity {
    EditText eText;
    EditText water;
    Button plus, minus, track;
    int water_tracked;
    String date_tracked;
    DatePickerDialog picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_water);
        water= (EditText)findViewById(R.id.wateredittext);
        water_tracked=Integer.parseInt(water.getText().toString());
        plus= (Button)findViewById(R.id.plusbutton);
        minus= (Button)findViewById(R.id.minusbutton);
        track= (Button)findViewById(R.id.trackwater);
        final Calendar myCalendar = Calendar.getInstance();
        eText= (EditText) findViewById(R.id.date);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(TrackWater.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis());
                picker.show();
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int track_year = picker.getDatePicker().getYear();
                int track_date = picker.getDatePicker().getDayOfMonth();
                int track_month = picker.getDatePicker().getMonth();
                date_tracked=""+track_year+"-"+track_month+"-"+track_date;
                int glasses= Integer.parseInt(water.getText().toString());
                String emailid= NavigationMainActivity.login_email;
                User_dbhelper user_dbhelper= new User_dbhelper(getApplicationContext());
                int gl=user_dbhelper.enterWater(emailid, date_tracked, glasses);
                Toast.makeText(getApplicationContext(), "You have tracked "+gl+ " glasses of water on "+date_tracked, Toast.LENGTH_LONG).show();
            }
        });
    }
    public void increase(View view)
    {
        water_tracked++;
        water.setText(""+water_tracked);
    }
    public void decrease(View view)
    {
        if(water_tracked>0) {
            water_tracked--;
        }
        water.setText(""+water_tracked);
    }

}