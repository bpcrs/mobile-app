/*
 * *
 *  * Created by TienND on 6/21/20 1:52 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/3/20 3:55 PM
 *
 */

package com.example.bpcrsadmin.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Account;
import com.example.bpcrsadmin.model.Location;

public class SharedPreferenceUtils {

    public static void saveJwtToken(Context context, String jwt) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.BPCRS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (jwt != null) {
            editor.putString(context.getString(R.string.jwt), jwt);
        }
        editor.apply();
    }

    public static void saveInfoUser(Context context, Account user) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.BPCRS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (user != null) {
            editor.putInt(context.getString(R.string.id), user.getId());
            editor.putString(context.getString(R.string.email), user.getEmail());
            editor.putString(context.getString(R.string.fullName), user.getFullName());
            editor.putString(context.getString(R.string.imageUrl), user.getImageUrl());
        }
        editor.apply();
    }

    public static void saveCurrentLocation(Context context, Location location) {
        //shared
        SharedPreferences prefs = context.getSharedPreferences(Constant.BPCRS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        if (null != location) {
            editor.putInt("car_id", location.getCarId());
            editor.putString("latitude", location.getLatitude());
            editor.putString("longitude", location.getLongitude());
            editor.putString("time", location.getTime());
        }

        editor.apply();
        editor.commit();
    }

    public static String retrieveData(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.BPCRS, Context.MODE_PRIVATE);
        return preferences.getString(key, null);
    }

    public static int retrieveDataInt(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constant.BPCRS, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

}
