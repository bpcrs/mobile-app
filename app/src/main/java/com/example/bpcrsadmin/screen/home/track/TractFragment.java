/*
 * *
 *  * Created by TienND on 6/21/20 11:58 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 11:58 AM
 *
 */

package com.example.bpcrsadmin.screen.home.track;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.screen.home.HomeActivity;
import com.example.bpcrsadmin.screen.home.HomePresenter;
import com.example.bpcrsadmin.screen.home.HomeView;
import com.example.bpcrsadmin.screen.home.track.adapter.TrackAdapter;
import com.example.bpcrsadmin.screen.home.track.adapter.TrackItemClickListener;
import com.example.bpcrsadmin.screen.monitor.MonitorActivity;
import com.example.bpcrsadmin.screen.monitor.MonitorView;
import com.example.bpcrsadmin.utils.SharedPreferenceUtils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Executor;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TractFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TractFragment extends Fragment implements TrackItemClickListener, HomeView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Car> mCarList;
    private RecyclerView rvTrack;
    private HomePresenter homePresenter;
    private FusedLocationProviderClient fusedLocationClient;

    public TractFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static TractFragment newInstance() {
        TractFragment fragment = new TractFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    public void bindCarsToRecyclerView(List<Car> carList) {
        TrackAdapter trackAdapter = new TrackAdapter(getContext(), carList, this);
        rvTrack.setAdapter(trackAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvTrack.setLayoutManager(layoutManager);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tract, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvTrack = view.findViewById(R.id.rv_tracks);
        homePresenter = new HomePresenter(this, getActivity());
        String jwt = SharedPreferenceUtils.retrieveData(Objects.requireNonNull(getActivity()), getString(R.string.jwt));
        int id =  SharedPreferenceUtils.retrieveDataInt(Objects.requireNonNull(getActivity()), getString(R.string.id));
        homePresenter.getMyCars(id, jwt);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(Objects.requireNonNull(getActivity()));
        getCurrentLocation();
    }

    @Override
    public void onTrackTapped(Car car) {
        Intent intent = new Intent(getActivity(), MonitorActivity.class);
        intent.putExtra("car", car);
        startActivity(intent);
    }

    @Override
    public void onSuccessGetCar(Car car) {
//        mCarList = new ArrayList<>();
//        mCarList.add(car);
//        bindCarsToRecyclerView(mCarList);
    }

    @Override
    public void onSuccessGetCars(List<Car> cars) {
        mCarList = new ArrayList<>();
        mCarList.addAll(cars);
        bindCarsToRecyclerView(mCarList);
    }

    @Override
    public void onFailGetCar() {

    }

    public void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(Objects.requireNonNull(getActivity()), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            try {
                                Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
                                List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                                String yourAddress = addresses.get(0).getAddressLine(0);
                                SharedPreferenceUtils.saveYourLocation(Objects.requireNonNull(getActivity()), yourAddress);
                                SharedPreferenceUtils.saveCurrentLocation(Objects.requireNonNull(getActivity()), location.getLatitude(), location.getLongitude());
                            } catch (IOException ex) {
                                ex.getMessage();
                            }
                        }
                    }
                });
    }
}
