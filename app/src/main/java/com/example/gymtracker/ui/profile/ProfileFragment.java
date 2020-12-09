package com.example.gymtracker.ui.profile;
import android.content.Intent;
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
    EditText weight, height, bmi, fname, lname, age, gender, goal;
    String email;
    double  wt, ht, bm;
    int agenumber;
    String fnamestring, lnamestring, genderstring, goalstring;
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
            fnamestring = ob.getFname();
            lnamestring=ob.getLname();
            goalstring= ob.getFitness();
            genderstring=ob.getGen();
            agenumber= ob.getAge();
        }
        fname= (EditText)view.findViewById(R.id.editTextFirstName);
        lname=(EditText)view.findViewById(R.id.editTextLastName);
        age= (EditText)view.findViewById(R.id.editTextAge);
        gender=(EditText)view.findViewById(R.id.editTextGender);
        goal= (EditText)view.findViewById(R.id.editTextGoal);
        fname.setText(""+fnamestring);
        lname.setText(""+lnamestring);
        goal.setText(""+goalstring);
        gender.setText(""+genderstring);
        age.setText(""+agenumber);

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
                fnamestring= fname.getText().toString();
                lnamestring= lname.getText().toString();
                agenumber= Integer.parseInt(age.getText().toString());
                genderstring= gender.getText().toString();
                wt= Double.parseDouble(weight.getText().toString());
                ht= Double.parseDouble(height.getText().toString());
                bm= Double.parseDouble(bmi.getText().toString());
                DBHelper db1;
                db1 = new DBHelper(getContext());
                try {
                    db1.createDatabase();
                    db1.openDataBase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                db1.updateProfile(email, fnamestring, lnamestring, agenumber, ht, wt, bm, genderstring);
                Toast.makeText(getActivity(), "Your profile is updated.", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}