package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quera.Models.Classroom;
import com.example.quera.Models.Exercise;
import com.example.quera.Models.Student;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(HomeActivity.this, Register_Activity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(HomeActivity.this, Login_Activity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        Classroom classroom = new Classroom("class1", "pr1");
        Exercise exercise = new Exercise("hw1");
        classroom.addExercises(exercise);
        new Student("a", "a", "ali", "1234");
    }


}