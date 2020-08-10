/*
 * *
 *  * Created by TienND on 8/2/20 12:41 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/2/20 12:41 AM
 *
 */

package com.example.bpcrsadmin.screen.home;

import com.example.bpcrsadmin.model.Car;

import java.util.List;

public interface HomeView {
    void onSuccessGetCar(Car car);
    void  onSuccessGetCars(List<Car> cars);
    void onFailGetCar();
}
