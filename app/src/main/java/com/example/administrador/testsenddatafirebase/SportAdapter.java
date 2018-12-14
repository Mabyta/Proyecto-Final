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

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.MyViewHolder>{
    private Sport[] mDataset;

    @NonNull
    @Override
    public SportAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout=LayoutInflater.from(parent.getContext()).inflate(R.layout.renglon, parent, false);
        SportAdapter.MyViewHolder vh = new SportAdapter.MyViewHolder(layout);
        return vh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull SportAdapter.MyViewHolder holder, int position){
        // Se asignan los datos a los items
        holder.nombre.setText(mDataset[position].getName());
        holder.calorias.setText("calories "+mDataset[position].getCaloriesxminute());
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    public SportAdapter(Sport[] mDataset) {
        this.mDataset=mDataset;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView nombre;
        public TextView calorias;

        public MyViewHolder(TextView nombre,TextView calorias) {
            super(nombre);
            this.nombre = nombre;
            this.calorias=calorias;
        }

        public MyViewHolder(View v) {
            super(v);
            nombre = v.findViewById(R.id.nombreSport);
            calorias=v.findViewById(R.id.caloriesSport);
        }
    }
}
