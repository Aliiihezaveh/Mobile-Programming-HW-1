package com.example.quera.Models;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Exercise {
    private static int counter = 0;
    private static ArrayList<Exercise> exercises = new ArrayList<>();

    private String name;
    public int id;
    private Student student;
    private Master master;
    private String question, answer;
    private Classroom classroom;
    public ArrayList<Answer> answers;

    public Exercise() {
        this.answers = new ArrayList<>();
        exercises.add(this);
    }

    public Exercise(Classroom classroom, String name) {
        this.classroom = classroom;
        answers = new ArrayList<>();
        this.name = name;
        this.id = counter;
        counter += 1;
        exercises.add(this);
    }

    public String serialize() {
        ExerciseDeepSerialized exerciseDeepSerialized = new ExerciseDeepSerialized(this.name, this.id, this.classroom.classID);
        return (new Gson()).toJson(exerciseDeepSerialized);
    }

    public static Exercise deserialize(String exerciseSerialized) {
        ExerciseDeepSerialized exerciseDeepSerialized = (new Gson()).fromJson(exerciseSerialized, ExerciseDeepSerialized.class);

        Exercise output = new Exercise();
        output.setId(exerciseDeepSerialized.id);
        output.setName(exerciseDeepSerialized.name);
        output.setClassroom(Classroom.getClassroomByID(exerciseDeepSerialized.classID));
        Classroom.getClassroomByID(exerciseDeepSerialized.classID).exercises.add(output);
        return output;
    }

    public static String saveExercises() {
        synchronized (exercises) {
            for (Exercise exercise : exercises) {
                String exerciseFilePath = "src/main/resources/exercises/" + exercise.getId() + ".json";
                File exerciseFile = new File(exerciseFilePath);
                try {
                    FileWriter writer = new FileWriter(exerciseFile.getPath(), false);
                    String jsonData = exercise.serialize();
                    writer.write(jsonData);
                    writer.close();
                } catch (IOException e) {
                    return "Can't parse classrooms JSON files";
                }
            }
            return "Exercise data saved successfully";
        }
    } //complete

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static synchronized String initializeExercises() {
        if (exercises.size() == 0) {
            synchronized (exercises) {
                File exercisesDirectory = new File("src/main/resources/exercises");
                File[] exercisesFiles = exercisesDirectory.listFiles();
                if (exercisesFiles == null)
                    return "Exercises JSON files missing!";
                for (File file : exercisesFiles) {
                    String exerciseJson;
                    try {
                        File file1 = new File(String.valueOf(Paths.get(file.getPath())));
                        exerciseJson = String.valueOf(new BufferedReader(new FileReader(file1)));
                        //exerciseJson = Files.readString(Paths.get(file.getPath()));
                    } catch (IOException e) {
                        return "JSON files can't be accessed!";
                    }
                    exercises.add(Exercise.deserialize(exerciseJson));
                }
                return "Exercises loaded successfully";
            }
        }
        return "";
    } //complete

    public static Exercise getExercisesById(int exerciseId) {
        for (Exercise exercise : exercises) {
            if (exercise.getId() == exerciseId)
                return exercise;
        }
        return null;
    }

    public static Exercise getExercisesByName(String exerciseName) {
        for (Exercise exercise : exercises) {
            if (exercise.getName().equals(exerciseName))
                return exercise;
        }
        return null;
    }

    public static Exercise getExercisesByClassName(Classroom classroom) {
        for (Exercise exercise : exercises) {
            if (exercise.getClassroom().getClassName().equals(classroom.getClassName()))
                return exercise;
        }
        return null;
    }

    public static void addExercises(Exercise exercise) {
        Exercise.exercises.add(exercise);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void addAnswers(Answer answer) {
        this.answers.add(answer);
    }

    public Answer getAnswerByStudent(String student) {
        for (Answer answer : answers) {
            if (answer.getStudent().getName().equals(student)) return answer;
        }
        return null;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
    //TODO
}

class ExerciseDeepSerialized {
    protected String name;
    protected int id;
    protected int classID;

    public ExerciseDeepSerialized(String name, int id, int classID) {
        this.name = name;
        this.id = id;
        this.classID = classID;
    }
}








