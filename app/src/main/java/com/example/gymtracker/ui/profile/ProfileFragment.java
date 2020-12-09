package com.example.gymtracker.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;
import com.example.gymtracker.db_classes.User;
import com.example.gymtracker.db_classes.Workouts;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {
    EditText weight, height, bmi, name, age, gender, goal, goalweight;
    String email;
    double  wt, ht, bm;
    int agenumber;
    String namestring, genderstring, goalstring;
    List<User> users;
    Button submitButton;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        email= NavigationMainActivity.login_email;
        DBHelper db;
        db = new DBHelper(getContext());
        try {
            db.createDatabase();
            db.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        users= new ArrayList<User>();
        users= db.getProfileDetails(email);

        for( User ob:users) {
            namestring = ""+ob.getFname()+" "+ob.getLname();
            goalstring= ob.getFitness();
            genderstring=ob.getGen();
            agenumber= ob.getAge();
        }
        name= (EditText)view.findViewById(R.id.editTextName);
        age= (EditText)view.findViewById(R.id.editTextAge);
        gender=(EditText)view.findViewById(R.id.editTextGender);
        goal= (EditText)view.findViewById(R.id.editTextGoal);
        name.setText(""+namestring);
        goal.setText(""+goalstring);
        gender.setText(""+genderstring);
        age.setText(""+agenumber);
        goalweight=(EditText)view.findViewById(R.id.editTextGoalWeight);
        weight= (EditText)view.findViewById(R.id.editTextWeight);
        height=(EditText)view.findViewById(R.id.editTextHeight);
        bmi= (EditText)view.findViewById(R.id.editTextBMI);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(weight.getText()==null )
                {
                    Toast.makeText(getActivity(), "Enter a valid weight", Toast.LENGTH_LONG).show();
                    return;
                }
                if(height.getText()==null)
                {
                    Toast.makeText(getActivity(), "Enter a valid height", Toast.LENGTH_LONG).show();
                    return;
                }
                wt= Double.parseDouble(weight.getText().toString());
                ht= Double.parseDouble(height.getText().toString());
                ht= ht/100;
                bm= wt/(ht*ht);
                bmi.setText(Double.toString(bm));
            }
        });
        submitButton =view.findViewById(R.id.submitProfile);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Your profile is updated.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}