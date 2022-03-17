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
import com.example.quera.Models.Master;
import com.example.quera.Models.Student;

import java.util.ArrayList;

public class DashBoardActivity extends AppCompatActivity {

    private RecyclerView studentDashBoardRecycleView;
    private EditText studentNameEditText;
    private Button addClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash_board);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("UserName");
        Boolean isStudent;
        Account account;
        if(intent.getStringExtra("Student").equals("True")) {
            isStudent = Boolean.TRUE;
            account = Student.getStudentByUsername(userName);
        }
        else {
            isStudent = Boolean.FALSE;
            account = Master.getMasterByUsername(userName);
        }
        studentNameEditText = findViewById(R.id.showStudentNameText);
        studentNameEditText.setText(userName);
        studentDashBoardRecycleView = findViewById(R.id.studentDashBoardRecycleView);
        addClassButton = findViewById(R.id.addClassButton);

        ArrayList<Classroom> classes;
        if(account.getClassrooms() != null)
            classes = account.getClassrooms();
        else
            classes = new ArrayList<>();

        new Classroom("Mobile Programming", "Dr", "14");
        classes.add(new Classroom("Mobile Programming", "Dr", "14"));
        classes.add(new Classroom("Artificial intelligence", "Dr", "16"));


        ClassroomAdapter adapter = new ClassroomAdapter(this);
        adapter.setStudentClasses(classes);


        addClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isStudent) {
                    classes.clear();
                    for (Classroom classroom : Classroom.getClassrooms())
                        classes.add(classroom);
                    adapter.setStudentClasses(classes);
                }
            }
        });

        studentDashBoardRecycleView.setAdapter(adapter);
        studentDashBoardRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}