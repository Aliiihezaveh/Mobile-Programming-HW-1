package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.quera.Models.Account;
import com.example.quera.Models.Classroom;

public class CreateClassActivity extends AppCompatActivity {

    private EditText className;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        className = findViewById(R.id.createClassEditText);
        Button createClass = findViewById(R.id.createClassButton);

        Button backToDashBoardButton = findViewById(R.id.backInCreateClassButton);

        createClass.setOnClickListener(view -> {
            Classroom classroom = new Classroom(className.getText().toString(), Account.loggedInAccount.getName());
            Account.loggedInAccount.addClassrooms(classroom);
            Intent dashBoardIntent = new Intent(CreateClassActivity.this, DashBoardActivity.class);
            startActivity(dashBoardIntent);
        });

        backToDashBoardButton.setOnClickListener(view -> {
            Intent dashBoardIntent = new Intent(CreateClassActivity.this, DashBoardActivity.class);
            startActivity(dashBoardIntent);
            finish();
        });
    }
}