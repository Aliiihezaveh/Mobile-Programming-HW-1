package com.example.quera;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.Models.Account;
import com.example.quera.Models.Answer;
import com.example.quera.Models.Classroom;
import com.example.quera.Models.Exercise;

public class HomeActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(view -> {
            Intent loginIntent = new Intent(HomeActivity.this, Register_Activity.class);
            startActivity(loginIntent);
            finish();
        });

        loginButton.setOnClickListener(view -> {
            Intent loginIntent = new Intent(HomeActivity.this, Login_Activity.class);
            startActivity(loginIntent);
            finish();
        });
        initialize();
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initialize(){
        Account.initializeAccounts();
        Classroom.initializeClassrooms();
        Exercise.initializeExercises();
        Answer.initializeAnswers();
    }

    public static void save(){
        Account.saveAccounts();
        Classroom.saveClassrooms();
        Exercise.saveExercises();
        Answer.saveAnswers();
    }
}