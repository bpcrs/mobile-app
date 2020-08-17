/*
 * *
 *  * Created by TienND on 8/17/20 1:41 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 1:41 PM
 *
 */

package com.example.bpcrsadmin.model;

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
public class Distance implements Serializable {

    @SerializedName("distance")
    private String distance;
}
