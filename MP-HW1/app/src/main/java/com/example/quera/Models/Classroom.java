package com.example.quera.Models;

import java.util.ArrayList;

public class Classroom {
    public static ArrayList<Classroom> classrooms;

    static {
        classrooms = new ArrayList<>();
    }

    private String name;
    private Master master;
    private ArrayList<Student> students;
    private ArrayList<Exercise> exercises;

    public Classroom(String name, Master master){
        this.name = name;
        this.master = master;
        students = new ArrayList<>();
        exercises = new ArrayList<>();
        classrooms.add(this);
    }

    //Getters
    public Classroom getClassroomByName(String name){
        for (Classroom classroom : classrooms)
            if (classroom.getName().equals(name))
                return classroom;
        return null;
    }

    public String getName(){
        return this.name;
    }

    public Master getMaster(){
        return this.master;
    }

    public ArrayList<Student> getStudents(){
        return this.students;
    }

    //Setters
    public void setName(String name){
        this.name = name;
    }

    public void setMaster(Master master){
        this.master = master;
    }

    public void addStudentToClassroom(Student student){
        this.students.add(student);
    }
}
