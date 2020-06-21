/*
 * *
 *  * Created by TienND on 6/21/20 1:53 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/1/20 12:47 PM
 *
 */

package com.example.bpcrsadmin.screen.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.screen.home.HomeActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mCarModel;
    private String mCarNumberPlate;

    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance(String param1, String param2) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCarModel = getArguments().getString(ARG_PARAM1);
            mCarNumberPlate = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvCarModel = view.findViewById(R.id.tv_carModel);
        TextView tvCarNumberPlate = view.findViewById(R.id.tv_number_plate);

        tvCarModel.setText(mCarModel);
        tvCarNumberPlate.setText(mCarNumberPlate);

            if (null != ((HomeActivity) getActivity()).getSupportActionBar()) {
                ((HomeActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                ((HomeActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
            }

    }

}
