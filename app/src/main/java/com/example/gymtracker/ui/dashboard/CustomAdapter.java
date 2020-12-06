
package com.example.gymtracker.ui.dashboard;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymtracker.R;
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
                Toast.makeText(context,"You have booked"+ data.getWork_name(),Toast.LENGTH_LONG).show();

            }
        });
        return view;

    }
}
