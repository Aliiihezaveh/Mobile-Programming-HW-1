package com.example.quera.Models;

import java.util.ArrayList;

public class Classroom {
    private static ArrayList<Classroom> classrooms;

    public static ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }


    private String className;
    private String professorName;
    private String classID;

    public Classroom(String className, String professorName, String classID) {
        this.className = className;
        this.professorName = professorName;
        this.classID = classID;
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

    public String getClassID() {
        return classID;
    }

    public void setClassID(String classID) {
        this.classID = classID;
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
