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
    private FloatingActionButton flbtTrack;

    private Fragment selectedFragment = null;

    private LinearLayout viewContracts;
    private LinearLayout viewCars;

    private Toolbar toolbar;

    private ImageView imgCar;
    private TextView tvCar;

    private ImageView imgContract;
    private TextView tvContract;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        initData();
        setSupportActionBar(botNav);

        //init data
        changeFragment(new CarFragment());

        flbtTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = new TractFragment();
                changeFragment(selectedFragment);
                unpressContract();
                unpressCar();
            }
        });

        viewCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = new CarFragment();
                pressMyCar();
                unpressContract();
                changeFragment(selectedFragment);
            }
        });

        viewContracts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFragment = new ContractFragment();
                unpressCar();
                pressContract();
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
        imgCar = findViewById(R.id.ic_car);
        tvCar = findViewById(R.id.tv_carIcon);
        imgContract = findViewById(R.id.img_contract);
        tvContract = findViewById(R.id.tv_contract);
    }

    public void changeFragment(Fragment selectedFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
    }

    public void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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

    public void unpressCar() {
        imgCar.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_car));
        tvCar.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightgray));
    }

    public void pressContract() {
        imgContract.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_contract2));
        tvContract.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
    }

    public void unpressContract() {
        imgContract.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_contract));
        tvContract.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightgray));
    }

}
