/*
 * *
 *  * Created by TienND on 6/21/20 1:54 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/21/20 1:51 AM
 *
 */

package com.example.bpcrsadmin.screen.home.car.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Car;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private Context mContext;
    private List<Car> mCarList;
    private CarItemClickListener mCarItemClickListener;

    public CarAdapter(Context context, List<Car> carList, CarItemClickListener carItemClickListener) {
        mContext = context;
        mCarList = carList;
        mCarItemClickListener = carItemClickListener;
    }

    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_car, parent, false);
        CarViewHolder viewHolder = new CarViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.tvCarName.setText(mCarList.get(position).getModel());
        holder.tvCarInfo.setText(mCarList.get(position).getYear() + " - " + mCarList.get(position).getCarNumber());
    }

    @Override
    public int getItemCount() {
        return mCarList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {

         TextView tvCarName;
         TextView tvCarInfo;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCarName = itemView.findViewById(R.id.tv_carName);
            tvCarInfo = itemView.findViewById(R.id.tv_carInfo);
            itemView.setOnClickListener(v -> mCarItemClickListener.onCarTapped(mCarList.get(getAdapterPosition())));
        }
    }
}
