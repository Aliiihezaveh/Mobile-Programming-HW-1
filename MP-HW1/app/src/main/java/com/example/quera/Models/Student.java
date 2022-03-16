package com.example.quera.Models;

import java.util.ArrayList;

public class Student extends Account{
    public static ArrayList<Student> students;

    static {
        students = new ArrayList<>();
    }
    private String studentID;

    public Student(String username, String password, String name, String studentID) {
        super(username, password, name);
        this.studentID = studentID;
        students.add(this);
        //TODO save json
    }

    //Getters
    public static Student getStudentByUsername(String username) {
        for (Student student : students)
            if (student.getUsername().equals(username))
                return student;
        return null;
    }

    public String getStudentID(){
        return this.studentID;
    }

    //Setters
    public void setStudentID(String studentID){
        this.studentID = studentID;
    }
}
