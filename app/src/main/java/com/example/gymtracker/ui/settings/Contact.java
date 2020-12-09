package com.example.gymtracker.ui.settings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymtracker.NavigationMainActivity;
import com.example.gymtracker.R;
import com.example.gymtracker.db_classes.DBHelper;

public class Contact extends AppCompatActivity {
    EditText contactphone, emailsettings;
    String phno;


    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        contactphone= (EditText) findViewById(R.id.settingsphone);
        emailsettings= (EditText)findViewById(R.id.settingsemail);
        final String email= NavigationMainActivity.login_email;
        emailsettings.setText(email);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phno= contactphone.getText().toString();
                DBHelper db;
                db = new DBHelper(getApplicationContext());
                try {
                    db.createDatabase();
                    db.openDataBase();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                db.setPhno(email, phno);
                Toast.makeText(Contact.this, "Phone number updated", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }
}