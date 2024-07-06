package com.example.gymtracker.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;
import com.example.gymtracker.db_classes.User_log;

import java.util.ArrayList;

public class ActivityFragment extends Fragment {
    TextView memstart, memend;
    String memstartstring, memendstring, email;
    String date_tracked;
    String tracklogs="";
    
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activity, container, false);
        memstart=(TextView)view.findViewById(R.id.memstartdate);
       // logs= new ArrayList<User_log>();
        memend=(TextView)view.findViewById(R.id.memenddate);
        //trackdetails=(TextView)view.findViewById(R.id.trackdetails);
        email= NavigationMainActivity.login_email;
        DBHelper db;
        db = new DBHelper(getContext());
        try {
            db.createDatabase();
            db.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        memstartstring=db.getMemStart(email);
        memendstring=db.getMemEnd(email);
        memstart.setText("You took your HealthGenix membership on  "+ memstartstring);
        memend.setText("Your membership will end on "+memendstring);

        Button button = (Button) view.findViewById(R.id.generateReport);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), GenerateReport.class);
                startActivity(intent);
            }
        });
//        logs= db.getTrackedDetails(email);
//
//        for( User_log ob:logs) {
//            glass = ob.getWater_tracked();
//            burntcal=ob.getBurnt_cal();
//            date_tracked= ob.getDate();
//            tracklogs=tracklogs+" \n\nYou have tracked "+glass+" glasses of water on "+date_tracked+"\n\nYou burnt "+burntcal+" calories "+" on "+date_tracked;
//        }
//        trackdetails.setText(tracklogs);
        return view;
    }
}
