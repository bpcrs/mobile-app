/*
 * *
 *  * Created by TienND on 8/13/20 3:34 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/11/20 12:30 AM
 *
 */

package com.example.bpcrsadmin.model.payload;

import com.example.bpcrsadmin.model.payload.CarsPayload;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

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
