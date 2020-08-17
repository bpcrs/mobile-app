/*
 * *
 *  * Created by TienND on 8/13/20 1:36 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 8/13/20 1:36 AM
 *
 */

package com.example.bpcrsadmin.screen.home.contract.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Booking;

import java.util.List;

public class ContractAdapter extends RecyclerView.Adapter<ContractAdapter.ContractViewHolder> {

    private Context mContext;
    private List<Booking> mBookList;
    private ContractItemClickListener mContractItemClickListener;

    public ContractAdapter(Context mContext, List<Booking> bookingList, ContractItemClickListener mContractItemClickListener) {
        this.mContext = mContext;
        this.mBookList = bookingList;
        this.mContractItemClickListener = mContractItemClickListener;
    }

    @NonNull
    @Override
    public ContractViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_contract, parent, false);
        return new ContractViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContractViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    public class ContractViewHolder extends RecyclerView.ViewHolder {

        public ContractViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> mContractItemClickListener.onBookTapped(mBookList.get(getAdapterPosition())));
        }
    }
}
