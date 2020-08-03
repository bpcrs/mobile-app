/*
 * *
 *  * Created by TienND on 6/21/20 11:58 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 11:58 AM
 *
 */

package com.example.bpcrsadmin.screen.home.track;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.screen.home.track.adapter.TrackAdapter;
import com.example.bpcrsadmin.screen.home.track.adapter.TrackItemClickListener;
import com.example.bpcrsadmin.screen.monitor.MonitorActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TractFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TractFragment extends Fragment implements TrackItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Car> mCarList;
    private RecyclerView rvTrack;

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

    public void createCarList() {
        mCarList = new ArrayList<>();
        mCarList.add(new Car(2, "lambo", 650000, "abcd22r4398"));
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

        createCarList();
        bindCarsToRecyclerView(mCarList);
    }

    @Override
    public void onTrackTapped(Car car) {
        Intent intent = new Intent(getActivity(), MonitorActivity.class);
        startActivity(intent);

    }
}
