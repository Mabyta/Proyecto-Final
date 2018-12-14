package com.example.administrador.testsenddatafirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class SelectionInformactionActivity extends AppCompatActivity {
    private User sesion;
    private Spinner spinner;
    private String deporteSeleccionado="";

    private Button usuarios;
    private Button meetings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_informaction);

        sesion=(User) getIntent().getSerializableExtra("usuario");
        String[] deportes=new String[sesion.sports.length];
        for (int o=0;o<deportes.length;o++){
            deportes[o]=sesion.sports[o].name;
        }

        spinner=(Spinner) findViewById(R.id.spinner);
        usuarios=(Button) findViewById(R.id.verUsuarios);
        meetings=(Button) findViewById(R.id.verMeetings);

        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, deportes));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                deporteSeleccionado=adapterView.getItemAtPosition(pos).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            { }
        });
        usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deporteSeleccionado.equalsIgnoreCase("Select a sport")){
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(SelectionInformactionActivity.this);
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Select a sport.");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }});
                    AlertDialog alert=alertDialog.create();
                    alert.show();
                }else{
                    Intent verUsuarios=new Intent(getApplicationContext(), SeeUsersActivity.class);
                    verUsuarios.putExtra("deporte",deporteSeleccionado);
                    startActivity(verUsuarios);
                }
            }
        });
        meetings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(deporteSeleccionado.equalsIgnoreCase("Select a sport")){
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(SelectionInformactionActivity.this);
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("Select a sport.");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }});
                    AlertDialog alert=alertDialog.create();
                    alert.show();
                }else{
                    Intent verMeetings=new Intent(getApplicationContext(),SeeMeetingsActivity.class);
                    verMeetings.putExtra("deporte",deporteSeleccionado);
                    startActivity(verMeetings);
                }
            }
        });

    }
}
