package com.example.administrador.testsenddatafirebase;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class SeeMeetingsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton agregar;

    private ArrayList<Meeting> listaMeeting;
    private String deporte;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_mettings);

        Bundle variable = getIntent().getExtras();
        deporte = variable.getString("deporte");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerMeetings);
        agregar=(FloatingActionButton) findViewById(R.id.agregarMeeting);

        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDividerItemDecoration);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createMeeting=new Intent(getApplicationContext(),CreateMettingActivity.class);
                createMeeting.putExtra("deporte",deporte);
                startActivity(createMeeting);
            }
        });

        listaMeeting = new ArrayList<Meeting>();

        mDatabase = FirebaseDatabase.getInstance().getReference();
        obtenerDatosBD();
    }

    /**
     *
     * @param meeting
     * @return
     */
    public boolean filtrarLista(Meeting meeting){
        boolean esta=false;
        if(meeting.getDeporte().equalsIgnoreCase(deporte)) {
            for (int o = 0; o < listaMeeting.size(); o++) {
                if (meeting.getNombre().equalsIgnoreCase(listaMeeting.get(o).getNombre())){
                    esta = true;
                }
            }
        }else{
            esta = true;
        }
        return esta;
    }

    /**
     *
     */
    private void obtenerDatosBD() {
        mDatabase.child("meetings").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot meetings: dataSnapshot.getChildren()) {
                    try {
                        Meeting meeting = meetings.getValue(Meeting.class);
                        if (!filtrarLista(meeting)) {
                            listaMeeting.add(meeting);
                        }
                    }catch (NullPointerException e){
                        Log.d("NOSEPUDO","Error de NullPointerException");
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("NOSEPDO", "Error trying to get classified ads for " +" "+databaseError);
            }
        });
        if(listaMeeting.isEmpty()){
            Meeting meeting=new Meeting();
            meeting.setNombre("No hay Datos");
            meeting.setDeporte("Nothing");
            meeting.setDate("00-00-0000");
            meeting.setPeopleCounter(0);
            meeting.setPlace("Nothing");
            meeting.setTime("10:00");
            listaMeeting.add(meeting);
        }
        mAdapter=new MeetingsAdapter(listaMeeting,getApplicationContext());
        recyclerView.setAdapter(mAdapter);
    }
}
