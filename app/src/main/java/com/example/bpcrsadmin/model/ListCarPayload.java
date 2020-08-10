/*
 * *
 *  * Created by TienND on 8/10/20 11:04 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/10/20 11:04 PM
 *
 */

package com.example.bpcrsadmin.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ListCarPayload implements Serializable {

    @SerializedName("success")
    private String success;

    @SerializedName("data")
    @Expose
    private CarsPayload data;

}
