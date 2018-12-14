package com.example.administrador.testsenddatafirebase;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder>{
    private ArrayList<User> mDataset;
    private Context context;

    @NonNull
    @Override
    public UserAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout=LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon, parent, false);
        UserAdapter.MyViewHolder vh = new UserAdapter.MyViewHolder(layout);
        return vh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.MyViewHolder holder, int position){
        // Se asignan los datos a los items
        holder.correo.setText(mDataset.get(position).name);
        holder.telefono.setText(mDataset.get(position).phone);
        holder.edad.setText(mDataset.get(position).age);
        holder.nombre.setText(mDataset.get(position).name);
        final int pocision=position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meeting=new Intent(context, MeetingActivity.class);
                meeting.putExtra("user",(Serializable) mDataset.get(pocision));
                context.startActivity(meeting);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public UserAdapter(ArrayList<User> mDataset, Context context) {
        this.mDataset=mDataset;
        this.context=context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView edad;
        public TextView telefono;
        public TextView correo;

        public MyViewHolder(TextView nombre,TextView edad, TextView telefono, TextView correo) {
            super(nombre);
            this.nombre = nombre;
            this.edad=edad;
            this.telefono=telefono;
            this.correo=correo;
        }

        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.nombreUser);
            edad=v.findViewById(R.id.edadUser);
            telefono=v.findViewById(R.id.telefonoUser);
            correo=v.findViewById(R.id.correoUser);
        }
    }
}
