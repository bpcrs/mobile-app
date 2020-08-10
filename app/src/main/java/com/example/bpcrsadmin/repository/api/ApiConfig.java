/*
 * *
 *  * Created by TienND on 6/21/20 1:49 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:39 AM
 *
 */

package com.example.bpcrsadmin.repository.api;

public class ApiConfig {

    public static final String BASE_URL = "https://api.bpcrs.network/";

    public interface Api {
        String LOGIN_BY_GOOGLE = "/account/google/login";
        String GET_ACCOUNT = "/account";
        String GET_CAR = "/car/{id}";
        String GET_MY_CAR = "/car/owner/{id}";
    }
}
