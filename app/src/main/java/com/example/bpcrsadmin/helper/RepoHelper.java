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
import com.example.bpcrsadmin.model.LoginPayload;
import com.example.bpcrsadmin.repository.callback.CallbackData;
import com.example.bpcrsadmin.repository.services.AppRepository;
import com.example.bpcrsadmin.repository.services.AppRepositoryImpl;

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
}
