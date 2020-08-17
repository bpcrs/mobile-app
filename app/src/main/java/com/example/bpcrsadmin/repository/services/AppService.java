/*
 * *
 *  * Created by TienND on 6/21/20 1:49 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:47 AM
 *
 */

package com.example.bpcrsadmin.repository.services;


import com.example.bpcrsadmin.model.payload.CarPayload;
import com.example.bpcrsadmin.model.payload.DistancePayload;
import com.example.bpcrsadmin.model.payload.ListCarPayload;
import com.example.bpcrsadmin.model.payload.LoginPayload;
import com.example.bpcrsadmin.model.request.DistanceRequest;
import com.example.bpcrsadmin.repository.api.ApiConfig;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AppService {
    @POST(ApiConfig.Api.LOGIN_BY_GOOGLE)
    Call<LoginPayload> loginWithGoogle(@Body String token);

    @GET(ApiConfig.Api.GET_CAR)
    Call<CarPayload> getCarById(@Path("id") int id);

    @GET(ApiConfig.Api.GET_MY_CAR)
    Call<ListCarPayload> getMyCar(@Path("id") int id, @Header("Authorization") String bearToken);

    @POST(ApiConfig.Api.GET_DISTANCE)
    Call<DistancePayload> getDistanceBetweenTwoLocation(@Body DistanceRequest request);
}
