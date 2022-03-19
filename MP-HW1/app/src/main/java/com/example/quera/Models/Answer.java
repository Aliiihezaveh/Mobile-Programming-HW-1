package com.example.quera.Models;

public class Answer {
    private Student student;
    private int score;
    private String answer;
    private Classroom classroom;

    public Answer(Student student, int score, String answer) {
        this.student = student;
        this.score = score;
        this.answer = answer;
    }

    public Student getStudent(){
        return this.student;
    }
    public String getAnswer(){
        return this.answer;
    }
    public int getScore(){
        return this.score;
    }
}