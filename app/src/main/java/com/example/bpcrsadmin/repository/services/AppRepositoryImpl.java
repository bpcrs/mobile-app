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
import com.example.bpcrsadmin.repository.api.ApiClient;
import com.example.bpcrsadmin.repository.callback.CallbackData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepositoryImpl implements AppRepository{

    @Override
    public void loginWithGoogle(CallbackData<Account> callbackData, String token) {

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
