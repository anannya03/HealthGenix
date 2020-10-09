package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.gymtracker.R;

public class FoodTracker extends AppCompatActivity {
    TextView enterdetails, foodtracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_tracker);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        foodtracker=(TextView)findViewById(R.id.foodtracker);
        String linkText = "<a href='https://www.nutritionix.com/dashboard'>FOOD TRACKER</a>";
        foodtracker.setText(Html.fromHtml(linkText));
        foodtracker.setTextColor(Color.parseColor("#000000"));
        foodtracker.setTextSize(30);
        foodtracker.setMovementMethod(LinkMovementMethod.getInstance());

    }
}