package com.example.bpcrsadmin.screen.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.screen.home.HomeActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference mDatabase;

    private Button btLogin;
    private ProgressBar progressBar;

    private CountDownTimer mCountDownTimer;;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        //loadDataFromFirebase();
        btLogin.setOnClickListener(this);
    }

    public void init() {
        btLogin = findViewById(R.id.bt_loginGmail);
        progressBar = findViewById(R.id.progressBar);
    }

    public void loadDataFromFirebase() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Location");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //get list database from firebase
                String mlatiude = dataSnapshot.child("latitude").getValue().toString().substring(1, dataSnapshot.child("latitude").getValue().toString().length() - 1);
                String mlongitude = dataSnapshot.child("longitude").getValue().toString().substring(1, dataSnapshot.child("longitude").getValue().toString().length() - 1);
                String mTime = dataSnapshot.child("time").getValue().toString().substring(1, dataSnapshot.child("time").getValue().toString().length() - 1);

                String[] stringLat = mlatiude.split(", ");
                Arrays.sort(stringLat);
                //get last
                String latiude = stringLat[stringLat.length-1].split("=")[1];

                String[] stringLong = mlongitude.split(", ");
                Arrays.sort(stringLong);
                String longitude = stringLong[stringLong.length - 1].split("=")[1];

                String[] stringTime = mTime.split(", ");
                Arrays.sort(stringTime);
                String time = stringTime[stringTime.length - 1].split("=")[1];

                Log.d("Success firebase", latiude + " " + longitude + " " + time);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Failure firebase", databaseError.getDetails());
            }
        });
    }

    @Override
    public void onClick(View view) {
        btLogin.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        mCountDownTimer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        };
        mCountDownTimer.start();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
