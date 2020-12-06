package com.example.gymtracker.ui.dashboard;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;

public class tabfrag2 extends Fragment {
    Button homeworkout, bookworkout;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab2, container, false);
        homeworkout=(Button)view.findViewById(R.id.homeworkout);
        bookworkout=(Button)view.findViewById(R.id.bookworkout);
        homeworkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), HomeWorkout.class);
                startActivity(intent);            }
        });
        Toast.makeText(getActivity().getApplicationContext(), ""+ NavigationMainActivity.login_email, Toast.LENGTH_LONG);
        bookworkout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(), BookWorkout.class);
                intent.putExtra("email", NavigationMainActivity.login_email);
                startActivity(intent);
            }
        });
        return(view);

        }
}
