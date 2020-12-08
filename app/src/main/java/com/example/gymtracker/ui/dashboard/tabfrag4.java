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

import androidx.fragment.app.Fragment;

import com.example.gymtracker.R;

public class tabfrag4 extends Fragment {
    public static final String []cities={"Bangalore", "Raipur", "Pune"};
    public static final String[] bangalore= { "HealthGenix Jayanagar Branch", "HealthGenix Basavangudi Branch" };
    public static final String[] raipur={"HealthGenix Gitanjali Nagar Branch"};
    public static final String[] pune= {"HealthGenix Balewadi Branch"};
    Spinner city, branch;
    String cityChosen, branchChosen;

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
        Button button = (Button)view.findViewById(R.id.gobutton);

        ArrayAdapter ad1=new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, cities);
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(ad1);
        city.setSelection(0);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cityChosen = cities[i];
                ArrayAdapter ad2;
                switch(i)
                {
                    case 0:
                        ad2= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, bangalore);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                branchChosen = bangalore[i];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 1:
                        ad2= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, raipur);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                branchChosen = raipur[i];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                    case 2:
                        ad2= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, pune);
                        ad2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        branch.setAdapter(ad2);
                        branch.setSelection(0);
                        branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                branchChosen = pune[i];
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), GymBrowse.class);
                intent.putExtra("Branch", branchChosen);
                startActivity(intent);
            }
        });

        return(view);
    }
}
