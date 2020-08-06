/*
 * *
 *  * Created by TienND on 8/6/20 11:56 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/6/20 11:56 PM
 *
 */

package com.example.bpcrsadmin.screen.login;

import android.content.Context;

import com.example.bpcrsadmin.helper.RepoHelper;
import com.example.bpcrsadmin.model.LoginPayload;
import com.example.bpcrsadmin.repository.callback.CallbackData;

public class LoginPresenter {
    private LoginView mLoginView;
    private Context context;
    private RepoHelper mRepoHelper;

    public LoginPresenter(LoginView mLoginView, Context context) {
        this.mLoginView = mLoginView;
        this.context = context;
        mRepoHelper = new RepoHelper(context);
    }

    public void loginWithGoogle(String token) {
        mRepoHelper.loginWithGoogle(token, new CallbackData<LoginPayload>() {

            @Override
            public void onSuccess(LoginPayload loginPayload) {

            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
