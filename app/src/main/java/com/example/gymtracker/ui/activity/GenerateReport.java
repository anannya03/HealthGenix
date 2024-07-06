//package com.example.gymtracker.ui.activity;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.Fragment;
//
//import android.os.Build;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import com.example.gymtracker.NavigationMainActivity;
//import com.example.gymtracker.R;
//import com.example.gymtracker.db_classes.DBHelper;
//import com.example.gymtracker.db_classes.User_log;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class GenerateReport extends Fragment {
//    TextView trackCalories;
//    String email;
//    List<User_log> logs;
//    int glass;
//    double burntcal;
//    String date_tracked;
//    String tracklogs="";
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setRetainInstance(true);
//    }
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_activity, container, false);
//        logs= new ArrayList<User_log>();
//        trackCalories=(TextView)view.findViewById(R.id.trackdetails);
//        email= NavigationMainActivity.login_email;
//        DBHelper db;
//        db = new DBHelper(getContext());
//        try {
//            db.createDatabase();
//            db.openDataBase();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        logs= db.getTrackedDetails(email);
//
//        for( User_log ob:logs) {
//            glass = ob.getWater_tracked();
//            burntcal=ob.getBurnt_cal();
//            date_tracked= ob.getDate();
//            tracklogs=tracklogs+" \n\nYou have tracked "+glass+" glasses of water on "+date_tracked+"\n\nYou burnt "+burntcal+" calories "+" on "+date_tracked;
//        }
//        trackCalories.setText(tracklogs);
//        return view;
//    }
//}

package com.example.gymtracker.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;
import com.example.gymtracker.db_classes.User_log;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class GenerateReport extends AppCompatActivity{
    TextView trackCalories;
    String email;
    List<User_log> logs;
    int glass;
    double burntcal;
    String date_tracked;
    String tracklogs="";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generate_report);
        logs= new ArrayList<User_log>();
        trackCalories=(TextView)findViewById(R.id.trackdetails);
        email= NavigationMainActivity.login_email;
        DBHelper db;
        db = new DBHelper(getApplicationContext());
        try {
            db.createDatabase();
            db.openDataBase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        logs= db.getTrackedDetails(email);

        for( User_log ob:logs) {
            glass = ob.getWater_tracked();
            burntcal=ob.getBurnt_cal();
            date_tracked= ob.getDate();
//            String[] dateArray = date_tracked.split("/");
//            String newDatee = dateArray[1];
//            int newdate = Integer.parseInt(newDatee) + 1;
//            date_tracked = dateArray[0] + "/" + String.valueOf(newdate) + "/" + dateArray[2];

            tracklogs=tracklogs+" \n\nYou have tracked "+glass+" glasses of water on "+ date_tracked+"\n\nYou burnt "+burntcal+" calories "+" on "+date_tracked;
        }
        trackCalories.setText(tracklogs);
    }
}
