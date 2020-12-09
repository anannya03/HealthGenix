package com.example.gymtracker.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;

import org.w3c.dom.Text;

public class Thankyou extends AppCompatActivity {
    TextView navigate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle= getIntent().getExtras();
        final String email= bundle.getString("Email");
        setContentView(R.layout.activity_thankyou);

        navigate=(TextView)findViewById(R.id.mainpage);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Thankyou.this, NavigationMainActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });

    }
}