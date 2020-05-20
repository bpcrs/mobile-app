package com.example.bpcrs.screen.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bpcrs.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bot_navigate_home);

    }
}
