package com.example.quera;

public class StudentClass {
    private String className;
    private String professorName;
    private String classID;

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

    public StudentClass(String className, String professorName, String classID) {
        this.className = className;
        this.professorName = professorName;
        this.classID = classID;

    }

    @Override
    public String toString() {
        return "StudentClass{" +
                "className='" + className + '\'' +
                ", professorName='" + professorName + '\'' +
                ", classID='" + classID + '\'' +
                '}';
    }
}
