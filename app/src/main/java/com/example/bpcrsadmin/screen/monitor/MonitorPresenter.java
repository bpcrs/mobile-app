/*
 * *
 *  * Created by TienND on 8/17/20 3:52 PM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/17/20 3:52 PM
 *
 */

package com.example.bpcrsadmin.screen.monitor;

import android.content.Context;

import com.example.bpcrsadmin.helper.RepoHelper;
import com.example.bpcrsadmin.model.Distance;
import com.example.bpcrsadmin.model.request.DistanceRequest;
import com.example.bpcrsadmin.repository.callback.CallbackData;

public class MonitorPresenter {
    private MonitorView mMonitorView;
    private Context mContext;
    private RepoHelper mReoHelper;

    public MonitorPresenter(MonitorView mMonitorView, Context mContext) {
        this.mMonitorView = mMonitorView;
        this.mContext = mContext;
        mReoHelper = new RepoHelper(mContext);
    }

    public void getDistanceBetweenTwoLocation(DistanceRequest request) {
        mReoHelper.getDistanceBetweenTwoLocation(request, new CallbackData<Distance>() {
            @Override
            public void onSuccess(Distance distance) {
                mMonitorView.onSuccessGetDistance(distance);
            }

            @Override
            public void onFail(String message) {

            }
        });
    }
}
