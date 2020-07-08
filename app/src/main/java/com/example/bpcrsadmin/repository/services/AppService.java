/*
 * *
 *  * Created by TienND on 6/21/20 1:49 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:47 AM
 *
 */

package com.example.bpcrsadmin.repository.services;


import com.example.bpcrsadmin.model.Account;
import com.example.bpcrsadmin.repository.api.ApiConfig;

import retrofit2.Call;
import retrofit2.http.POST;

public interface AppService {
    @POST(ApiConfig.Api.GET_ACCOUNT)
    Call<Account> loginWithGoogle(Account account);
}
