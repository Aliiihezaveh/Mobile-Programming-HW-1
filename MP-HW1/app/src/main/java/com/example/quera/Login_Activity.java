package com.example.quera;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quera.Models.Account;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class Login_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextInputEditText inputUsername = findViewById(R.id.editTextUsername);
        TextInputEditText inputPassword = findViewById(R.id.editTextPassword);
        Button loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = Objects.requireNonNull(inputUsername.getText()).toString();
                String password = Objects.requireNonNull(inputPassword.getText()).toString();
                if(Account.canLogin(username, password)){
                    Toast.makeText(Login_Activity.this, "success:))", Toast.LENGTH_SHORT).show();
                    Account.loggedInAccount = Account.getAccountByUsername(username);
                    Intent userDashBoardIntent = new Intent(Login_Activity.this, DashBoardActivity.class);
                    startActivity(userDashBoardIntent);
                }
                else{
                    Toast.makeText(Login_Activity.this, "Wrong info!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
