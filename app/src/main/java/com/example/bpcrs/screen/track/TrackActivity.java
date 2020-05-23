package com.example.bpcrs.screen.track;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.bpcrs.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

public class TrackActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DatabaseReference mDatabase;
    private String latiude;
    private String longitude;
    private String time;

    private double la;
    private double lo;

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;

    private TextView tvTimeLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        mDatabase = FirebaseDatabase.getInstance().getReference("Location");
        testPushFirebase();
        loadLastLocation();

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        tvTimeLocation = findViewById(R.id.tv_time_location);
        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

    }



    public void saveLocation() {
        SharedPreferences pref = this.getSharedPreferences("Location", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("latiude", latiude);
        editor.putString("longitude", longitude);
        editor.putString("time", time);

        editor.apply();
    }

    public void loadLastLocation() {
        SharedPreferences pref = this.getSharedPreferences("Location", Context.MODE_PRIVATE);
        if (null != pref) {
            latiude = pref.getString("latiude", "-34");
            longitude = pref.getString("longitude", "151");
            time = pref.getString("time", "21 October 2020");

            la = Double.parseDouble(latiude);
            lo = Double.parseDouble(longitude);

            Log.d("last location", la + " - " + lo);
        } else {
            Log.d("Pref null", "pref null");

        }
    }

    public void testPushFirebase() {
        mDatabase.child("latitude").push().setValue("-34");
        mDatabase.child("longitude").push().setValue("151");
        mDatabase.child("time").push().setValue("21 October 2020");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
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
                latiude = stringLat[stringLat.length-1].split("=")[1];

                String[] stringLong = mlongitude.split(", ");
                Arrays.sort(stringLong);
                longitude = stringLong[stringLong.length - 1].split("=")[1];

                String[] stringTime = mTime.split(", ");
                Arrays.sort(stringTime);
                time = stringTime[stringTime.length - 1].split("=")[1];

                LatLng newLocation = new LatLng(Double.parseDouble(latiude), Double.parseDouble(longitude));
                Log.d("new location", latiude + " " + longitude);
                mMap.clear();

                MarkerOptions options = new MarkerOptions().position(newLocation).title("New location");
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 10));
                mMap.addMarker(options);

                saveLocation();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Can't read db", databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });

        mMap = googleMap;

        //loadLastLocation();

        tvTimeLocation.setText(time);

        LatLng lastLocation = new LatLng(la, lo);
        //create market options
        MarkerOptions options = new MarkerOptions().position(lastLocation)
                .title("I'm here");
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 10));
        //add marker
        googleMap.addMarker(options);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
