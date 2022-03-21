package com.example.quera.Models;

import java.util.ArrayList;

public class Master extends Account {
    public static ArrayList<Master> masters;

    static {
        masters = new ArrayList<>();
    }

    private String university;

    public Master(String username, String password, String name, String university) {
        super(username, password, name);
        this.university = university;
        masters.add(this);
    }

    //Getters
    public static Master getMasterByUsername(String username) {
        for (Master master : masters)
            if (master.getUsername().equals(username))
                return master;
        return null;
    }

    public String getUniversity() {
        return this.university;
    }

    //Setters
    public void setUniversity(String university) {
        this.university = university;
    }
}
