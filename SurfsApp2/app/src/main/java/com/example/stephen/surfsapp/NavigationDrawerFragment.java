package com.example.stephen.surfsapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

// implements an interface inside the NavDrawerAdapter to listen and handle clicks on views inside the Navigation drawer
public class NavigationDrawerFragment extends Fragment implements NavDrawerAdapter.NavDrawerListener {  // AdapterViewCompat.OnItemClickListener{

    ActionBarDrawerToggle actionBarDrawerToggle;
    DrawerLayout drawerLayout;
    View navDrawerView;

    Boolean userAwareNavDrawer, existsNavDrawer;

    public static final String PREFERENCE_FILE_NAME ="testpref";
    public static final String KEY_USER_AWARE_DRAWER="user_learner_drawer";

    RecyclerView recyclerViewNavDrawer;

    NavDrawerAdapter navDrawerAdapter;

    Button btnSpots;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // checks whether the user is aware of drawer, by checking if it the value of the key can be retrieved from preferences
        userAwareNavDrawer = Boolean.valueOf(readFromPreferences(getActivity(), KEY_USER_AWARE_DRAWER, "false"));

        // checks if the nav drawer is starting for the first time or not
        if(savedInstanceState != null) {

            existsNavDrawer = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for the Navigation Drawer fragment
        View navDrawerLayout = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        // gets hold of recycler view id from xml
        recyclerViewNavDrawer = (RecyclerView) navDrawerLayout.findViewById(R.id.nav_drawer_list);

        // creates instance of the adapter, this is set to the recycler view
        navDrawerAdapter = new NavDrawerAdapter(getActivity(), getNavDrawerTitlesIcons());
        recyclerViewNavDrawer.setAdapter(navDrawerAdapter);

        // sets the appearance of the recycler view
        recyclerViewNavDrawer.setLayoutManager(new LinearLayoutManager(getActivity()));

        // calls method inside adapter class passing ref to instance of navDrawerAdapter
        navDrawerAdapter.setNavDrawerListener(this);


        return navDrawerLayout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



//        Parcel source = null;


        // spots fragment added to fragment container when activity is created
        SpotsFragment spotsFrag = new SpotsFragment();


//        Bundle bundle = new Bundle();
//        bundle.clone();
//        spotsFrag.setArguments(bundle);
//        bundle.putString("Drawer layout", drawerLayout.toString());
//        bundle.putString("Nav Drawer View", navDrawerView.toString());



        fragmentTransaction(spotsFrag);

//        drawerLayout.openDrawer(navDrawerView);
    }

    public static List<NavDrawerMenu> getNavDrawerTitlesIcons() {

        List<NavDrawerMenu> titleIconRows = new ArrayList<>();
        int[] icons = {R.drawable.ic_search, R.drawable.ic_search, R.drawable.ic_search, R.drawable.ic_search};
        String [] titles = {"Forecast", "Charts", "Tides", "Favourites"};

        for(int i=0; i < icons.length && i < titles.length; i++) {

            NavDrawerMenu titleIconRow = new NavDrawerMenu();
            titleIconRow.menuTitle = titles[i];
            titleIconRow.menuIcon = icons[i%icons.length];
            titleIconRows.add(titleIconRow);

        }
        return  titleIconRows;
    }

    // configures the functionality of the Navigation drawer and the toolbar working together
    public void configure(DrawerLayout drawerLayout, final Toolbar toolbar, int fragNavDrawerId) {

        this.drawerLayout = drawerLayout;
        navDrawerView = getActivity().findViewById(fragNavDrawerId);

        // monitors toggling states (Open, Close) of the Navigation drawer
        actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.close_drawer, R.string.open_drawer) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                // if user has not seen drawer, set to true and write to preferences file
                if(!userAwareNavDrawer) {
                    userAwareNavDrawer = true;
                    writeToPreferences(getActivity(), KEY_USER_AWARE_DRAWER, userAwareNavDrawer.toString());
                }
                getActivity().invalidateOptionsMenu(); // makes activity redraw the toolbar
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu(); // makes activity redraw the toolbar
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);

                // controls the alpha value setting visibility of toolbar, when nav drawer slides out
                if(slideOffset < 0.5)
                    toolbar.setAlpha(1 - slideOffset);
            }
        };

        // if the user hasn't seen the drawer and it's doesn't exist then open the drawer
        // (Note &&) requires to be left with no space to work on API 16 - Jellybean
        if(!userAwareNavDrawer && !existsNavDrawer) {

            drawerLayout.openDrawer(navDrawerView);
        }

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        // synchronises the navigation drawer with it's toolbar indicator
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });
    }

    public static void writeToPreferences(Context cont, String nameOfPref, String valueOfPref) {

        SharedPreferences sharedPreferences = cont.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nameOfPref, valueOfPref);
        editor.apply();
    }

    public static String readFromPreferences(Context cont, String nameOfPref, String defaultValue) {

        SharedPreferences sharedPreferences = cont.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(nameOfPref, defaultValue);
    }

    // method handles click events inside the Navigation Drawer
    @Override
    public void itemViewClicked(View view, int position) {

//        int pos = position;
//        SpotsFragment spotsFrag = new SpotsFragment();
//        fragmentTransaction(spotsFrag);

        if(position  == 0) {
            Log.d(" Item clicked at", " position " + position);
            drawerLayout.closeDrawer(navDrawerView);

            Intent intent = new Intent(getActivity(), SubActivity.class);
            startActivity(intent);
        }
        else if(position == 1) {
            Log.d(" Item clicked at", " position " + position);
            drawerLayout.closeDrawer(navDrawerView);

            ChartsFragment chartsFrag = new ChartsFragment();
            fragmentTransaction(chartsFrag);
        }
        else if(position == 2) {
            Log.d(" Item clicked at", " position " + position);
            drawerLayout.closeDrawer(navDrawerView);

            TidesFragment tidesFrag = new TidesFragment();
            fragmentTransaction(tidesFrag);
        }
        else if(position == 3) {
            Log.d(" Item clicked at", " position " + position);
            drawerLayout.closeDrawer(navDrawerView);

//            SpotsFragment spotsFrag = new SpotsFragment();
//            fragmentTransaction(spotsFrag);
        }
    }

    // transaction replaces the container in main activity xml with supplied fragment
    public void fragmentTransaction(Fragment frag) {

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, frag);
        transaction.addToBackStack(null);
        transaction.commit();
    }


/*
    public DrawerLayout getNavDrawer() {

        return this.drawerLayout;
    }

    public View getNavDrawerView() {

        return this.navDrawerView;
    }
*/
    
//    public interface NavDrawerFrag {
//
//        public void sendNavDrawer(DrawerLayout drawerLayout, View navDrawerView);
//    }




/*
    @Override
    public void onItemClick(AdapterViewCompat<?> adapterViewCompat, View view, int i, long l) {

    }
*/


}