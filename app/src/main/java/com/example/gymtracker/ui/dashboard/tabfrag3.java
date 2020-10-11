package com.example.gymtracker.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.gymtracker.R;

public class tabfrag3 extends Fragment {
    Button homeworkout, bookworkout;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        homeworkout=(Button)view.findViewById(R.id.homeworkout);
        bookworkout=(Button)view.findViewById(R.id.bookworkout);
        homeworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), HomeWorkout.class);
                startActivity(intent);


            }
        });

        return (view);
    }
}
