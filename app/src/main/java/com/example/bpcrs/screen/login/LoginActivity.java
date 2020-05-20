package com.example.bpcrs.screen.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.bpcrs.MainActivity;
import com.example.bpcrs.R;
import com.example.bpcrs.screen.verify.VerifyActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseUser mCurrentUser;
    private Button btLoginGmail;
    private ProgressBar progressBar;
    private CountDownTimer mCountDownTimer;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

//        mAuth = FirebaseAuth.getInstance();
//        mCurrentUser = mAuth.getCurrentUser();

        btLoginGmail.setOnClickListener(this);



    }

    private void init() {
        btLoginGmail = findViewById(R.id.bt_loginGmail);
        progressBar = findViewById(R.id.progressBar);
    }

    @Override
    public void onClick(View view) {
        btLoginGmail.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        mCountDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(LoginActivity.this, VerifyActivity.class);
                startActivity(intent);
            }
        };
        mCountDownTimer.start();
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (mCurrentUser == null) {
//            Intent homeIntent = new Intent(LoginActivity.this, MainActivity.class);
//            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(homeIntent);
//            finish();
//        }
//    }



}
