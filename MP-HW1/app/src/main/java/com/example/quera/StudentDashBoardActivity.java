package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.quera.Models.Student;

import java.util.ArrayList;

public class StudentDashBoardActivity extends AppCompatActivity {

    private RecyclerView studentDashBoardRecycleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dash_board);

        studentDashBoardRecycleView = findViewById(R.id.studentDashBoardRecycleView);

        ArrayList<StudentClass> studentClasses = new ArrayList<>();
        studentClasses.add(new StudentClass("Linear Algebra", "Dr", "12"));
        studentClasses.add(new StudentClass("Mobile Programming", "Dr", "14"));
        studentClasses.add(new StudentClass("Artificial intelligence", "Dr", "16"));


        StudentClassAdapter adapter = new StudentClassAdapter(this);
        adapter.setStudentClasses(studentClasses);

        studentDashBoardRecycleView.setAdapter(adapter);
        studentDashBoardRecycleView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}