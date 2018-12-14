package com.example.administrador.testsenddatafirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SignInActivity extends AppCompatActivity {
    // Variables para capturar datos
    private EditText email;
    private EditText name;
    private EditText phone;
    private EditText age;
    private EditText condition;
    private Button createAccount;
    private EditText sportName;
    private EditText calories;
    private Button addSport;
    private TextView tituloDeporte;

    List<User> listaUsuarios = new ArrayList<User>();
    List<Sport> listaDeportes = new ArrayList<Sport>();

    private DatabaseReference mDatabase;
    public static final String EXTRA_POST_KEY = "post_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Se relacionan las variables con los componentes del layout
        email=(EditText)findViewById(R.id.registerEmail);
        name=(EditText)findViewById(R.id.registerName);
        phone=(EditText)findViewById(R.id.registerPhone);
        condition=(EditText)findViewById(R.id.registerCondition);
        age=(EditText)findViewById(R.id.registerAge);
        createAccount=(Button)findViewById(R.id.createAccount);
        sportName=(EditText)findViewById(R.id.nameSport);
        calories=(EditText)findViewById(R.id.calories);
        addSport=(Button)findViewById(R.id.addSport);
        tituloDeporte=(TextView)findViewById(R.id.titleSport);
        // Metodo para registrar al nuevo usuario
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listaDeportes.isEmpty()){
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(SignInActivity.this);
                    alertDialog.setTitle("Alert");
                    alertDialog.setMessage("You need to register sports");
                    alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) { }});
                    AlertDialog alert=alertDialog.create();
                    alert.show();
                }else{
                    String correo=email.getText().toString();
                    String nombre=name.getText().toString();
                    String telefono=phone.getText().toString();
                    String edad=age.getText().toString();
                    String condicion=condition.getText().toString();
                    if(correo.isEmpty()||nombre.isEmpty()||telefono.isEmpty()||edad.isEmpty()||condicion.isEmpty()){
                        AlertDialog.Builder alertDialog=new AlertDialog.Builder(SignInActivity.this);
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("One of fields are blank");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { }});
                        AlertDialog alert=alertDialog.create();
                        alert.show();
                    }else {
                        if (!estaEnLista(correo)) {
                            User user = new User();
                            user.name = nombre;
                            user.email = correo;
                            user.phone = telefono;
                            user.age = edad;
                            user.condition = condicion;
                            user.sports = listaDeportes.toArray(new Sport[listaDeportes.size()]);
                            agregaHijoBD(user);
                            Intent variables = new Intent();
                            variables.putExtra("usuario", (Serializable) user);
                            setResult(RESULT_OK, variables);
                            finish();
                        } else {
                            AlertDialog.Builder alertDialog = new AlertDialog.Builder(SignInActivity.this);
                            alertDialog.setTitle("Alert");
                            alertDialog.setMessage("This email already are registered");
                            alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            });
                            AlertDialog alert = alertDialog.create();
                            alert.show();
                        }
                    }
                }
            }
        });
        // Metodo para añadir deportes
        addSport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean repetido=false;
                String nombreDeporte=sportName.getText().toString();
                if(!listaDeportes.isEmpty()){
                    for(int o=0;o<listaDeportes.size();o++){
                        if(listaDeportes.get(o).name.equalsIgnoreCase(nombreDeporte)){
                            repetido=true;
                        }
                    }
                }
                if(!repetido) {
                    String calorias = calories.getText().toString();
                    Sport deporte = new Sport(nombreDeporte, Integer.parseInt(calorias));
                    listaDeportes.add(deporte);
                    sportName.setText("");
                    calories.setText("");
                    tituloDeporte.setText("Sports (" + listaDeportes.size() + ")");
                }
            }
        });
        // Se inicia la coneccion a la base de datos
        mDatabase = FirebaseDatabase.getInstance().getReference();
        obtenerDatosBD();
    }

    /**
     *
     * @param user
     */
    private void agregaHijoBD(User user){
        //esto lo deben investigar
        mDatabase.child("usersMaby").setValue(user);
    }

    /**
     *
     * @param email
     * @return
     */
    public boolean estaEnLista(String email){
        boolean esta=false;
        if(!listaUsuarios.isEmpty()) {
            for (int o = 0; o < listaUsuarios.size(); o++) {
                if (listaUsuarios.get(o).email.equalsIgnoreCase(email))
                    esta = true;
            }
        }
        return esta;
    }

    private void obtenerDatosBD() {
        mDatabase.child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot users: dataSnapshot.getChildren()) {
                    try{
                        User usuario=users.getValue(User.class);
                        if(!estaEnLista(usuario.email)) {
                            listaUsuarios.add(usuario);
                            Log.i("DATOS", usuario.toString());
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
    }
}
