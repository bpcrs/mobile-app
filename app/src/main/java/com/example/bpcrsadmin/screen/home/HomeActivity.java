package com.example.bpcrsadmin.screen.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.screen.home.account.AccountFragment;
import com.example.bpcrsadmin.screen.home.car.CarFragment;
import com.example.bpcrsadmin.screen.home.contract.ContractFragment;
import com.example.bpcrsadmin.screen.home.track.TractFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    private BottomAppBar botNav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        botNav = findViewById(R.id.bot_navigate_home);
        setSupportActionBar(botNav);

        //botNav.setOnContextClickListener(navListener);

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new CarFragment()).commit();

    }

//    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
//            new BottomNavigationView.OnNavigationItemSelectedListener() {
//                @Override
//                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment selectedFragment = null;
//
//                    switch (item.getItemId()) {
//                        case R.id.nav_car:
//                            selectedFragment = new CarFragment();
//                            break;
//                        case R.id.nav_contract:
//                            selectedFragment = new ContractFragment();
//                            break;
//
//                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
//                    return true;
//                }
//            };

}
