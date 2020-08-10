/*
 * *
 *  * Created by TienND on 6/21/20 1:51 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:49 AM
 *
 */

package com.example.bpcrsadmin.repository.services;

import android.util.Log;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Account;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.model.CarPayload;
import com.example.bpcrsadmin.model.CarsPayload;
import com.example.bpcrsadmin.model.ListCarPayload;
import com.example.bpcrsadmin.model.LoginPayload;
import com.example.bpcrsadmin.repository.api.ApiClient;
import com.example.bpcrsadmin.repository.callback.CallbackData;
import com.example.bpcrsadmin.utils.Constant;
import com.example.bpcrsadmin.utils.SharedPreferenceUtils;

import java.util.List;

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
                callbackData.onSuccess(response.body());
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
                Log.d("RESPONSE", response.body().getData().getName() + response.body().getData().getVin() + response.body().getData().getPrice());

            }

            @Override
            public void onFailure(Call<CarPayload> call, Throwable t) {

            }
        });
    }

    @Override
    public void getMyCars(int id, String jwt, CallbackData<List<Car>> callbackData) {
        AppService service = ApiClient.getClient().create(AppService.class);
        String bearToken = "Bearer " + jwt;
        Call<ListCarPayload> call = service.getMyCar(id, bearToken);
        call.enqueue(new Callback<ListCarPayload>() {
            @Override
            public void onResponse(Call<ListCarPayload> call, Response<ListCarPayload> response) {
                Log.d("CARS", response.body().getData().getCar().get(0).getName() + "");
            }

            @Override
            public void onFailure(Call<ListCarPayload> call, Throwable t) {

            }
        });

    }


}
