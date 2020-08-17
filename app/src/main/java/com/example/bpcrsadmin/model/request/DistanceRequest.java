/*
 * *
 *  * Created by TienND on 8/17/20 1:50 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 1:50 PM
 *
 */

package com.example.bpcrsadmin.model.request;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistanceRequest  {

    @SerializedName("destination")
    private String destination;

    @SerializedName("location")
    private String location;
}
