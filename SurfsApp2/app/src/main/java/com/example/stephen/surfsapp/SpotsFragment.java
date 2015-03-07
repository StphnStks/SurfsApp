package com.example.stephen.surfsapp;


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


    DrawerLayout nav;
//    View a;


//    NavigationDrawerFragment navigationDrawerFragment;
//    DrawerLayout drawerLayout;
//    View view;

//    String a ,b;

//    int mData;

//    DrawerLayout drawerLayout;
//    View navDrawerView;

    public SpotsFragment() {
        // Required empty public constructor


//        mData = source.readInt();
//        this.drawerLayout = drawerLayout;
//        this.navDrawerView = navDrawerView;
//        Bundle args = new Bundle();
//        setArguments(args);

/*
        drawerLayout = navigationDrawerFragment.getNavDrawer();
        view = navigationDrawerFragment.getNavDrawerView();

        this.drawerLayout = drawerLayout;
        view = view;
*/

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("Fragment ", "onCreateView called");

        View view = inflater.inflate(R.layout.fragment_spots, container, false);


        btnSpots = (Button)view.findViewById(R.id.btn);
        btnSpots.setOnClickListener(this);


//        nav = (DrawerLayout) getActivity().findViewById(R.id.frag_nav_drawer);

//        View a = getActivity().findViewById(R.id.nav_drawer_layout);

        return view;

        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_spots, container, false);
    }


/*
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



//        Bundle arg = getArguments();
//        a = arg.getString("Drawer Layout");
//        b = arg.getString("Nav Drawer View");


    }
*/


    @Override
    public void onClick(View v) {

        countClicks ++;
        Log.i("Btn ", "clicked " + countClicks);

//        nav.openDrawer();



//        drawerLayout.openDrawer(view);
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
