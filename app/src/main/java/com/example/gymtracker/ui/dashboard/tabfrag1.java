package com.example.gymtracker.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;

public class tabfrag1 extends Fragment {
    Button water_track, food_track, workout_track;
    @Override
     public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        water_track= (Button)view.findViewById(R.id.water);
        water_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),TrackWater.class);
                startActivity(intent);
            }
        });
        food_track= (Button)view.findViewById(R.id.food);
        food_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), FoodTracker.class);
                startActivity(intent);
            }
        });
        workout_track= (Button)view.findViewById(R.id.workout);
        workout_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), WorkoutTracker.class);
                startActivity(intent);

            }
        });
        Toast.makeText(getActivity().getApplicationContext(), ""+ NavigationMainActivity.login_email, Toast.LENGTH_LONG);

        return(view);
    }

}
