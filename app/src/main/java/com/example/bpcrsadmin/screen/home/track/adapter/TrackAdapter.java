/*
 * *
 *  * Created by TienND on 8/4/20 2:32 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/4/20 2:32 AM
 *
 */

package com.example.bpcrsadmin.screen.home.track.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Car;

import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder>{

    private Context mContext;
    private List<Car> mCarList;
    private TrackItemClickListener trackItemClickListener;

    public TrackAdapter(Context mContext, List<Car> mCarList, TrackItemClickListener trackItemClickListener) {
        this.mContext = mContext;
        this.mCarList = mCarList;
        this.trackItemClickListener = trackItemClickListener;
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_track, parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mCarList.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> trackItemClickListener.onTrackTapped(mCarList.get(getAdapterPosition())));
        }
    }
}
