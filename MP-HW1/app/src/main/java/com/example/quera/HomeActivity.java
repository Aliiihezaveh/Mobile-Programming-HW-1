package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

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
    }
}