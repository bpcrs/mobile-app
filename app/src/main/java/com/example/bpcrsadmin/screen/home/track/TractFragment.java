package com.example.bpcrsadmin.screen.home.track;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.screen.monitor.MonitorActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TractFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TractFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private CardView cvTrack;


    public TractFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static TractFragment newInstance() {
        TractFragment fragment = new TractFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tract, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        cvTrack = getActivity().findViewById(R.id.cv_track);
        cvTrack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MonitorActivity.class);
                startActivity(intent);
            }
        });
    }
}
