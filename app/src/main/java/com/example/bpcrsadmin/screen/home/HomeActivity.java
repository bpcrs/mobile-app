package com.example.bpcrsadmin.screen.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.screen.home.car.CarFragment;
import com.example.bpcrsadmin.screen.home.contract.ContractFragment;
import com.example.bpcrsadmin.screen.home.track.TractFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HomeActivity extends AppCompatActivity  {

    private BottomAppBar botNav;
    private FloatingActionButton flbtTrack;

    private Fragment selectedFragment = null;

    private LinearLayout viewContracts;
    private LinearLayout viewCars;

    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initData();
        setSupportActionBar(botNav);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarFragment()).commit();

        flbtTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = new TractFragment();
                changeFragment(selectedFragment);
            }
        });

        viewCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = new CarFragment();
                changeFragment(selectedFragment);
            }
        });

        viewContracts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = new ContractFragment();
                changeFragment(selectedFragment);
            }
        });

        setupToolbar();
    }

    public void initData() {
        botNav = findViewById(R.id.bot_navigate_home);
        flbtTrack = findViewById(R.id.fab_track);
        viewCars = findViewById(R.id.view_cars);
        viewContracts = findViewById(R.id.view_contracts);
        toolbar = findViewById(R.id.toolbar);
    }

    public void changeFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);
        //getSupportActionBar().setIcon(R.drawable.ic_logo);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

}
