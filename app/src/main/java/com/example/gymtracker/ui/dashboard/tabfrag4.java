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
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;

public class tabfrag4 extends Fragment {
    TextView textView;
    String branch;
    String email;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab4, container, false);
        Button button = (Button) view.findViewById(R.id.button);
        Button three = (Button) view.findViewById(R.id.button1);
        Button six = (Button)view. findViewById(R.id.button2);
        textView = (TextView) view.findViewById(R.id.textView14);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentPage();
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentPageOneYear();
            }
        });
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaymentPageSixMonths();
            }
        });
        return(view);
    }
    private void PaymentPageSixMonths() {
        Intent intent = new Intent(getActivity(), PaymentActivitySixMonths.class);
        intent.putExtra("Branch", branch);
        intent.putExtra("Email", email);
        startActivity(intent);
    }
    private void PaymentPageOneYear() {
        Intent intent = new Intent(getActivity(), PaymentActivityOneYear.class);
        intent.putExtra("Email", email);
        intent.putExtra("Branch", branch);
        startActivity(intent);
    }

    void PaymentPage() {
        Intent intent = new Intent(getActivity(), PaymentActivityOneMonth.class);
        intent.putExtra("Email", email);
        intent.putExtra("Branch", branch);
        startActivity(intent);
    }

}
