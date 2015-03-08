package com.example.stephen.surfsapp;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class SpotsFragment extends Fragment implements View.OnClickListener { //implements Parcelable {

    Button btnSpots;
    int countClicks = 0;


    // position to hold onto position clicked on the Nav Drawer
    int position;


    // interface reference variable
    NavigationDrawerFragment.FragmentTransactions fragmentTransactions;

    public SpotsFragment() {
        // Required empty public constructor

//        Bundle args = new Bundle();
//        setArguments(args);

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


//        Bundle arg = getArguments();
//        a = arg.getString("Drawer Layout");
//        b = arg.getString("Nav Drawer View");

    }


    @Override
    public void onClick(View v) {


//        NavigationDrawerFragment a = new NavigationDrawerFragment();


        Log.i("Btn ", "onClick " + position);

//        countClicks ++;

        if(position == 0) {
            Log.i("Btn 0 ", "clicked " + position);


            ChartsFragment chartsFrag = new ChartsFragment();
            fragmentTransactions.fragmentTransaction(chartsFrag, position);


//            a.fragmentTransaction(chartsFrag);
        }
        else if(position == 1) {
            Log.i("Btn 1 ", "clicked " + position);

//            ChartsFragment chartsFrag = new ChartsFragment();
//            fragmentTransactions.fragmentTransaction(chartsFrag);


            TidesFragment tidesFrag = new TidesFragment();
            fragmentTransactions.fragmentTransaction(tidesFrag, position);


        }
        else if(position == 2) {
            Log.i("Btn 2 ", "clicked " + position);

            TidesFragment tidesFrag = new TidesFragment();
            fragmentTransactions.fragmentTransaction(tidesFrag, position);


//            a.fragmentTransaction(tidesFrag);
        }
    }



    /*
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeInt(mData);
    }

    public static final Creator<SpotsFragment> CREATOR = new Creator<SpotsFragment>() {

        @Override
        public SpotsFragment createFromParcel(Parcel source) {
            return new SpotsFragment(source);
        }

        @Override
        public SpotsFragment[] newArray(int size) {
            return new SpotsFragment[0];
        }
    };
*/


}
