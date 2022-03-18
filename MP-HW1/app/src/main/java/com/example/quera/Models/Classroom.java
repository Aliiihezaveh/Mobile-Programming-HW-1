package com.example.quera.Models;

import java.util.ArrayList;

public class Classroom {
    private static ArrayList<Classroom> classrooms;

    public static ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }


    private String className;
    private String professorName;

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    private int classID;
    private static int counter = 0;

    public ArrayList<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(ArrayList<Exercise> exercises) {
        this.exercises = exercises;
    }

    public Classroom(String className, String professorName) {
        exercises = new ArrayList<>();
        this.className = className;
        this.professorName = professorName;
        this.classID = counter;
        counter += 1;
        classrooms.add(this);
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "className='" + className + '\'' +
                ", professorName='" + professorName + '\'' +
                ", classID='" + classID + '\'' +
                ", name='" + name + '\'' +
                ", master=" + master +
                ", students=" + students +
                ", exercises=" + exercises +
                '}';
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
