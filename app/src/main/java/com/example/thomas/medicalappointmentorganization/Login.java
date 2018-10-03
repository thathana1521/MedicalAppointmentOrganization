package com.example.thomas.medicalappointmentorganization;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Login extends AppCompatActivity {
    EditText UsernameET, PasswordET;
    CheckBox identity_doctor, identity_patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UsernameET = (EditText)findViewById(R.id.editText_username);
        PasswordET = (EditText)findViewById(R.id.editText_password);
        identity_doctor = (CheckBox)findViewById(R.id.checkBox_doctor);
        identity_patient = (CheckBox)findViewById(R.id.checkBox_patient);
    }

    public void OnLogin(View view) {
        String username = UsernameET.getText().toString();
        String password = PasswordET.getText().toString();
        String type = "login";
        int identidyId;
        //This sets only one checkBox checked.
        if(identity_doctor.isChecked()) {
            identity_patient.setChecked(false);
        }

        //identityId = 0 if user is a doctor. Else 1 (patient)
        if(identity_doctor.isChecked()){
            identidyId = 0;
        }
        else {
            identidyId = 1;
        }
        System.out.println("Identity = "+identidyId);

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, username, password, String.valueOf(identidyId));

    }
    public void OpenReg(View view) {
        startActivity(new Intent(this, Register.class));
    }
}
