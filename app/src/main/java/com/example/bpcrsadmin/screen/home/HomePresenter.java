/*
 * *
 *  * Created by TienND on 8/2/20 12:40 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/2/20 12:40 AM
 *
 */

package com.example.bpcrsadmin.screen.home;

import android.content.Context;

import com.example.bpcrsadmin.helper.RepoHelper;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.repository.callback.CallbackData;

import java.util.List;

public class HomePresenter {
    private HomeView mHomeView;
    private Context mContext;
    private RepoHelper mReoHelper;

    public HomePresenter(HomeView homeView, Context context) {
        mHomeView = homeView;
        mContext = context;
        mReoHelper = new RepoHelper(mContext);
    }

    public void getCarById(int id) {
        mReoHelper.getCarById(id, new CallbackData<Car>() {
            @Override
            public void onSuccess(Car car) {
                mHomeView.onSuccessGetCar(car);
            }

            @Override
            public void onFail(String message) {
                mHomeView.onFailGetCar();
            }
        });
    }

    public void getMyCars(int id, String jwt) {
        mReoHelper.getMyCars(id, jwt, new CallbackData<List<Car>>() {
            @Override
            public void onSuccess(List<Car> cars) {
//                mHomeView.onSuccessGetCars(cars);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
