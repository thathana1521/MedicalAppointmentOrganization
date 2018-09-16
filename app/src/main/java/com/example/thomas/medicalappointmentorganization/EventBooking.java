package com.example.thomas.medicalappointmentorganization;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import static android.app.PendingIntent.getActivity;

public class EventBooking extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

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
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH, i1);
        c.set(Calendar.DAY_OF_MONTH, i2);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {

    }

}
