package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quera.Models.Account;
import com.example.quera.Models.Classroom;
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
        Button enterExercise = findViewById(R.id.enterExerciseByNameButton);
        EditText exerciseNameEditText = findViewById(R.id.nameOfExerciseEditText);
        Button dashBoardButton = findViewById(R.id.dashboardInExerciseButton);
        Button createExerciseButton = findViewById(R.id.MasterCreateExercise);
        Intent intent = getIntent();
        String nameOfTheClass = intent.getStringExtra("ClassName");
        className.setText(nameOfTheClass);
        professorName.setText(intent.getStringExtra("ProfessorName"));
        Classroom classroom = Classroom.getClassroomByName(nameOfTheClass);
        ArrayList<Exercise> exercises = new ArrayList<>();
        if (classroom != null) {
            exercises.addAll(classroom.getExercises());
        }
        adapter.setExercisesClasses(exercises);
        exerciseRecycleView.setAdapter(adapter);
        exerciseRecycleView.setLayoutManager(new GridLayoutManager(this, 2));

        createExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Account.loggedInAccount.getClass().getName().equals("com.example.quera.Models.Student")) {
                    Toast.makeText(getApplicationContext(), "You can't create exercise", Toast.LENGTH_SHORT).show();
                } else {
                    Intent createExerciseIntent = new Intent(ExerciseActivity.this, CreateExerciseActivity.class);
                    createExerciseIntent.putExtra("ClassName", nameOfTheClass);
                    startActivity(createExerciseIntent);
                    finish();
                }
            }
        });

        enterExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Account.loggedInAccount.getClass().getName().equals("com.example.quera.Models.Student")) {
                    for (Exercise exercise : exercises) {
                        if (exercise.getName().equals(exerciseNameEditText.getText().toString())) {
                            Intent studentExerciseIntent = new Intent(ExerciseActivity.this, ExercisePageActivity.class);
                            studentExerciseIntent.putExtra("ExerciseName", exercise.getName());
                            startActivity(studentExerciseIntent);
                        }
                    }
                }else {
                    for (Exercise exercise : exercises) {
                        if (exercise.getName().equals(exerciseNameEditText.getText().toString())) {
                            Intent studentExerciseIntent = new Intent(ExerciseActivity.this, AnswerActivity.class);
                            studentExerciseIntent.putExtra("ExerciseName", exercise.getName());
                            studentExerciseIntent.putExtra("ClassName",nameOfTheClass);
                            startActivity(studentExerciseIntent);
                        }
                    }
                }
            }
        });

        dashBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashBoardIntent = new Intent(ExerciseActivity.this, DashBoardActivity.class);
                startActivity(dashBoardIntent);
            }
        });
    }
}