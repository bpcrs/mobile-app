/*
 * *
 *  * Created by TienND on 6/21/20 12:06 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 12:06 PM
 *
 */

package com.example.bpcrsadmin.screen.home.car;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.screen.detail.DetailFragment;
import com.example.bpcrsadmin.screen.home.HomePresenter;
import com.example.bpcrsadmin.screen.home.HomeView;
import com.example.bpcrsadmin.screen.home.car.adapter.CarAdapter;
import com.example.bpcrsadmin.screen.home.car.adapter.CarItemClickListener;
import com.example.bpcrsadmin.utils.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarFragment extends Fragment implements CarItemClickListener, HomeView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Car> mCarList;
    private RecyclerView rvCar;
    private CircleImageView avatar;
    private HomePresenter homePresenter;


    public CarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarFragment newInstance() {
        CarFragment fragment = new CarFragment();
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
        CarAdapter carAdapter = new CarAdapter(getContext(), carList, this);
        rvCar.setAdapter(carAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCar.setLayoutManager(layoutManager);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_car, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCar = view.findViewById(R.id.rv_cars);
        avatar = view.findViewById(R.id.avatar);
        homePresenter = new HomePresenter(this, getActivity());
        homePresenter.getCarById(1);
        setAvatarUser();
//        createCarList();

    }

    public void setAvatarUser() {
        String imgUrl = SharedPreferenceUtils.retrieveData(Objects.requireNonNull(getActivity()), getString(R.string.imageUrl));
        Glide.with(this).load(imgUrl).into(avatar);
    }

    @Override
    public void onCarTapped(Car car) {
        Fragment detail = DetailFragment.newInstance(car.getName(), car.getVin());
        //add the fragment to activity
        if (getFragmentManager() != null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, detail);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onSuccessGetCar(Car car) {
        mCarList = new ArrayList<>();
        mCarList.add(car);
        bindCarsToRecyclerView(mCarList);
    }

    @Override
    public void onSuccessGetCars(List<Car> cars) {

    }

    @Override
    public void onFailGetCar() {

    }
}
