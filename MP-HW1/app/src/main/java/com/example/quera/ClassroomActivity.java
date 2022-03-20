package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.quera.Models.Classroom;
import java.util.ArrayList;

public class ClassroomActivity extends AppCompatActivity {
    private Button backToClassButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        RecyclerView allClassRecycleView = findViewById(R.id.allClassRecycleView);
        ClassroomAdapter adapter = new ClassroomAdapter(this);
        backToClassButton = findViewById(R.id.backClassButton);
        backToClassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dashBoardIntent = new Intent(ClassroomActivity.this, DashBoardActivity.class);
                startActivity(dashBoardIntent);
                finish();
            }
        });
        ArrayList<Classroom> classes = new ArrayList<>(Classroom.getClassrooms());
        adapter.setStudentClasses(classes);
        allClassRecycleView.setAdapter(adapter);
        allClassRecycleView.setLayoutManager(new GridLayoutManager(this, 2));

    }
}