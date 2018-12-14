package com.example.administrador.testsenddatafirebase;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // Variables para capturar datos
    private Button signin;
    private Button login;
    private EditText email;
    // Variables para leer los datos
    private String mPostKey;
    private int pocision=0;
    private String emailUsuario;
    private DatabaseReference mDatabase;
    //
    List<User> listaUsuarios = new ArrayList<User>();
    List<Sport> listaDeportes = new ArrayList<Sport>();

    // Constantes
    public static final String EXTRA_POST_KEY = "post_key";
    public static final int SIGN_IN_ACTIVITY=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Se relacionan las variables con los componentes del layout
        signin=(Button)findViewById(R.id.signin);
        login=(Button)findViewById(R.id.login);
        email=(EditText)findViewById(R.id.email);
        // Se crea el metodo para crear un usuario
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn=new Intent(getApplicationContext(),SignInActivity.class);
                startActivityForResult(signIn,0);
            }
        });
        // Se crea el metodo para ingresar a la aplicacion
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailUsuario=email.getText().toString();
                if(!emailUsuario.isEmpty())
                    if(estaEnLista(emailUsuario)){
                        Intent select=new Intent(getApplicationContext(),SelectionInformactionActivity.class);
                        select.putExtra("usuario",(Serializable) listaUsuarios.get(pocision));
                        startActivity(select);
                    }else{
                        AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                        alertDialog.setTitle("Alert");
                        alertDialog.setMessage("Email doesn't exist");
                        alertDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { }});
                        AlertDialog alert=alertDialog.create();
                        alert.show();
                    }
            }
        });
        // Se inicia la coneccion a la base de datos
        mDatabase = FirebaseDatabase.getInstance().getReference();
        obtenerDatosBD();
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
                if (listaUsuarios.get(o).email.equalsIgnoreCase(email)){
                    esta = true;
                    pocision=o;
                }
            }
        }
        return esta;
    }

    /**
     *
     */
    private void obtenerDatosBD() {
        mDatabase.child("users").orderByChild("name").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot users: dataSnapshot.getChildren()) {
                    try {
                        User usuario = users.getValue(User.class);
                        if (!estaEnLista(usuario.email)) {
                            listaDeportes.clear();
                            for (DataSnapshot spots : users.child("sport").getChildren()) {
                                Sport deporte = spots.getValue(Sport.class);
                                listaDeportes.add(deporte);
                            }
                            usuario.sports = listaDeportes.toArray(new Sport[listaDeportes.size()]);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            if (requestCode == SIGN_IN_ACTIVITY) {
                Intent select=new Intent(getApplicationContext(),SelectionInformactionActivity.class);
                select.putExtra("usuario",data.getSerializableExtra("usuario"));
                startActivity(select);
            }
        }
    }
}
