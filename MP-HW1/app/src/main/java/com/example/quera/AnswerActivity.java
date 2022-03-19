package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quera.Models.Answer;
import com.example.quera.Models.Classroom;

import java.util.ArrayList;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);

        RecyclerView allClassRecycleView = findViewById(R.id.allAnswerRecycleView);
        AnswerAdapter adapter = new AnswerAdapter(this);
        ArrayList<Answer> answers = new ArrayList<>();
        adapter.setStudentAnswers(answers);
        allClassRecycleView.setAdapter(adapter);
        allClassRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}