package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class StudentDashBoardActivity extends AppCompatActivity {

    private RecyclerView studentDashBoardRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash_board);

        studentDashBoardRecycleView = findViewById(R.id.studentDashBoardRecycleView);
    }
}