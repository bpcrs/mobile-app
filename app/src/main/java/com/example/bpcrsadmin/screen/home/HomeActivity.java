/*
 * *
 *  * Created by TienND on 6/21/20 1:53 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/9/20 12:30 AM
 *
 */

package com.example.bpcrsadmin.screen.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.screen.home.car.CarFragment;
import com.example.bpcrsadmin.screen.home.contract.ContractFragment;
import com.example.bpcrsadmin.screen.home.track.TractFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity  {

    private BottomAppBar botNav;
    private FloatingActionButton fltTrack;

    private Fragment selectedFragment = null;

    private LinearLayout viewContracts;
    private LinearLayout viewCars;

    private Toolbar toolbar;

    private ImageView imgCar;
    private TextView tvCar;

    private ImageView imgContract;
    private TextView tvContract;
    private TextView tvTracking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initData();
        setSupportActionBar(botNav);

        //init fragment
        changeFragment(CarFragment.newInstance());
        pressMyCar();

        fltTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = TractFragment.newInstance();
                changeFragment(selectedFragment);
                unpressedContract();
                unpressedCar();
                pressTracking();
            }
        });

        viewCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = CarFragment.newInstance();
                pressMyCar();
                unpressedContract();
                unpressedTracking();
                changeFragment(selectedFragment);
            }
        });

        viewContracts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = ContractFragment.newInstance();
                unpressedCar();
                pressContract();
                unpressedTracking();
                changeFragment(selectedFragment);
            }
        });

        setupToolbar();
        // OneSignal Initialization
        /*
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

         */
    }

    public void initData() {
        botNav = findViewById(R.id.bot_navigate_home);
        fltTrack = findViewById(R.id.fab_track);
        viewCars = findViewById(R.id.view_cars);
        viewContracts = findViewById(R.id.view_contracts);
        toolbar = findViewById(R.id.toolbar);
        imgCar = findViewById(R.id.ic_car);
        tvCar = findViewById(R.id.tv_carIcon);
        imgContract = findViewById(R.id.img_contract);
        tvContract = findViewById(R.id.tv_contract);
        tvTracking = findViewById(R.id.tv_tracking);
    }

    public void changeFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    public void pressMyCar() {
        imgCar.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_car2));
        tvCar.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
    }

    public void pressContract() {
        imgContract.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_contract2));
        tvContract.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
    }

    public void pressTracking() {
        tvTracking.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
    }

    public void unpressedCar() {
        imgCar.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_car));
        tvCar.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightgray));
    }

    public void unpressedContract() {
        imgContract.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_contract));
        tvContract.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightgray));
    }

    public void unpressedTracking() {
        tvTracking.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightgray));
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        if (getSupportActionBar() != null && getSupportFragmentManager().getBackStackEntryCount() == 0) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
        return true;
    }

}
