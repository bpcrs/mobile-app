/*
 * *
 *  * Created by TienND on 6/21/20 1:53 AM
 *  * Copyright (c) 2020 . All rights reserved.
 *  * Last modified 6/1/20 12:47 PM
 *
 */

package com.example.bpcrsadmin.screen.home.contract;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Booking;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.screen.home.contract.adapter.ContractAdapter;
import com.example.bpcrsadmin.screen.home.contract.adapter.ContractItemClickListener;
import com.example.bpcrsadmin.screen.login.LoginActivity;
import com.example.bpcrsadmin.utils.SharedPreferenceUtils;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Executor;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContractFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContractFragment extends Fragment implements View.OnClickListener, ContractItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button btSignOut;
    private CircleImageView ctvAvatar;
    private RecyclerView rvContract;
    private List<Booking> mBookList;

    public ContractFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
     * @return A new instance of fragment ContractFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContractFragment newInstance() {
        ContractFragment fragment = new ContractFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contract, container, false);
        btSignOut =  view.findViewById(R.id.button_sign_out);
        ctvAvatar = view.findViewById(R.id.civ_avatar);
        rvContract = view.findViewById(R.id.rv_contract);
        btSignOut.setOnClickListener(this);
        setAvatarUser();
        createBookList();
        bindBookingsToRecyclerView(mBookList);
        return view;
    }

    public void setAvatarUser() {
        String imgUrl = SharedPreferenceUtils.retrieveData(Objects.requireNonNull(getActivity()), getString(R.string.imageUrl));
        Glide.with(this).load(imgUrl).into(ctvAvatar);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_out:
                signOut();
                break;
        }
    }

    private void signOut() {
        GoogleSignInOptions gso = new GoogleSignInOptions.
                Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                build();

        GoogleSignInClient googleSignInClient=GoogleSignIn.getClient(Objects.requireNonNull(getActivity()),gso);
        googleSignInClient.revokeAccess()
                .addOnCompleteListener(getActivity(), new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.d("SIGN_OUT", "sign out successfully");
                        Intent intent = new Intent(getActivity(), LoginActivity.class);
                        startActivity(intent);
                    }
                });
    }

    public void createBookList() {
        mBookList = new ArrayList<>();
        Car car = Car.builder().id(4).name("abc").vin("xyz").price(20000).build();
        Booking book = Booking.builder().car(car).build();
        mBookList.add(book);
        mBookList.add(book);
    }

    public void bindBookingsToRecyclerView(List<Booking> bookingList) {
        ContractAdapter contractAdapter = new ContractAdapter(getContext(), bookingList, this);
        rvContract.setAdapter(contractAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvContract.setLayoutManager(layoutManager);
    }

    @Override
    public void onBookTapped(Booking booking) {

    }
}
