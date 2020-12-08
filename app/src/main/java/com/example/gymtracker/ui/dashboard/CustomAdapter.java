
package com.example.gymtracker.ui.dashboard;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;
import com.example.gymtracker.ui.dashboard.workout_desc;

import java.util.ArrayList;

class Holder {
    TextView title;
    TextView desc;
    Button book;
}
public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<workout_desc> workout;
    public CustomAdapter(Context context, ArrayList<workout_desc> workout)
    {
        this.context=context;
        this.workout=workout;
    }
    @Override
    public int getCount() {
        return(workout.size());
    }

    @Override
    public Object getItem(int position) {
        return(workout.get(position));
    }

    @Override
    public long getItemId(int position) {
        return(position);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final Holder holder;
        final workout_desc data = BookWorkout.workouts.get(position);
        if (view == null) {
            holder = new Holder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_desc, null);
            view.setTag(holder);
            holder.title = view.findViewById(R.id.Title);
            holder.desc = view.findViewById(R.id.description);
            holder.book = view.findViewById(R.id.book);
        }
        else{
            holder = (Holder) view.getTag();
        }
        holder.title.setText (data.getWork_name());
        holder.desc.setText (data.getDesc());
        holder.book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int booked = data.getBooked();
                int work_id = data.getWork_id();
                int cap = data.getCap();
                String branch = data.getBranch();
                String email = NavigationMainActivity.login_email;
                DBHelper db;
                db = new DBHelper(context);
                try {
                    db.createDatabase();
                    db.openDataBase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (db.workoutBooked(email, work_id)) {
                    Toast.makeText(context, "You have already made a booking for this workout! ", Toast.LENGTH_LONG).show();
                } else {
                    if (booked >= cap) {
                        Toast.makeText(context, "Sorry, This workout just reached its limit. Try booking another one", Toast.LENGTH_LONG).show();
                    } else {
                        booked = booked + 1;
                        DBHelper db1;
                        db1 = new DBHelper(context);
                        try {
                            db1.createDatabase();
                            db1.openDataBase();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        db1.updateBooking(work_id, booked);
                        db1.insertWorkoutBooking(email, work_id, branch);
                        holder.book.setBackgroundColor(Color.parseColor("#696969"));
                        holder.book.setClickable(false);
                        holder.book.setEnabled(false);
                        Intent intent = new Intent(context, Thankyou.class);
                        context.startActivity(intent);
                    }
                }
            }
        });
        return view;

    }
}
