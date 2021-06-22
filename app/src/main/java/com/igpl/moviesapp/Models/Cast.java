package com.igpl.moviesapp.Models;

public class Cast {

    int id;

    String gender;

    String name;

    String character;

    String profile_path;

    public Cast() {
    }

    public Cast(int id, String gender, String name, String profile_path, String original_name) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.profile_path = profile_path;
        this.character = original_name;
    }

    public String getOriginal_name() {
        return character;
    }

    public int getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getProfile_path() {
        return profile_path;
    }
}
