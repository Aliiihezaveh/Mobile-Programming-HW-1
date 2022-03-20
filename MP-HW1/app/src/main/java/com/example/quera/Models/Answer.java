package com.example.quera.Models;

import java.util.ArrayList;

public class Answer {
    private Student student;
    private int score;
    private String answer;
    private Classroom classroom;
    private static ArrayList<Answer> answers = new ArrayList<>();

    public Answer(Student student, int score, String answer) {
        this.student = student;
        this.score = score;
        this.answer = answer;
        answers.add(this);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public static Answer getAnswerByStudentName(String studentName) {
        for(Answer answer : answers){
            if(answer.getStudent().getName().equals(studentName))
                return answer;
        }
        return null;
    }

    public static void setAnswers(ArrayList<Answer> answers) {
        Answer.answers = answers;
    }

    public Student getStudent(){
        return this.student;
    }
    public String getAnswer(){
        return this.answer;
    }
    public String getScore(){
        if(this.score == 0)
            return "No Score";
        return String.valueOf(this.score);
    }
    public void editAnswer(String answer){
        this.answer = answer;
    }
}