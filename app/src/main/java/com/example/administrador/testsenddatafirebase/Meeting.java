package com.example.administrador.testsenddatafirebase;

public class Meeting {
    private String name="";
    private String place="";
    private int numberOfPeople=0;
    private String fecha="";
    private String sport="";
    private String time="";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString(){
        return "User{" +
                "date='" +fecha +'\''+
                ", deporte='" +sport +'\''+
                ", nombre='" + name + '\'' +
                ", people_counter='" + numberOfPeople + '\'' +
                ", place='" +place +'\''+
                ", time='" +time +'\''+
                '}';
    }
}
