/*
 * *
 *  * Created by TienND on 6/21/20 12:49 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 12:46 PM
 *
 */

package com.example.bpcrsadmin.screen.monitor;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bpcrsadmin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class MonitorActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DatabaseReference mDatabase;

    private String latitude;
    private String longitude;
    private String time;
    private String address;

    private double la;
    private double lo;

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;

    private TextView tvTimeLocation;
    private TextView tvAddress;

    private static final String SREF = "OLD_LOCATION";

    private LocationListener locationListener;
    private LocationManager locationManager;

    private final long MIN_TIME = 1000;
    private final long MIN_DIST = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor);

        init();

        mDatabase = FirebaseDatabase.getInstance().getReference("Location");
        loadLastLocation();

        mapFragment.getMapAsync(this);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, PackageManager.PERMISSION_GRANTED);

    }

    public void init() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        tvTimeLocation = findViewById(R.id.tv_time_location);
        tvAddress = findViewById(R.id.tv_address);
    }

    public void loadLastLocation() {
        SharedPreferences pref = this.getSharedPreferences(SREF, Context.MODE_PRIVATE);
        if (null != pref) {
            latitude = pref.getString("latiude", "-34");
            longitude = pref.getString("longitude", "151");
            time = pref.getString("time", "21 October 2020");
            address = pref.getString("address", "1 Lê Văn Việt, Quận 9");

            la = Double.parseDouble(latitude);
            lo = Double.parseDouble(longitude);

            //Log.d("last location", la + " - " + lo);
        } else {
            Log.d("Pref null", "pref null");
        }
    }

    public void saveLocation() {
        SharedPreferences pref = this.getSharedPreferences(SREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("latiude", latitude);
        editor.putString("longitude", longitude);
        editor.putString("time", time);
        editor.putString("address", address);

        editor.apply();
    }

    public void getNewLocationFromFirebase() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //get list database from firebase
                String mlatitude = Objects.requireNonNull(dataSnapshot.child("latitude").getValue()).toString().substring(1, Objects.requireNonNull(dataSnapshot.child("latitude").getValue()).toString().length() - 1);
                String mlongitude = Objects.requireNonNull(dataSnapshot.child("longitude").getValue()).toString().substring(1, Objects.requireNonNull(dataSnapshot.child("longitude").getValue()).toString().length() - 1);
                String mTime = Objects.requireNonNull(dataSnapshot.child("time").getValue()).toString().substring(1, Objects.requireNonNull(dataSnapshot.child("time").getValue()).toString().length() - 1);

                String[] stringLat = mlatitude.split(", ");
                Arrays.sort(stringLat);
                //get last
                latitude = stringLat[stringLat.length - 1].split("=")[1];

                String[] stringLong = mlongitude.split(", ");
                Arrays.sort(stringLong);
                longitude = stringLong[stringLong.length - 1].split("=")[1];

                String[] stringTime = mTime.split(", ");
                Arrays.sort(stringTime);
                time = stringTime[stringTime.length - 1].split("=")[1];

                LatLng newLocation = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
                //Log.d("new location", latitude + " " + longitude);
                getAddressOfCarLocation();

                mMap.clear();
                MarkerOptions options = new MarkerOptions().position(newLocation).title("New location")
                        .icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MonitorActivity.this, R.drawable.ic_car)));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15));
                mMap.addMarker(options);

                String formatTime = splitTime(time);
                tvTimeLocation.setText(formatTime);

                saveLocation();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("Can't read db", databaseError.getDetails() + " " + databaseError.getMessage());

            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        getNewLocationFromFirebase();
        mMap = googleMap;

        String formatTime = splitTime(time);
        Log.d("TIME", time);
        tvTimeLocation.setText(formatTime);

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

    public String splitTime(String time) {
        String[] timeArr = time.split(" ");
        return  timeArr[0] + " " + timeArr[1] + " " + timeArr[2] + " " + timeArr[3];
    }

    public void getAddressOfCarLocation() {
        try {
            Geocoder geocoder = new Geocoder(MonitorActivity.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(Double.parseDouble(latitude), Double.parseDouble(longitude), 1);

            String district = addresses.get(0).getSubAdminArea();
            address = addresses.get(0).getAdminArea();

            String displayAddress = district + "-" + address;
            tvAddress.setText(displayAddress);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static Bitmap createCustomMarker(Context context, @DrawableRes int resource) {
        View marker = ((LayoutInflater) Objects.requireNonNull(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.custom_marker_layout, null);

        CircleImageView markerImage =  marker.findViewById(R.id.user_dp);
        markerImage.setImageResource(resource);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        marker.setLayoutParams(new ViewGroup.LayoutParams(40, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();

        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);

        return bitmap;
    }

}
