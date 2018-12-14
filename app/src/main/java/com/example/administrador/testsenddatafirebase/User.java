package com.example.administrador.testsenddatafirebase;

import java.io.Serializable;
import java.util.Arrays;

public class User implements Serializable {

    String name;
    String email;
    String phone;
    String age;
    String condition;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public User(String name, String email, String phone, String age, String condition, Sport[] sports) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.condition = condition;
        this.sports = sports;
    }

    @Override
    public String toString() {
        return "User{" +
                "age='"+age+'\''+
                ", condition='"+condition+'\''+
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='"+phone+'\''+
                ", sports=" + Arrays.toString(sports) +
                '}';
    }
}
