package com.example.administrador.testsenddatafirebase;

import java.io.Serializable;

public class Sport implements Serializable {
    String name;
    int caloriesxminute;

    public Sport(String name, int caloriesxminute) {
        this.name = name;
        this.caloriesxminute = caloriesxminute;
    }

    public Sport(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCaloriesxminute() {
        return caloriesxminute;
    }

    public void setCaloriesxminute(int caloriesxminute) {
        this.caloriesxminute = caloriesxminute;
    }

    @Override
    public String toString() {
        return "Sport{" +
                "name='" + name + '\'' +
                ", caloriesxminute=" + caloriesxminute +
                '}';
    }
}
