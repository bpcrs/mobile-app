package com.example.bpcrsadmin.screen.home.car;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bpcrsadmin.R;
import com.example.bpcrsadmin.model.Car;
import com.example.bpcrsadmin.screen.detail.DetailFragment;
import com.example.bpcrsadmin.screen.home.car.adapter.CarAdapter;
import com.example.bpcrsadmin.screen.home.car.adapter.CarItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarFragment extends Fragment implements CarItemClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Car> mCarList;
    private RecyclerView rvCar;
    private RecyclerView.Adapter carAdapter;
    private RecyclerView.LayoutManager layoutManager;


    public CarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarFragment newInstance(String param1, String param2) {
        CarFragment fragment = new CarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
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

    public void bindCarsToRecyclerView(List<Car> carList) {
        carAdapter = new CarAdapter(getContext(), carList, this);
        rvCar.setAdapter(carAdapter);
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvCar.setLayoutManager(layoutManager);
    }

    public void createCarList() {
        mCarList = new ArrayList<>();
        mCarList.add(new Car("Lamborghini", "2019", "SS 66 AA 77"));
        mCarList.add(new Car("Ferrari", "2018", "55 BB AA 11"));
        mCarList.add(new Car("Tesla", "2019", "RR 44 DD 77"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_car, container, false);



        return rootView;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvCar = view.findViewById(R.id.rv_cars);

        createCarList();
        bindCarsToRecyclerView(mCarList);
    }

    @Override
    public void onCarTapped(Car car) {
        Fragment detail = DetailFragment.newInstance(car.getModel(), car.getCarNumber());
        //add the fragment to activity
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, detail);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();

    }
}
