/*
 * *
 *  * Created by TienND on 8/7/20 1:18 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/7/20 1:18 AM
 *
 */

package com.example.bpcrsadmin.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginPayload implements Serializable {
    @SerializedName("success")
    private String success;

    @SerializedName("message")
    private String message;

    @SerializedName("data")
    private String data;

    public LoginPayload(String success, String message, String data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
