package com.example.gymtracker.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;
import com.example.gymtracker.db_classes.User;
import com.example.gymtracker.db_classes.User_log;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ActivityFragment extends Fragment {
    TextView memstart, memend, trackdetails;
    String memstartstring, memendstring, email;
    List<User_log> logs;
    int glass;
    double burntcal;
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
        logs= new ArrayList<User_log>();
        memend=(TextView)view.findViewById(R.id.memenddate);
        trackdetails=(TextView)view.findViewById(R.id.trackdetails);
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
        memstart.setText("You took your HealthGenix membership on  "+memstartstring);
        memend.setText("Your membership will end on "+memendstring);
        logs= db.getTrackedDetails(email);

        for( User_log ob:logs) {
            glass = ob.getWater_tracked();
            burntcal=ob.getBurnt_cal();
            date_tracked= ob.getDate();
            tracklogs=tracklogs+" \n\nYou have tracked "+glass+" glasses of water on "+date_tracked+"\n\nYou burnt "+burntcal+" calories "+" on "+date_tracked;
        }
        trackdetails.setText(tracklogs);
        return view;
    }
}
