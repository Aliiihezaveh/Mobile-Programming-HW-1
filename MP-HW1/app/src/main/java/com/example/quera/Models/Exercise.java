package com.example.quera.Models;

import java.util.ArrayList;

public class Exercise {
    private static int counter = 0;
    private static ArrayList<Exercise> exercises = new ArrayList<>();
    private ArrayList<Answer> answers;
    private String name;
    private int id;
    private Student student;
    private Master master;
    private String question, answer;
    private Classroom classroom;

    public Exercise(String name) {
        answers = new ArrayList<>();
        this.name = name;
        this.id = counter;
        counter +=1;
        exercises.add(this);
    }

    public static Exercise getExercisesById(int exerciseId) {
        for(Exercise exercise : exercises){
            if(exercise.getId() == exerciseId)
                return exercise;
        }
        return null;
    }

    public static Exercise getExercisesByName(String exerciseName) {
        for(Exercise exercise : exercises){
            if(exercise.getName().equals(exerciseName))
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
    public Answer getAnswerByStudent(Student student){
        for (Answer answer:answers){
            if(answer.getStudent().equals(student)) return answer;
        }
        return null;
    }
    //TODO
}
