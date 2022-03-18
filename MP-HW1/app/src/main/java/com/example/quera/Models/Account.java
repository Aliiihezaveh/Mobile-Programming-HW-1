package com.example.quera.Models;

import java.util.ArrayList;

public class Account {
    public static ArrayList<Account> accounts;
    public static Account loggedInAccount;
    private String username;
    private String password;
    private String name;
    private ArrayList<Classroom> classrooms;

    public void addClassrooms(Classroom classroom) {
        classrooms.add(classroom);
    }

    static {
        accounts = new ArrayList<>();
    }


    public Account(){
        this.username = null;
        this.password = null;
        this.name = null;
        this.classrooms = new ArrayList<>();
    }

    public Account(String username, String password, String name){
        this.username = username;
        this.password = password;
        this.name = name;
        this.classrooms = new ArrayList<>();
        accounts.add(this);
    }

    public static boolean canRegister(String username, String name, String password){
        if(username == null || name == null || password == null) return false;
        return getAccountByUsername(username) == null;
    }

    public static boolean canLogin(String username, String password) {
        if(Account.getAccountByUsername(username) == null) return false;
        return Account.getAccountByUsername(username).getPassword().equals(password);
    }

    public static boolean isStudent(String username){
        return Student.getStudentByUsername(username) != null;
    }

    public static boolean isMaster(String username){
        return Master.getMasterByUsername(username) != null;
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

    public String getName() {
        return this.name;
    }

    public ArrayList<Classroom> getClassrooms(){
        return this.classrooms;
    }

    //Setters
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

}
