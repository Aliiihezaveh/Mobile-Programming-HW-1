package com.example.quera;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

public class Register_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TabLayout tabLayout = findViewById(R.id.loginTabLayout);
        ViewPager viewPager = findViewById(R.id.loginViewPager);
        Button homeButton = findViewById(R.id.backToHomeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homeIntent = new Intent(Register_Activity.this, HomeActivity.class);
                startActivity(homeIntent);
                finish();
            }
        });


        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPagerAdapter.addFragment(new Student_Register_Fragment(), "Student");
        viewPagerAdapter.addFragment(new Professor_Register_Fragment(), "Professor");

        viewPager.setAdapter(viewPagerAdapter);

    }
}