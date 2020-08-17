/*
 * *
 *  * Created by TienND on 8/2/20 12:30 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/2/20 12:30 AM
 *
 */

package com.example.bpcrsadmin.helper;

import android.content.Context;

import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.model.Distance;
import com.example.bpcrsadmin.model.payload.LoginPayload;
import com.example.bpcrsadmin.model.request.DistanceRequest;
import com.example.bpcrsadmin.repository.callback.CallbackData;
import com.example.bpcrsadmin.repository.services.AppRepository;
import com.example.bpcrsadmin.repository.services.AppRepositoryImpl;

import java.util.List;

public class RepoHelper {
    private Context mContext;
    private AppRepository appRepository;

    public RepoHelper(Context context) {
        mContext = context;
        appRepository = new AppRepositoryImpl();
    }

    public void getCarById(int id, final CallbackData<Car> callbackData) {
        appRepository.getCarById(id, callbackData);
    }

    public void loginWithGoogle(String token, final CallbackData<LoginPayload> callbackData) {
        appRepository.loginWithGoogle(callbackData, token);
    }

    public void getMyCars(int id, String jwt, final CallbackData<List<Car>> callbackData) {
        appRepository.getMyCars(id, jwt, callbackData);
    }

    public void getDistanceBetweenTwoLocation(DistanceRequest request, final CallbackData<Distance> callbackData) {
        appRepository.getDistance(request, callbackData);
    }
}
