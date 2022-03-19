package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.quera.Models.Answer;
import com.example.quera.Models.Classroom;
import com.example.quera.Models.Exercise;
import com.example.quera.Models.Student;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        RecyclerView allClassRecycleView = findViewById(R.id.allAnswerRecycleView);
        AnswerAdapter adapter = new AnswerAdapter(this);
        ArrayList<Answer> answers = new ArrayList<>();
        Intent intent = getIntent();
        String className = intent.getStringExtra("ClassName");
        String exerciseName = intent.getStringExtra("ExerciseName");
        Classroom classroom = Classroom.getClassroomByName(className);
        Exercise exercise = Exercise.getExercisesByClassName(classroom);
        Student student = new Student("A", "b", "c", "d");
        Answer answer;
        if(Answer.getAnswerByStudentName(student.getName()) == null)
            answer = new Answer(student, 0, "ali");
        else
            answer = Answer.getAnswerByStudentName(student.getName());
        if (exercise != null) {
            exercise.addAnswers(answer);
        }
        // exercise.addAnswers(new Answer(new Student("Ali","A","B","As"),5,"SAlam"));
        Toast.makeText(getApplicationContext(), classroom.getClassName(), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), exercise.getName(), Toast.LENGTH_SHORT).show();
        if (exercise != null) {
            answers.addAll(exercise.getAnswers());
        }
        adapter.setStudentAnswers(answers);
        allClassRecycleView.setAdapter(adapter);
        allClassRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}