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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bpcrsadmin.BuildConfig;
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
        if (position % 2 == 1) {
            holder.mLayout.setBackgroundResource(R.drawable.background_silver_radius);
            holder.mButtonTrack.setBackgroundResource(R.drawable.bg_button_track_silver);
        }
    }

    @Override
    public int getItemCount() {
        return mCarList.size();
    }

    public class TrackViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout mLayout;
        private ConstraintLayout mButtonTrack;

        public TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            mLayout = itemView.findViewById(R.id.item_rv_track);
            mButtonTrack = itemView.findViewById(R.id.button_track);
            itemView.setOnClickListener(v -> trackItemClickListener.onTrackTapped(mCarList.get(getAdapterPosition())));
        }
    }


}
