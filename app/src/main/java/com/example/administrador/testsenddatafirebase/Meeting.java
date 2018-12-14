package com.example.administrador.testsenddatafirebase;

import java.io.Serializable;

public class Meeting implements Serializable {
    private String nombre="";
    private String place="";
    private int peopleCounter=0;
    private String date="";
    private String deporte="";
    private String time="";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getPeopleCounter() {
        return peopleCounter;
    }

    public void setPeopleCounter(int peopleCounter) {
        this.peopleCounter = peopleCounter;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Meeting(String nombre, String place, int peopleCounter, String date, String deporte, String time) {
        this.nombre = nombre;
        this.place = place;
        this.peopleCounter = peopleCounter;
        this.date = date;
        this.deporte = deporte;
        this.time = time;
    }

    public Meeting() { }

    @Override
    public String toString(){
        return "User{" +
                "date='" +date +'\''+
                ", deporte='" +deporte +'\''+
                ", nombre='" + nombre + '\'' +
                ", people_counter='" + peopleCounter + '\'' +
                ", place='" +place +'\''+
                ", time='" +time +'\''+
                '}';
    }
}
