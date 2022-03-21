package com.example.quera.Models;

import java.util.ArrayList;

public class Classroom {
    private static ArrayList<Classroom> classrooms = new ArrayList<>();
    private static int counter = 0;

    private int classID;
    private String className;
    private String professorName;
    private Master master;
    private ArrayList<Student> students;
    private ArrayList<Exercise> exercises;


    public Classroom() {
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

    public String serialize() {
        ArrayList<String> studentsUsernames = new ArrayList<>();
        for (Student student : students)
            studentsUsernames.add(student.getUsername());
        String studentUsernamesSerialized = (new Gson()).toJson(studentsUsernames);
        ClassroomDeepSerialized classroomDeepSerialized = new ClassroomDeepSerialized(this.classID, this.className, this.professorName, studentUsernamesSerialized);
        return (new Gson()).toJson(classroomDeepSerialized);
    } //complete

    public static Classroom deserialize(String classroomSerialized) {

    }

    public static String saveClassrooms() {
        synchronized (classrooms) {
            for (Classroom classroom : classrooms) {
                String classroomFilePath = "src/main/resources/classrooms/" + classroom.getClassID() + ".json";
                File classroomFile = new File(classroomFilePath);
                try {
                    FileWriter writer = new FileWriter(classroomFile.getPath(), false);
                    String jsonData = classroom.serialize();
                    writer.write(jsonData);
                    writer.close();
                } catch (IOException e) {
                    return "Can't parse classrooms JSON files";
                }
            }
            return "Classrooms data saved successfully";
        }
    } //complete

    public static synchronized String initializeClassrooms() {
        if (classrooms.size() == 0) {
            synchronized (classrooms) {
                File classroomsDirectory = new File("src/main/resources/classrooms");
                File[] classroomsFiles = classroomsDirectory.listFiles();
                if (classroomsFiles == null)
                    return "Classrooms JSON files missing!";
                for (File file : classroomsFiles) {
                    String classroomJson;
                    try {
                        classroomJson = Files.readString(Paths.get(file.getPath()));
                    } catch (IOException e) {
                        return "JSON files can't be accessed!";
                    }
                    accounts.add(Classroom.deserialize(classroomJson));
                }
                return "Classrooms loaded successfully";
            }
        }
        return "";
    } //complete

    public static ArrayList<Classroom> getClassrooms() {
        return classrooms;
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

class ClassroomDeepSerialized {
    protected int classID;
    protected String className;
    protected String profName;
    protected String studentsUsernamesSerialized;

    public ClassroomDeepSerialized(int classID, String className, String profName, String studentsUsernamesSerialized) {
        this.classID = classID;
        this.className = className;
        this.profName = profName;
        this.studentsUsernamesSerialized = studentsUsernamesSerialized;
    }
}
