/*
 * *
 *  * Created by TienND on 8/6/20 11:55 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/6/20 11:55 PM
 *
 */

package com.example.bpcrsadmin.screen.login;

public interface LoginView {
    void onSuccessLogin(String jwt);
    void onFailLogin();
}
