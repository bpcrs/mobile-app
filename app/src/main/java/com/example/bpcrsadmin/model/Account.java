/*
 * *
 *  * Created by TienND on 7/9/20 1:21 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 7/9/20 1:18 AM
 *
 */

package com.example.bpcrsadmin.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private String email;
    private String imageUrl;
    private String fullName;
}
