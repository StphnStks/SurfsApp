package com.example.stephen.surfsapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpotsFragment extends Fragment implements View.OnClickListener {

    Button btnSpots;


    // position to hold onto position clicked on the Nav Drawer
    int position;

    // interface reference variable
    NavigationDrawerFragment.FragmentTransactions fragmentTransactions;

    public SpotsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("Fragment ", "onCreateView called");

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spots, container, false);


        btnSpots = (Button)view.findViewById(R.id.btn);
        btnSpots.setOnClickListener(this);


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // this returns a ref to main activity initializing to the interface property
        fragmentTransactions = (NavigationDrawerFragment.FragmentTransactions) getActivity();

        Bundle extras = getArguments();
        position = extras.getInt("position");
        Log.i("Bundle ", " value " + position);
    }

    @Override
    public void onClick(View v) {

        if(position == 0) {
            Log.i("Btn 0 ", "clicked " + position);

            ForecastFragment foreFrag = new ForecastFragment();
            fragmentTransactions.fragmentTransaction(foreFrag, position);
        }
        else if(position == 1) {
            Log.i("Btn 1 ", "clicked " + position);

            ChartsFragment chartsFrag = new ChartsFragment();
            fragmentTransactions.fragmentTransaction(chartsFrag, position);
        }
        else if(position == 2) {
            Log.i("Btn 2 ", "clicked " + position);

            TidesFragment tidesFrag = new TidesFragment();
            fragmentTransactions.fragmentTransaction(tidesFrag, position);
        }
    }
}