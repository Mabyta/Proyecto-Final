package com.example.administrador.testsenddatafirebase;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.MyViewHolder>{
    private ArrayList<Meeting> mDataset;

    @NonNull
    @Override
    public MeetingsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout=LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon, parent, false);
        MeetingsAdapter.MyViewHolder vh = new MeetingsAdapter.MyViewHolder(layout);
        return vh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingsAdapter.MyViewHolder holder, int position){
        // Se asignan los datos a los items
        holder.nombre.setText(mDataset.get(position).getNombre());
        holder.counter_people.setText("People counter= "+mDataset.get(position).getPeopleCounter());
        holder.lugar.setText("Place: "+mDataset.get(position).getPlace());
        holder.fecha.setText("Date: "+mDataset.get(position).getDate());
        holder.hora.setText("Time: "+mDataset.get(position).getTime());
        holder.deporte.setText("Sport: "+mDataset.get(position).getDeporte());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public MeetingsAdapter(ArrayList<Meeting> mDataset) {
        this.mDataset=mDataset;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView counter_people;
        public TextView lugar;
        public TextView fecha;
        public TextView hora;
        public TextView deporte;

        public MyViewHolder(TextView nombre,TextView counter_people, TextView lugar, TextView fecha, TextView hora, TextView deporte) {
            super(nombre);
            this.nombre = nombre;
            this.counter_people=counter_people;
            this.lugar=lugar;
            this.fecha=fecha;
            this.hora=hora;
            this.deporte=deporte;
        }

        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.nombreRenglon);
            counter_people=v.findViewById(R.id.numeroRenglon);
            fecha=v.findViewById(R.id.fechaRenglon);
            lugar=v.findViewById(R.id.lugarRenglon);
            hora=v.findViewById(R.id.horaRenglon);
            deporte=v.findViewById(R.id.deporteRenglon);
        }
    }
}
