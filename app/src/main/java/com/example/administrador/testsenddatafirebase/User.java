package com.example.administrador.testsenddatafirebase;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable {

    String name;
    String email;
    String phone;
    Sport [] sports;

    public User(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Sport[] getSports() {
        return sports;
    }

    public void setSports(Sport[] sports) {
        this.sports = sports;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User(String name, String email, String phone, Sport[] sports) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.sports = sports;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='"+phone+'\''+
                ", sports=" + Arrays.toString(sports) +
                '}';
    }
}
