package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;

public class BookWorkoutBranch extends AppCompatActivity {
    public static final String []cities={"Bangalore", "Raipur", "Pune"};
    public static final String[] bangalore= { "HealthGenix Basavangudi Branch", "HealthGenix Jayanagar Branch" };
    public static final String[] raipur={"HealthGenix Gitanjali Nagar Branch"};
    public static final String[] pune= {"HealthGenix Balewadi Branch"};
    Spinner city, branch;
    String cityChosen, branchChosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_workout_branch);
        city= (Spinner)findViewById(R.id.city);
        branch=(Spinner)findViewById(R.id.branch);
        Button button = (Button)findViewById(R.id.gobutton);

        ArrayAdapter ad1=new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, cities);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(ad1);
        city.setSelection(0);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityChosen = cities[i];
                ArrayAdapter ad2;
                switch(i)
                {
                    case 0:
                        ad2= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, bangalore);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                branchChosen = bangalore[i];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 1:
                        ad2= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, raipur);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                branchChosen = raipur[i];
                            }
                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });
                        break;
                    case 2:
                        ad2= new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, pune);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                branchChosen = pune[i];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BookWorkoutBranch.this, BookWorkout.class);
                String email= NavigationMainActivity.login_email;
                intent.putExtra("Email", email);
                intent.putExtra("Branch", branchChosen);
                startActivity(intent);
            }
        });

    }
}