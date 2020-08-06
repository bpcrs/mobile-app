/*
 * *
 *  * Created by TienND on 6/21/20 1:51 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:49 AM
 *
 */

package com.example.bpcrsadmin.repository.services;

import android.util.Log;

import com.example.bpcrsadmin.model.Account;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.model.CarPayload;
import com.example.bpcrsadmin.model.LoginPayload;
import com.example.bpcrsadmin.repository.api.ApiClient;
import com.example.bpcrsadmin.repository.callback.CallbackData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepositoryImpl implements AppRepository{


    @Override
    public void loginWithGoogle(CallbackData<LoginPayload> callbackData, String token) {
        AppService service = ApiClient.getClient().create(AppService.class);
        Call<LoginPayload> call = service.loginWithGoogle(token);
        call.enqueue(new Callback<LoginPayload>() {
            @Override
            public void onResponse(Call<LoginPayload> call, Response<LoginPayload> response) {
                Log.d("LOGIN SUCCES", response.body().getData());
            }

            @Override
            public void onFailure(Call<LoginPayload> call, Throwable t) {

            }
        });
    }

    @Override
    public void getCarById(int carId, CallbackData<Car> callbackData) {
        AppService service = ApiClient.getClient().create(AppService.class);
        Call<CarPayload> call = service.getCarById(carId);
        call.enqueue(new Callback<CarPayload>() {
            @Override
            public void onResponse(Call<CarPayload> call, Response<CarPayload> response) {
                Log.d("RESPONSE", response.body().getData().getName());
            }

            @Override
            public void onFailure(Call<CarPayload> call, Throwable t) {

            }
        });
    }
}
