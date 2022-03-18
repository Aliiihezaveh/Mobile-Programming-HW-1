package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.quera.Models.Exercise;

import java.util.ArrayList;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        RecyclerView exerciseRecycleView = findViewById(R.id.exerciseRecycleView);
        ExerciseAdapter adapter = new ExerciseAdapter(this);
        TextView className = findViewById(R.id.classNameTextView);
        TextView professorName = findViewById(R.id.professorNameTextView);
        Intent intent = getIntent();
        className.setText(intent.getStringExtra("ClassName"));
        professorName.setText(intent.getStringExtra("ProfessorName"));
        ArrayList<Exercise> exercises = new ArrayList<>();
        exercises.add(new Exercise("Home Work 1"));
        exercises.add(new Exercise("Home Work 1"));
        exercises.add(new Exercise("Home Work 1"));
        exercises.add(new Exercise("Home Work 1"));
        exercises.add(new Exercise("Home Work 1"));
        exercises.add(new Exercise("Home Work 1"));
        exercises.add(new Exercise("Home Work 1"));
        exercises.add(new Exercise("Home Work 1"));
        exercises.add(new Exercise("Home Work 1"));
        adapter.setExercisesClasses(exercises);
        exerciseRecycleView.setAdapter(adapter);
        exerciseRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}