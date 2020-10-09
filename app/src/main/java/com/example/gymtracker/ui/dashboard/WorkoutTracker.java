package com.example.gymtracker.ui.dashboard;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.gymtracker.R;

import java.util.HashMap;

public class WorkoutTracker extends AppCompatActivity {
    Spinner spinnerworkout;
    TextView chooseworkout, entertime, enterweight, cals, totalcals;
    EditText timeentry, bodyweight;
    public static double workout_time, weight;
    public static double calories=0.0, total_calories=0.0, met=0.0;
    Button calculatecalories, resetbutton, trackbutton;
    public static String workout_type;
    String[] exercise = {"Cycling, moderate(leisure)", "Cycling vigorous(uphill)", "Cycling(stationary)", "Circuit training(vigorous)", "Resistance (weight) training – squats, explosive effort", "Resistance (weight) training – multiple exercises, 8-15 reps", "Jumping rope", "Hatha Yoga", "Home activity – cleaning, sweeping, moderate effort", "Home activity – laundry – folding, putting away clothes (incl. walking)", "Gardening – general, moderate effort", "Running moderate speed", "Running high speed", "Walking for exercise – brisk pace (3.5 mph)", "Tennis - singles","Swimming laps – freestyle/crawl light – moderate effort", "Hiking (hills w/10-20lb. load)", "Exercise/activity-based video game – moderate effort (e.g. Wii Fit)", "Video-exercise (DVD/TV) cardio-resistance, moderate effort", "Standing – working on computer / reading / talking on phone", "Dance workout-Zumba", "Dance workout-aerobics"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_tracker);
        ActionBar actionBar=this.getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        chooseworkout=(TextView)findViewById(R.id.chooseworkout);
        entertime=(TextView)findViewById(R.id.entertime);
        enterweight=(TextView)findViewById(R.id.enterweight);
        timeentry=(EditText)findViewById(R.id.timeentry);
        bodyweight=(EditText)findViewById(R.id.bodyweight);
        cals= (TextView)findViewById(R.id.cals);
        totalcals=(TextView)findViewById(R.id.totalcals);
        spinnerworkout= (Spinner)findViewById(R.id.spinnerworkout);
        resetbutton=(Button)findViewById(R.id.resetbutton);
        trackbutton=(Button)findViewById(R.id.trackworkout);
        final HashMap<String, Double> met_vals= new HashMap<String, Double>();
        met_vals.put("Cycling, moderate(leisure)", 8.0);
        met_vals.put("Cycling vigorous(uphill)", 14.0);
        met_vals.put("Cycling(stationary)", 6.8);
        met_vals.put("Circuit training(vigorous)", 8.0);
        met_vals.put("Resistance (weight) training – squats, explosive effort", 5.0);
        met_vals.put("Resistance (weight) training – multiple exercises, 8-15 reps", 3.5);
        met_vals.put("Jumping rope", 12.3);
        met_vals.put("Hatha Yoga", 2.5);
        met_vals.put("Home activity – cleaning, sweeping, moderate effort", 3.5);
        met_vals.put("Home activity – laundry – folding, putting away clothes (incl. walking)", 2.3);
        met_vals.put("Gardening – general, moderate effort", 3.8);
        met_vals.put("Running moderate speed", 9.8);
        met_vals.put("Running high speed", 23.0);
        met_vals.put("Walking for exercise – brisk pace (3.5 mph)", 4.3);
        met_vals.put("Tennis singles", 8.0);
        met_vals.put("Swimming laps – freestyle/crawl light – moderate effort", 5.8);
        met_vals.put("Hiking (hills w/10-20lb. load)", 7.3);
        met_vals.put("Exercise/activity-based video game – moderate effort (e.g. Wii Fit)", 3.8);
        met_vals.put("Video-exercise (DVD/TV) cardio-resistance, moderate effort", 4.0);
        met_vals.put("Standing – working on computer / reading / talking on phone", 1.8);
        met_vals.put("Dance workout-Zumba",8.8);
        met_vals.put("Dance workout- Aerobics", 2.5);
        ArrayAdapter myAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, exercise);
        spinnerworkout.setSelection(0);
        spinnerworkout.setAdapter(myAdapter);
        spinnerworkout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                workout_type = exercise[i];
                met= met_vals.get(exercise[i]);

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        calculatecalories=(Button)findViewById(R.id.calculatecalories);
        calculatecalories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    workout_time = Double.parseDouble(timeentry.getText().toString());

                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Enter a valid workout time", Toast.LENGTH_LONG).show();
                    timeentry.setText(""+0);
                }
                try {
                    weight=Double.parseDouble(bodyweight.getText().toString());

                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Enter a valid weight", Toast.LENGTH_LONG).show();
                    bodyweight.setText(""+0);
                }

                calories= workout_time*(met*3.5*weight)/200;
                total_calories= total_calories+calories;
                Math.ceil(calories);
                Math.ceil(total_calories);
                cals.setText("CALORIES BURNT="+calories);
                totalcals.setText("TOTAL CALORIES BURNT="+total_calories);
            }
        });
        resetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                total_calories=0;
                calories=0;
                cals.setText("");
                totalcals.setText("");
            }
        });
    }
}