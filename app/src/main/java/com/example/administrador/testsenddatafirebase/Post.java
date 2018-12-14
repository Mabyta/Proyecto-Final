package com.example.administrador.testsenddatafirebase;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Post {

    public String uid;
    public String name;
    public String [] sports;
    public String email;
    public int starCount = 0;
    public Map<String, Boolean> stars = new HashMap<>();

    public Post() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Post(String uid, String author, String []sports, String email) {
        this.uid = uid;
        this.name = author;
        this.sports = sports;
        this.email = email;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("name", name);
        result.put("sports", sports);
        result.put("email", email);
     /*   result.put("starCount", starCount);
        result.put("stars", stars);*/

        return result;
    }

    @Override
    public String toString() {
        return "Post{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", sports=" + Arrays.toString(sports) +
                ", email='" + email + '\'' +
                '}';
    }
}