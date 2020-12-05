package com.example.gymtracker.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;

public class tabfrag3 extends Fragment {
    String goal;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab3, container, false);
        String email = NavigationMainActivity.login_email;
        DBHelper db;
        db = new DBHelper(getActivity().getApplicationContext());
        try {
            db.createDatabase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        goal = db.extractGoal(email);
        imageView = (ImageView) view.findViewById(R.id.dietPlan);
        if(goal.equals("Lose Weight")){
            imageView.setImageResource(R.drawable.weight_loss);
        }
        else if(goal.equals("Gain Muscle")){
            imageView.setImageResource(R.drawable.gain_muscle);
        }
        else{
            imageView.setImageResource(R.drawable.get_fit);
        }
        return (view);
    }
}
