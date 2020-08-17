/*
 * *
 *  * Created by TienND on 8/13/20 3:21 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/13/20 3:21 PM
 *
 */

package com.example.bpcrsadmin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Booking implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("car")
    private Car car;

    @SerializedName("renter")
    private Account renter;

    @SerializedName("location")
    private String location;

    @SerializedName("status")
    private String status;

    @SerializedName("destination")
    private String destination;

    @SerializedName("toDate")
    private Date toDate;

    @SerializedName("fromDate")
    private Date fromDate;

    @SerializedName("totalPrice")
    private double totalPrice;
}
