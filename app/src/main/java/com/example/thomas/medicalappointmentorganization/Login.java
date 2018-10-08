package com.example.thomas.medicalappointmentorganization;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText UsernameET, PasswordET;
    CheckBox identity_doctor, identity_patient;
    private ProgressDialog progressDialog;
    private int identityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /*if ( SharedPreManager.getInstance(this).isLoggedIn()) {
            finish();
            startActivity(new Intent(this, ProfileActivity.class));
            return;
        }*/

        UsernameET = (EditText)findViewById(R.id.editText_username);
        PasswordET = (EditText)findViewById(R.id.editText_password);
        identity_doctor = (CheckBox)findViewById(R.id.checkBox_doctor);
        identity_patient = (CheckBox)findViewById(R.id.checkBox_patient);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
    }

    public void OnLogin(View view) {
        final String username = UsernameET.getText().toString().trim();
        final String password = PasswordET.getText().toString().trim();

        //progressDialog.show();
        StringRequest stringRequest = new StringRequest(
                Request.Method.POST,
                Constants.login_url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {

                            JSONObject object = new JSONObject(response);
                            Toast.makeText(
                                    getApplicationContext(),
                                    object.toString(),
                                    Toast.LENGTH_LONG
                            ).show();


                            if(!object.getBoolean("error")){
                                //that means the user successfully authenticated

                                /*if(SharedPreManager.getInstance(Login.this).userLogin(object.getInt("id"),
                                        object.getString("username"),
                                        object.getString("surname"),
                                        object.getString("identity"))){
                                    Toast.makeText(
                                            getApplicationContext(),
                                            "TRUE",
                                            Toast.LENGTH_LONG
                                    ).show();
                                }*/
                                SharedPreManager.getInstance(getApplicationContext())
                                        .userLogin(
                                                object.getInt("id"),
                                                object.getString("username"),
                                                object.getString("age"),
                                                object.getString("surname"),
                                                object.getString("identity")
                                        );
                                Toast.makeText(
                                        getApplicationContext(),
                                        "User successfully login",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                            else {
                                Toast.makeText(
                                        getApplicationContext(),
                                        object.getString("message"),
                                        Toast.LENGTH_LONG
                                ).show();
                            }

                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            finish();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();

                        Toast.makeText(
                                getApplicationContext(),
                                error.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params = new HashMap<>();
                params.put("username", username);

                params.put("password", password);
                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }

    public void OpenReg(View view) {
        startActivity(new Intent(this, Register.class));
    }

    public void doctorCheckBoxClick(View view) {
        identity_patient.setChecked(false);
        identityId=0;
    }

    public void patientCheckBoxClick(View view) {
        identity_doctor.setChecked(false);
        identityId=1;
    }
}
