package com.example.quera;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.quera.Models.Account;
import com.example.quera.Models.Student;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Student_Register_Fragment extends Fragment {

    private TextInputEditText name;
    private TextInputEditText username;
    private TextInputEditText studentId;
    private TextInputEditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student__register_, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button registerButton = view.findViewById(R.id.studentRegisterButton);
        name = view.findViewById(R.id.studentEditTextName);
        username = view.findViewById(R.id.studentEditTextUserName);
        studentId = view.findViewById(R.id.EditTextStudentNumber);
        password = view.findViewById(R.id.studentEditTextPassword);


        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = Objects.requireNonNull(name.getText()).toString();
                String inputUsername = Objects.requireNonNull(username.getText()).toString();
                String inputStudentId = Objects.requireNonNull(studentId.getText()).toString();
                String inputPassword = Objects.requireNonNull(password.getText()).toString();

                if(Account.canRegister(inputUsername, inputName, inputPassword)){
                    Account.loggedInAccount = new Student(inputUsername, inputPassword, inputName, inputStudentId);
                    Toast.makeText(getActivity(), "wellCome to quera",Toast.LENGTH_LONG).show();
                    Intent studentDashBoardIntent = new Intent(getActivity(), DashBoardActivity.class);
                    HomeActivity.save();
                    Toast.makeText(getActivity(), Account.saveAccounts(), Toast.LENGTH_SHORT).show();
                    startActivity(studentDashBoardIntent);
                }
                else{
                    Toast.makeText(getActivity(), "Register failed!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}