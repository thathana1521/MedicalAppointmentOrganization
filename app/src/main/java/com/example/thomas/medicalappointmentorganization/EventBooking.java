package com.example.thomas.medicalappointmentorganization;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;

import static android.app.PendingIntent.getActivity;

public class EventBooking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    EditText name;
    Time start_time=null;
    Time end_time;
    Date date;
    int approved = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_booking);

        name = (EditText)findViewById(R.id.editText_Name);
        final Button selectDatebtn = (Button)findViewById(R.id.button_SelectDate);
        final Button selectStartbtn = (Button)findViewById(R.id.button_SelectStartTime);
        final Button selectEndbtn = (Button)findViewById(R.id.button_SelectEndTime);
    }

    public void onBtnPickDateClicked (View view){

        DialogFragment datePicker = new com.example.thomas.medicalappointmentorganization.DatePicker();
        datePicker.show(getSupportFragmentManager(), "date picker");
    }

    public void  onBtnSelectTimeClicked (View view) {
        DialogFragment timePicker = new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(), "time picker");
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c = Calendar.getInstance();
        date=c.getTime();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar c = Calendar.getInstance();
        if(start_time==null){
            start_time = new Time(c.getTime().getTime());
        }
        else {
            end_time = new Time(c.getTime().getTime());
        }
    }


    public void onAddEvent(View view){

        final String str_name = name.getText().toString().trim();
        final String str_date = date.toString().trim();
        final String str_startTime = start_time.toString().trim();
        final String str_endTime = end_time.toString().trim();
        final String str_approved = Integer.toString(approved);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.EVENTS_URL,
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
                params.put("date",str_date);
                params.put("start_time",str_startTime);
                params.put("end_time",str_endTime);
                params.put("approoved",str_approved);
                return params;
            }
        };
        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
    }
}
