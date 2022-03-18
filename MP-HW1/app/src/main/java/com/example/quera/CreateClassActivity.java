package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quera.Models.Account;
import com.example.quera.Models.Classroom;

public class CreateClassActivity extends AppCompatActivity {

    private EditText className;
    private Button createClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_class);

        className = findViewById(R.id.createClassEditText);
        createClass = findViewById(R.id.createClassButton);

        createClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Account.loggedInAccount.addClassrooms(new Classroom(className.getText().toString(), Account.loggedInAccount.getName()));
                Intent dashBoardIntent = new Intent(CreateClassActivity.this, DashBoardActivity.class);
                startActivity(dashBoardIntent);
                finish();
            }
        });
    }
}