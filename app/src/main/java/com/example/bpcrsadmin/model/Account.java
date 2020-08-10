/*
 * *
 *  * Created by TienND on 7/9/20 1:21 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/9/20 1:18 AM
 *
 */

package com.example.bpcrsadmin.model;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account implements Serializable {

    @SerializedName("id")
    private int id;

    @SerializedName("email")
    private String email;

    @SerializedName("fullName")
    private String fullName;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("role")
    private String role;

}
