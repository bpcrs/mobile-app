/*
 * *
 *  * Created by TienND on 8/13/20 3:34 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/11/20 12:31 AM
 *
 */

package com.example.bpcrsadmin.model.payload;

import com.example.bpcrsadmin.model.Car;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CarsPayload implements Serializable {

    @SerializedName("count")
    private int count;

    @SerializedName("data")
    private List<Car> car;
}
