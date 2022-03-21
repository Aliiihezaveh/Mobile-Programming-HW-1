package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quera.Models.Account;
import com.example.quera.Models.Answer;
import com.example.quera.Models.Classroom;
import com.example.quera.Models.Exercise;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        RecyclerView allClassRecycleView = findViewById(R.id.allAnswerRecycleView);
        Button backToDashBoardButton = findViewById(R.id.BackToAllAnswerButton);
        TextView exerciseTextView = findViewById(R.id.exerciseTextViewName);
        EditText changeExerciseNameEditText = findViewById(R.id.changeExerciseNameEditText);
        Button changeExerciseNameButton = findViewById(R.id.changeExerciseNameButton);
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

        for(Answer answer : Answer.getAnswers()){
            if(answer.getExercise().getName().equals(exerciseName)){
                if(!answers.contains(answer))
                    answers.add(answer);
            }
        }

        backToDashBoardButton.setOnClickListener(view -> {
            Intent dashBoardIntent = new Intent(AnswerActivity.this, DashBoardActivity.class);
            startActivity(dashBoardIntent);
            finish();
        });

        changeExerciseNameButton.setOnClickListener(view -> {
            if (exercise != null) {
                if(Account.loggedInAccount.getClass().getName().equals("com.example.quera.Models.Master")) {
                    exercise.setName(changeExerciseNameEditText.getText().toString());
                    exerciseTextView.setText(exercise.getName());
                }else{
                    Toast.makeText(getApplicationContext(), "You don't have permission", Toast.LENGTH_SHORT).show();
                }
            }
        });

        allClassRecycleView.setAdapter(adapter);
        allClassRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}