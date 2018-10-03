package com.example.thomas.medicalappointmentorganization;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {

    private EditText name, surname, age, username, password;
    private CheckBox identity_doctor, identity_patient;
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
        registerUser();

    }


    private void registerUser() {

        final String str_name = name.getText().toString().trim();
        final String str_surname = surname.getText().toString().trim();
        final String str_age = age.getText().toString();
        final String str_username = username.getText().toString();
        final String str_password = password.getText().toString();
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
        final String str_identity = String.valueOf(identidyId);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(), jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("name",str_name);
                params.put("surname",str_surname);
                params.put("age",str_age);
                params.put("username",str_username);
                params.put("password",str_password);
                params.put("identity",str_identity);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

}
