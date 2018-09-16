package com.example.thomas.medicalappointmentorganization;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    EditText name, surname, age, username, password;
    CheckBox identity_doctor, identity_patient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = (EditText)findViewById(R.id.editText_name);
        surname = (EditText)findViewById(R.id.editText_surname);
        age = (EditText)findViewById(R.id.editText_age);
        username = (EditText)findViewById(R.id.editText_username);
        password = (EditText)findViewById(R.id.editText_password);
        identity_doctor = (CheckBox)findViewById(R.id.checkBox_doctor);
        identity_patient = (CheckBox)findViewById(R.id.checkBox_patient);
    }

    public void OnRegister(View view) {
        String type = "register";
        String str_name = name.getText().toString();
        String str_surname = surname.getText().toString();
        String str_age = age.getText().toString();
        String str_username = username.getText().toString();
        String str_password = password.getText().toString();
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



        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, str_name, str_surname, str_age, str_username, str_password, String.valueOf(identidyId) );
    }


}
