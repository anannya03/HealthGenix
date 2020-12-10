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

public class ResetPassword extends AppCompatActivity {
    public Button submit;
    EditText pwd, confirmPwd, oldPwd;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
        email = NavigationMainActivity.login_email;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        submit = (Button) findViewById(R.id.button);
        oldPwd = (EditText) findViewById(R.id.oldPassword);
        DBHelper db;
        db = new DBHelper(getApplicationContext());
        try {
            db.createDatabase();
            db.openDataBase();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String oldPassword = db.getPassword(email );
        if(oldPassword != oldPwd.getText().toString()){
            Toast.makeText(ResetPassword.this, "Enter correct password", Toast.LENGTH_LONG).show();
            return;
        }

        confirmPwd = (EditText) findViewById(R.id.confirmPassword);
        pwd = (EditText) findViewById(R.id.newPassword);
        if(pwd != confirmPwd){
            Toast.makeText(ResetPassword.this, "Confirmed Password is not same as new password", Toast.LENGTH_LONG).show();
            return;
        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password = confirmPwd.getText().toString();
                Toast.makeText(ResetPassword.this, "Submitted", Toast.LENGTH_SHORT).show();
                DBHelper db;
                db = new DBHelper(getApplicationContext());
                try {
                    db.createDatabase();
                    db.openDataBase();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                db.updatePassword(email,password );
                db.close();
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