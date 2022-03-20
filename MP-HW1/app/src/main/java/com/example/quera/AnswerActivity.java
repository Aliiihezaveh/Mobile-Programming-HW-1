package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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
        Button backToDashBoardButton = findViewById(R.id.BackToAllAnswerButton);
        TextView exerciseTextView = findViewById(R.id.exerciseTextViewName);
        AnswerAdapter adapter = new AnswerAdapter(this);
        ArrayList<Answer> answers = new ArrayList<>();
        Intent intent = getIntent();
        String className = intent.getStringExtra("ClassName");
        String exerciseName = intent.getStringExtra("ExerciseName");
        exerciseTextView.setText(exerciseName);
        Classroom classroom = Classroom.getClassroomByName(className);
        Exercise exercise = Exercise.getExercisesByClassName(classroom);
        if (exercise != null) {
            answers.addAll(exercise.getAnswers());
        }
        adapter.setStudentAnswers(answers);

        backToDashBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashBoardIntent = new Intent(AnswerActivity.this, DashBoardActivity.class);
                startActivity(dashBoardIntent);
                finish();
            }
        });

        allClassRecycleView.setAdapter(adapter);
        allClassRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}