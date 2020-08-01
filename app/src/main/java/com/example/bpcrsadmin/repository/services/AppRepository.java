/*
 * *
 *  * Created by TienND on 6/21/20 1:51 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:51 AM
 *
 */

package com.example.bpcrsadmin.repository.services;

import com.example.bpcrsadmin.model.Account;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.repository.callback.CallbackData;

public interface AppRepository {
    void loginWithGoogle(CallbackData<Account> callbackData, String token);
    void  getCarById(int carId, CallbackData<Car> callbackData);
}
