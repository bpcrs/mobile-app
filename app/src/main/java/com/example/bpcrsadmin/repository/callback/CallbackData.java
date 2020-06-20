/*
 * *
 *  * Created by TienND on 6/21/20 1:51 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/3/20 3:14 PM
 *
 */

package com.example.bpcrsadmin.repository.callback;

public interface CallbackData<T> {
    void onSuccess(T t);
    void onFail(String message);
}
