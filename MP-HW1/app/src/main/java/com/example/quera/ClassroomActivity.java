package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quera.Models.Account;
import com.example.quera.Models.Classroom;
import java.util.ArrayList;

public class ClassroomActivity extends AppCompatActivity {
    private EditText classIDEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);

        RecyclerView allClassRecycleView = findViewById(R.id.allClassRecycleView);
        ClassroomAdapter adapter = new ClassroomAdapter(this);
        Button backToClassButton = findViewById(R.id.backClassButton);
        Button addClassButton = findViewById(R.id.addClassByIDButton);
        classIDEditText = findViewById(R.id.classIDEditText);
        backToClassButton.setOnClickListener(view -> {
            Intent dashBoardIntent = new Intent(ClassroomActivity.this, DashBoardActivity.class);
            startActivity(dashBoardIntent);
            finish();
        });

        addClassButton.setOnClickListener(view -> {
            Classroom classroom = Classroom.getClassroomByID(Integer.parseInt(classIDEditText.getText().toString()));
            if(classroom == null)
                Toast.makeText(getApplicationContext(), "There is no class with this ID", Toast.LENGTH_SHORT).show();
            else{
                if(!Account.loggedInAccount.getClassrooms().contains(classroom)){
                    Account.loggedInAccount.addClassrooms(classroom);
                    Toast.makeText(getApplicationContext(), "class added",Toast.LENGTH_SHORT).show();
                }else{
                    Intent classIntent = new Intent(ClassroomActivity.this, ExerciseActivity.class);
                    classIntent.putExtra("ClassName", classroom.getClassName());
                    classIntent.putExtra("ProfessorName",classroom.getProfessorName());
                    startActivity(classIntent);
                }
            }
        });

        ArrayList<Classroom> classes = new ArrayList<>(Classroom.getClassrooms());
        adapter.setStudentClasses(classes);
        allClassRecycleView.setAdapter(adapter);
        allClassRecycleView.setLayoutManager(new GridLayoutManager(this, 2));

    }
}