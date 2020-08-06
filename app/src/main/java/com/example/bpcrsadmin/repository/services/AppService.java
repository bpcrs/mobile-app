/*
 * *
 *  * Created by TienND on 6/21/20 1:49 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:47 AM
 *
 */

package com.example.bpcrsadmin.repository.services;


import com.example.bpcrsadmin.model.Account;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.model.CarPayload;
import com.example.bpcrsadmin.model.LoginPayload;
import com.example.bpcrsadmin.repository.api.ApiConfig;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface AppService {
    @POST(ApiConfig.Api.LOGIN_BY_GOOGLE)
    Call<LoginPayload> loginWithGoogle(@Body String token);

    @GET(ApiConfig.Api.GET_CAR)
    Call<CarPayload> getCarById(@Path("id") int id);
}
