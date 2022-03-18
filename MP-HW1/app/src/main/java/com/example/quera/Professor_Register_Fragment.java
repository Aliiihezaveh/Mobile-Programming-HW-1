package com.example.quera;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.quera.Models.Account;
import com.example.quera.Models.Master;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Professor_Register_Fragment extends Fragment {

    private Button registerButton;
    private TextInputEditText name;
    private TextInputEditText username;
    private TextInputEditText collage;
    private TextInputEditText password;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_professor__register_, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        registerButton = view.findViewById(R.id.professorRegisterButton);
        name = view.findViewById(R.id.professorEditTextName);
        username = view.findViewById(R.id.professorEditTextUserName);
        collage = view.findViewById(R.id.EditTextCollegeName);
        password = view.findViewById(R.id.professorEditTextPassword);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = Objects.requireNonNull(name.getText()).toString();
                String inputUsername = Objects.requireNonNull(username.getText()).toString();
                String inputCollage = Objects.requireNonNull(collage.getText()).toString();
                String inputPassword = Objects.requireNonNull(password.getText()).toString();

                if(Account.canRegister(inputUsername, inputName, inputPassword)){
                    Account.loggedInAccount = new Master(inputUsername, inputPassword, inputName, inputCollage);
                    Toast.makeText(getActivity(), "wellCome to quera",Toast.LENGTH_LONG).show();
                    Intent professorDashBoardIntent = new Intent(getActivity(), DashBoardActivity.class);
                    startActivity(professorDashBoardIntent);
                }
                else{
                    Toast.makeText(getActivity(), "Register failed!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}