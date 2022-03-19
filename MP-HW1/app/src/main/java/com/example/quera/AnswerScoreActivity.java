package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quera.Models.Answer;
import com.example.quera.Models.Student;

public class AnswerScoreActivity extends AppCompatActivity {
    private TextView nameOfTheStudent;
    private Button setScoreButton;
    private EditText scoreEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_score);

        Intent intent = getIntent();
        String studentName = intent.getStringExtra("StudentName");
        Answer answer = Answer.getAnswerByStudentName(studentName);

        nameOfTheStudent = findViewById(R.id.studentNameInScore);
        setScoreButton = findViewById(R.id.setScoreButton);
        scoreEditText = findViewById(R.id.scoreEditText);

        nameOfTheStudent.setText(answer.getStudent().getName());
        setScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer.setScore(Integer.parseInt(scoreEditText.getText().toString()));
                Intent dashBoardIntent = new Intent(AnswerScoreActivity.this, DashBoardActivity.class);
                startActivity(dashBoardIntent);
            }
        });

    }
}