package com.example.quera.Models;

import java.util.ArrayList;

public class Account {
    public static ArrayList<Account> accounts;

    static {
        accounts = new ArrayList<>();
    }

    private String username;
    private String password;
    private String firstname;
    private String lastname;
    private ArrayList<Classroom> classrooms;

    public Account(){
        this.username = null;
        this.password = null;
        this.firstname = null;
        this.lastname = null;
        this.classrooms = new ArrayList<>();
    }

    public Account(String username, String password, String firstname, String lastname){
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.classrooms = new ArrayList<>();
        accounts.add(this);
    }

    //Getters
    public static Account getAccountByUsername(String username) {
        for (Account account : accounts)
            if (account.getUsername().equals(username))
                return account;
        return null;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public ArrayList<Classroom> getClassrooms(){
        return this.classrooms;
    }

    public String getFullName(){
        return this.firstname + " " + this.lastname;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
