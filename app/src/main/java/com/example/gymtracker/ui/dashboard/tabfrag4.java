package com.example.gymtracker.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.gymtracker.R;

public class tabfrag4 extends Fragment {
    public static final String []cities={"Bangalore", "Raipur", "Pune"};
    public static final String[] bangalore= {"HealthGenix Basavanagudi branch", "HealthGenix Jayanagar branch" };
    public static final String[] raipur={"HealthGenix Gitanjali Nagar branch"};
    public static final String[] pune= {"HealthGenix Balewadi branch"};
    Spinner city, branch;
    Button gobutton;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab4, container, false);
        city= (Spinner)view.findViewById(R.id.city);
        branch=(Spinner)view.findViewById(R.id.branch);
        ArrayAdapter ad1=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, cities);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(ad1);
        city.setSelection(0);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ArrayAdapter ad2;
                switch(i)
                {
                    case 0:
                        ad2= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, bangalore);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        break;
                    case 1:
                        ad2= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, raipur);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        break;
                    case 2:
                        ad2= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, pune);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        gobutton= (Button)view.findViewById(R.id.gobutton);
        gobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String br=branch.getSelectedItem().toString();

            }
        });
        return(view);
    }
}
