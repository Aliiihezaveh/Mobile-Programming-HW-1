package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.quera.Models.Classroom;
import com.example.quera.Models.Exercise;


public class CreateExerciseActivity extends AppCompatActivity {

    private EditText exerciseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        exerciseName = findViewById(R.id.createExerciseEditText);
        Button createExercise = findViewById(R.id.createExerciseButton);
        Button backToDashBoard = findViewById(R.id.backInCreateExerciseButton);

        Intent intent = getIntent();
        String className = intent.getStringExtra("ClassName");
        Classroom classroom = Classroom.getClassroomByName(className);

        createExercise.setOnClickListener(view -> {
            Intent exerciseIntent = new Intent(CreateExerciseActivity.this, ExerciseActivity.class);
            if (classroom != null) {
                classroom.addExercises(new Exercise(classroom, exerciseName.getText().toString()));
                exerciseIntent.putExtra("ClassName",classroom.getClassName());
                exerciseIntent.putExtra("ProfessorName",classroom.getProfessorName());
            }
            startActivity(exerciseIntent);
            finish();
        });

        backToDashBoard.setOnClickListener(view -> {
            Intent dashBoardIntent = new Intent(CreateExerciseActivity.this, DashBoardActivity.class);
            startActivity(dashBoardIntent);
            finish();
        });
    }
}