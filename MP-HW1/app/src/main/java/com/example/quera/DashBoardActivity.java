package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        RecyclerView studentDashBoardRecycleView = findViewById(R.id.studentDashBoardRecycleView);
        EditText studentNameEditText = findViewById(R.id.showStudentNameText);
        EditText classIDEditText = findViewById(R.id.enterClassIDEditText);
        Button addClassButton = findViewById(R.id.addClassButton);
        Button backToRegisterButton = findViewById(R.id.backToRegisterButton);
        Button classIDButton = findViewById(R.id.enterClassIDButton);

        if(!isStudent){
            addClassButton.setText("Create Class");
        }else{
            addClassButton.setText("Add Class");
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

        backToRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(DashBoardActivity.this, Register_Activity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        classIDButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Classroom classroom = Classroom.getClassroomByID(Integer.parseInt(classIDEditText.getText().toString()));
                if(classroom == null)
                    Toast.makeText(getApplicationContext(), "There is no class with this ID", Toast.LENGTH_SHORT).show();
                else{
                    if(!Account.loggedInAccount.getClassrooms().contains(classroom)){
                        Toast.makeText(getApplicationContext(), "you should add this class first",Toast.LENGTH_SHORT).show();
                    }else{
                        Intent classIntent = new Intent(DashBoardActivity.this, ExerciseActivity.class);
                        classIntent.putExtra("ClassName", classroom.getClassName());
                        classIntent.putExtra("ProfessorName",classroom.getProfessorName());
                        startActivity(classIntent);
                    }
                }
            }
        });

        studentDashBoardRecycleView.setAdapter(adapter);
        studentDashBoardRecycleView.setLayoutManager(new LinearLayoutManager(this));
    }
}