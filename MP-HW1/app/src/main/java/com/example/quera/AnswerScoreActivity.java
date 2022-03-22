package com.example.quera;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.Models.Answer;

public class AnswerScoreActivity extends AppCompatActivity {
    private EditText scoreEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_score);

        Intent intent = getIntent();
        String studentName = intent.getStringExtra("StudentName");
        Answer answer = Answer.getAnswerByStudentName(studentName);

        TextView nameOfTheStudent = findViewById(R.id.studentNameInScore);
        Button setScoreButton = findViewById(R.id.setScoreButton);
        scoreEditText = findViewById(R.id.scoreEditText);
        TextView studentAnswerTextView = findViewById(R.id.studentAnswerTextView);

        if (answer != null) {
            studentAnswerTextView.setText(answer.getAnswer());
            nameOfTheStudent.setText(answer.getStudent().getName());
        }
        setScoreButton.setOnClickListener(view -> {
            if (answer != null) {
                answer.setScore(Integer.parseInt(scoreEditText.getText().toString()));
                HomeActivity.save();
            }
            Intent dashBoardIntent = new Intent(AnswerScoreActivity.this, DashBoardActivity.class);
            startActivity(dashBoardIntent);
        });

    }
}