/*
 * *
 *  * Created by TienND on 8/11/20 12:05 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/11/20 12:05 AM
 *
 */

package com.example.bpcrsadmin.model;

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
