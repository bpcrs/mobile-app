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
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bpcrsadmin.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class MonitorActivity extends AppCompatActivity implements OnMapReadyCallback {

    private DatabaseReference mDatabase;

    private String latiude;
    private String longitude;
    private String time;

    private double la;
    private double lo;

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;

    private TextView tvTimeLocation;
    private Button btUpdateLocation;

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
        btUpdateLocation = findViewById(R.id.bt_updateLocation);
    }

    public void loadLastLocation() {
        SharedPreferences pref = this.getSharedPreferences(SREF, Context.MODE_PRIVATE);
        if (null != pref) {
            latiude = pref.getString("latiude", "-34");
            longitude = pref.getString("longitude", "151");
            time = pref.getString("time", "21 October 2020");

            la = Double.parseDouble(latiude);
            lo = Double.parseDouble(longitude);

            //Log.d("last location", la + " - " + lo);
        } else {
            Log.d("Pref null", "pref null");

        }
    }

    public void saveLocation() {
        SharedPreferences pref = this.getSharedPreferences(SREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString("latiude", latiude);
        editor.putString("longitude", longitude);
        editor.putString("time", time);

        editor.apply();
    }

    public void getNewLocationFromFirebase() {
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
                latiude = stringLat[stringLat.length - 1].split("=")[1];

                String[] stringLong = mlongitude.split(", ");
                Arrays.sort(stringLong);
                longitude = stringLong[stringLong.length - 1].split("=")[1];

                String[] stringTime = mTime.split(", ");
                Arrays.sort(stringTime);
                time = stringTime[stringTime.length - 1].split("=")[1];

                LatLng newLocation = new LatLng(Double.parseDouble(latiude), Double.parseDouble(longitude));
                //Log.d("new location", latiude + " " + longitude);

                mMap.clear();

                MarkerOptions options = new MarkerOptions().position(newLocation).title("New location")
                        .icon(BitmapDescriptorFactory.fromBitmap(createCustomMarker(MonitorActivity.this, R.drawable.ic_car)));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(newLocation, 15));
                mMap.addMarker(options);
                tvTimeLocation.setText(time);
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

        tvTimeLocation.setText(time);

        LatLng lastLocation = new LatLng(la, lo);
        //create market options
        MarkerOptions options = new MarkerOptions().position(lastLocation)
                .title("I'm here");
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lastLocation, 10));
        //add marker
        googleMap.addMarker(options);

//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                latiude = String.valueOf(location.getLatitude());
//                longitude = String.valueOf(location.getLongitude());
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//            }
//        };
//
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        try {
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, MIN_TIME, MIN_DIST, locationListener);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    public void updateLocation(View view) {
        getNewLocationFromFirebase();

    }

    public static Bitmap createCustomMarker(Context context, @DrawableRes int resource) {

        View marker = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);

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
