package com.example.quera.Models;

import java.util.ArrayList;

public class Classroom {
    private static ArrayList<Classroom> classrooms;

    public static ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }


    private int classID;
    private static int counter = 0;
    private String className;
    private String professorName;
    private Master master;
    private ArrayList<Student> students;
    private ArrayList<Exercise> exercises;

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }


    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void addExercises(Exercise exercise) {
        this.exercises.add(exercise);
    }

    public Classroom() {
        this.classrooms = null;
        this.className = null;
        this.professorName = null;

    }

    public Classroom(String className, String professorName) {
        exercises = new ArrayList<>();
        this.className = className;
        this.professorName = professorName;
        this.classID = counter;
        counter += 1;
        classrooms.add(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }


    static {
        classrooms = new ArrayList<>();
    }

//    public Classroom(String name, Master master){
//        this.name = name;
//        this.master = master;
//        students = new ArrayList<>();
//        exercises = new ArrayList<>();
//        classrooms.add(this);
//    }

    //Getters
    public static Classroom getClassroomByName(String name) {
        for (Classroom classroom : classrooms)
            if (classroom.getClassName().equals(name))
                return classroom;
        return null;
    }

    public static Classroom getClassroomByID(int id) {
        for (Classroom classroom : classrooms)
            if (classroom.getClassID() == id)
                return classroom;
        return null;
    }

    public Master getMaster() {
        return this.master;
    }

    public ArrayList<Student> getStudents() {
        return this.students;
    }

    //Setters

    public void setMaster(Master master) {
        this.master = master;
    }

    public void addStudentToClassroom(Student student) {
        this.students.add(student);
    }
}
