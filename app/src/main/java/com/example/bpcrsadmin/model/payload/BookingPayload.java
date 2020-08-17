/*
 * *
 *  * Created by TienND on 8/13/20 3:36 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/13/20 3:36 PM
 *
 */

package com.example.bpcrsadmin.model.payload;

import com.example.bpcrsadmin.model.Booking;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BookingPayload implements Serializable {
    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private Booking data;
}
