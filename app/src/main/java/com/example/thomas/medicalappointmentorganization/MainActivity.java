package com.example.thomas.medicalappointmentorganization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText UsernameET, PasswordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        UsernameET = (EditText)findViewById(R.id.editText_username);
        PasswordET = (EditText)findViewById(R.id.editText_password);

    }



    public void OpenLogin(View view) {
        startActivity(new Intent(this, Login.class));
    }

    public void OpenReg(View view) {
        startActivity(new Intent(this, Register.class));
    }


    public void onBtnCheckClick(View view) {
        startActivity(new Intent(this, EventBooking.class));
    }
}
