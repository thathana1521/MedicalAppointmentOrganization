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
import java.util.SimpleTimeZone;

import static android.app.PendingIntent.getActivity;

public class EventBooking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    EditText name;
    Time start_time=null;
    Time end_time;
    Date date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_booking);


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
        String type = "Event";
        name = (EditText)findViewById(R.id.editText_Name);
        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, name.getText().toString(), date.toString(), start_time.toString(), end_time.toString());
    }


}
