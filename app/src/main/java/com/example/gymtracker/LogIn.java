package com.example.gymtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gymtracker.db_classes.DBHelper;

public class LogIn extends AppCompatActivity {
    Button logIn;
    TextView joinUs;
    EditText email, pass;
    String email_text, pass_text;
    TextView forgotPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        email=(EditText) findViewById(R.id.editTextEmailAddress);
        pass= (EditText)findViewById(R.id.editTextPassword);
        logIn = (Button) findViewById(R.id.logIn);
        logIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email id", Toast.LENGTH_LONG).show();
                }
                else if(isEmpty(pass))
                {
                    Toast.makeText(getApplicationContext(),"Enter password", Toast.LENGTH_LONG).show();
                }
                else {
                    email_text = email.getText().toString();
                    pass_text= pass.getText().toString();
                    DBHelper db;
                    db = new DBHelper(getApplicationContext());
                    try {
                        db.createDatabase();
                        db.openDataBase();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }

                    if(!db.userExist(email_text))
                    {
                        Toast.makeText(getApplicationContext(),"The user does not exist. Try with a different email id or Sign Up as a new user", Toast.LENGTH_LONG).show();
                        email.setText("");
                    }
                    else
                    {
                        if(db.passWordMatch(pass_text,email_text))
                        {
                            Toast.makeText(getApplicationContext(), "Authentication successful", Toast.LENGTH_LONG).show();
                            openLogIn();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Wrong password", Toast.LENGTH_LONG).show();
                            pass.setText("");
                        }
                    }
                }
            }
        });
        joinUs = (TextView) findViewById(R.id.joinUsText);
        joinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openJoinUs();
            }
        });
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openForgotPassword();
            }
        });
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    private void openForgotPassword() {
        Intent intent = new Intent(this, ForgottenPassword.class);
        startActivity(intent);
    }
    private void openJoinUs() {
        Intent intent = new Intent(this, JoinUs.class);
        startActivity(intent);
    }
    private void openLogIn() {
        Intent intent = new Intent(this, Processing.class);
        intent.putExtra("email", email_text);
        startActivity(intent);
    }
}