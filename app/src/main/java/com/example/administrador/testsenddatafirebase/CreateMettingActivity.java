package com.example.administrador.testsenddatafirebase;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class CreateMettingActivity extends AppCompatActivity {
    // Variables para capturar los datos
    private EditText name;
    private EditText place;
    private EditText peopleCounter;
    private Button crearMeeting;
    // Variables para capturar el tiempo
    private EditText date;
    private EditText time;
    private int año,mes,dia,hora,minuto;
    private Calendar calendario= Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);
        date=(EditText) findViewById(R.id.dateMeeting);
        time=(EditText) findViewById(R.id.timeMeeting);
        name=(EditText) findViewById(R.id.nameMeeting);
        place=(EditText) findViewById(R.id.placeMeeting);
        peopleCounter=(EditText) findViewById(R.id.peopleMeeting);
        crearMeeting=(Button) findViewById(R.id.createMeeting);

        año=calendario.get(Calendar.YEAR);
        mes=calendario.get(Calendar.MONTH);
        dia=calendario.get(Calendar.DAY_OF_MONTH);
        hora=calendario.get(Calendar.HOUR_OF_DAY);
        minuto=calendario.get(Calendar.MINUTE);

        date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                    showDialog(1);
            }
        });
        time.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View view, boolean hasFocus){
                if(hasFocus)
                    showDialog(2);
            }
        });
        crearMeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private DatePickerDialog.OnDateSetListener dateListener=new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            año=year;
            mes=month;
            dia=dayOfMonth;
            date.setText(dia+"-"+mes+"-"+año);
        }
    };
    private TimePickerDialog.OnTimeSetListener timeListener=new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hora=hourOfDay;
            minuto=minute;
            time.setText(hora+":"+minuto);
        }
    };
    protected Dialog onCreateDialog(int id){
        switch (id){
            case 1:
                return new DatePickerDialog(this, dateListener, año, mes, dia);
            case 2:
                return new TimePickerDialog(this, timeListener, hora, minuto,true);
        }
        return null;
    }
}
