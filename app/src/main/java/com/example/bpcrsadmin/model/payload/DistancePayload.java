/*
 * *
 *  * Created by TienND on 8/17/20 1:39 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 1:39 PM
 *
 */

package com.example.bpcrsadmin.model.payload;

import com.example.bpcrsadmin.model.Distance;
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
public class DistancePayload implements Serializable {

    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private Distance data;
}
