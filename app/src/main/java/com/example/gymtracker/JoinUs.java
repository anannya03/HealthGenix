package com.example.gymtracker;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import com.example.gymtracker.db_classes.User;
import com.example.gymtracker.db_classes.DBHelper;
public class JoinUs extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText password;
    EditText email;
    DatePickerDialog picker;
    EditText editText;
    Button joinUs;
    TextView logIn;
    String fname, lname, pwd, email_id;
    User user;
    int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_us);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextTextLastName);
        password = findViewById(R.id.editTextTextPassword);
        email = findViewById(R.id.editTextTextEmailAddress);
        logIn = findViewById(R.id.logInTextView);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogIn();
            }
        });
        editText = (EditText) findViewById(R.id.editTextTextBirthDate);
        editText.setInputType(InputType.TYPE_NULL);
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(JoinUs.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                editText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.getDatePicker().setMaxDate(System.currentTimeMillis() - 1000);
                picker.show();
            }
        });
        joinUs = (Button) findViewById(R.id.joinUs);
        joinUs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(checkDataEntered()==true) {
                    fname = firstName.getText().toString();
                    lname = lastName.getText().toString();
                    email_id = email.getText().toString();
                    pwd = password.getText().toString();
                    int dob_year = picker.getDatePicker().getYear();
                    int dob_date = picker.getDatePicker().getDayOfMonth();
                    int dob_month = picker.getDatePicker().getMonth();
                    int curr_date = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
                    int curr_year = Calendar.getInstance().get(Calendar.YEAR);
                    int curr_month = Calendar.getInstance().get(Calendar.MONTH);
                    age = curr_year - dob_year;
                    if (curr_month == dob_month) {
                        if (curr_date < dob_date) {
                            age = age - 1;
                        }
                    }
                    if (curr_month < dob_month) {
                        age = age - 1;
                    }
                    if (age < 18) {
                        Toast.makeText(getApplicationContext(), "Children below 18 years of age cannot use this app", Toast.LENGTH_LONG).show();
                        editText.setText("");
                    } else {
                        DBHelper db;
                        db = new DBHelper(getApplicationContext());
                        try {
                            db.createDatabase();
                            db.openDataBase();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (db.userExist(email_id)) {
                            Toast.makeText(getApplicationContext(), "Email id already exists", Toast.LENGTH_LONG).show();
                            email.setText("");

                        } else {
                            user = new User();
                            user.setFname(fname);
                            user.setLname(lname);
                            user.setEmail(email_id);
                            user.setPwd(pwd);
                            user.setAge(age);
                            db.createUser(user);
                            openJoinUs();
                        }
                    }
                }
            }});

    }
    private void openLogIn() {
        Intent intent = new Intent(this, LogIn.class);
        startActivity(intent);
    }
    private void openJoinUs() {
        Intent intent = new Intent(this, GetStartedJoin.class);
        intent.putExtra("email",email_id);
        startActivity(intent);
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    public boolean checkDataEntered() {
        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
            return(false);
        }
        else if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
            return(false);
        }
        else if (isEmail(email) == false) {
            email.setError("Enter valid email!");
            return(false);
        }

        else if(isEmpty(password)) {
            password.setError("Enter valid password");
            return(false);
        }
        else{
            if(password.getText().toString().length() < 9) {
                password.setError("Minimum 8 characters");
                return(false);
            }
        }
        return(true);
    }
}