/*
 * *
 *  * Created by TienND on 6/21/20 1:51 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:51 AM
 *
 */

package com.example.bpcrsadmin.repository.services;

import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.model.Distance;
import com.example.bpcrsadmin.model.payload.DistancePayload;
import com.example.bpcrsadmin.model.payload.LoginPayload;
import com.example.bpcrsadmin.model.request.DistanceRequest;
import com.example.bpcrsadmin.repository.callback.CallbackData;

import java.util.List;

public interface AppRepository {
    void loginWithGoogle(CallbackData<LoginPayload> callbackData, String token);
    void getCarById(int carId, CallbackData<Car> callbackData);
    void getMyCars(int id, String jwt, CallbackData<List<Car>> callbackData);
    void getDistance(DistanceRequest request, CallbackData<Distance> callbackData);
}
