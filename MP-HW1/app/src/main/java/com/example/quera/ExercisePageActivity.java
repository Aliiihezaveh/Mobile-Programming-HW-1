package com.example.quera;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.Models.Account;
import com.example.quera.Models.Answer;
import com.example.quera.Models.Classroom;
import com.example.quera.Models.Exercise;
import com.example.quera.Models.Student;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class ExercisePageActivity extends AppCompatActivity {

    private String nameOfExercise;
    private TextInputEditText inputAnswer;
    private Classroom classroom;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_page);

        Button submitButton = findViewById(R.id.submitButton);
        inputAnswer = findViewById(R.id.answerInputText);
        TextView classroomNameTextView = findViewById(R.id.classNameText);
        TextView exerciseNameTextView = findViewById(R.id.exerciseNameText);
        TextView scoreTextView = findViewById(R.id.scoreText);
        nameOfExercise = getIntent().getStringExtra("ExerciseName");
        String nameOfClass = getIntent().getStringExtra("ClassName");
        exerciseNameTextView.setText(nameOfExercise);
        classroomNameTextView.setText(nameOfClass);
        classroom = Classroom.getClassroomByName(nameOfClass);
        Exercise exercise = Exercise.getExercisesByName(nameOfExercise);
        if(Objects.requireNonNull(Exercise.getExercisesByName(nameOfExercise)).getAnswerByStudent(Account.loggedInAccount.getName()) != null){
            inputAnswer.setText(Exercise.getExercisesByName(nameOfExercise).getAnswerByStudent(Account.loggedInAccount.getName()).getAnswer());
            scoreTextView.setText("Score:    " + String.valueOf(Exercise.getExercisesByName(nameOfExercise).getAnswerByStudent(Account.loggedInAccount.getName()).getScore()));
        }

        if (Objects.requireNonNull(Exercise.getExercisesByName(nameOfExercise)).getAnswerByStudent(Account.loggedInAccount.getName()) == null) {
            submitButton.setText("Submit");
        } else {
            submitButton.setText("Edit");
        }

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Objects.requireNonNull(Exercise.getExercisesByName(nameOfExercise)).getAnswerByStudent(Account.loggedInAccount.getName()) == null){
                    String Answer = Objects.requireNonNull(inputAnswer.getText()).toString();
                    Objects.requireNonNull(Exercise.getExercisesByName(nameOfExercise)).addAnswers(new Answer((Student) Account.loggedInAccount, -1, Answer, classroom,Exercise.getExercisesByName(nameOfExercise)));
                    Intent intent = new Intent(ExercisePageActivity.this, DashBoardActivity.class);
                    HomeActivity.save();
                    startActivity(intent);
                    finish();
                }
                else {
                    String Answer = Objects.requireNonNull(inputAnswer.getText()).toString();
                    Objects.requireNonNull(Exercise.getExercisesByName(nameOfExercise)).getAnswerByStudent(Account.loggedInAccount.getName()).setScore(-1);
                    Objects.requireNonNull(Exercise.getExercisesByName(nameOfExercise)).getAnswerByStudent(Account.loggedInAccount.getName()).editAnswer(Answer);
                    Intent intent = new Intent(ExercisePageActivity.this, DashBoardActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
}