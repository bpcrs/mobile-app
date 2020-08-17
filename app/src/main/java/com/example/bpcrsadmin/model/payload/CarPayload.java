/*
 * *
 *  * Created by TienND on 8/13/20 3:34 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/10/20 11:57 PM
 *
 */

package com.example.bpcrsadmin.model.payload;

import com.example.bpcrsadmin.model.Car;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CarPayload implements Serializable {

    @SerializedName("success")
    private String success;

    @SerializedName("data")
    private Car data;

    public CarPayload(String success, Car data) {
        this.success = success;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Car getData() {
        return data;
    }

    public void setData(Car data) {
        this.data = data;
    }
}
