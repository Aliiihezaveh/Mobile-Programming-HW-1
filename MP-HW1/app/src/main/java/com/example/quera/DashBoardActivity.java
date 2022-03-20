package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.quera.Models.Account;
import com.example.quera.Models.Classroom;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash_board);

        Account account = Account.loggedInAccount;
        Boolean isStudent = account.getClass().getName().equals("com.example.quera.Models.Student")? Boolean.TRUE:Boolean.FALSE;

        EditText studentNameEditText = findViewById(R.id.showStudentNameText);
        RecyclerView studentDashBoardRecycleView = findViewById(R.id.studentDashBoardRecycleView);
        Button addClassButton = findViewById(R.id.addClassButton);

        if(!isStudent){
            addClassButton.setText("Create Class");
        }

        studentNameEditText.setText(account.getName());

        ArrayList<Classroom> classes = new ArrayList<>(account.getClassrooms());
        ClassroomAdapter adapter = new ClassroomAdapter(this);

        adapter.setStudentClasses(classes);


        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStudent) {
                    Intent allClassIntent = new Intent(DashBoardActivity.this, ClassroomActivity.class);
                    startActivity(allClassIntent);
                    finish();
                }
                else {
                    Intent createClassIntent = new Intent(DashBoardActivity.this, CreateClassActivity.class);
                    startActivity(createClassIntent);
                    finish();
                }
            }
        });

        studentDashBoardRecycleView.setAdapter(adapter);
        studentDashBoardRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}