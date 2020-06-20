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

import com.example.bpcrsadmin.model.Location;

public class SharedPreferenceUtils {
    private static final String SREF = "OLD_LOCATION";


    public static void saveCurrentLocation(Context context, Location location) {
        SharedPreferences prefs = context.getSharedPreferences(SREF, Context.MODE_PRIVATE);
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

    public static void retrieveData(Context context, String data) {

    }

}
