package com.example.stephen.surfsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */


public class SpotsFragment extends Fragment implements NavDrawerAdapter.NavDrawerListener {

    // hold onto position clicked on the Nav Drawer
    int pos;

    // interface reference variable
    NavigationDrawerFragment.FragmentTransactions fragmentTransactions;

    // references to instance objects, requesting a internet connection
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    // private ImageLoader imageLoader; // and loading an Image


    public static final String URL_SURFS_API_SPOTS="http://49605095.ngrok.com/surfs/api/v1.0/spots";

    private ArrayList<Spot> listSpots;

    private RecyclerView recyclerViewSpots;

    private SpotsAdapter spotsAdapter;


    public SpotsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volleySingleton = VolleySingleton.getVolleySingleton();
        requestQueue = volleySingleton.getRequestQueue();
    }


    private void jsonRequestGet() {

        // create a JSON request, includes GET request, URL and anonymous listeners for response and error response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_SURFS_API_SPOTS, (JSONObject)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonResponse) {

                listSpots = jsonResponseParse(jsonResponse);
                spotsAdapter.setListSpots(listSpots);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // add to the JSON request to Queue
        requestQueue.add(jsonObjectRequest);

    }


    // parse JSONArray to a list, this is the response from API call
    private ArrayList<Spot> jsonResponseParse(JSONObject jsonResponse) {

        // initialize empty arrayList, if any exceptions returns empty ArrayList instead of null
        ArrayList<Spot> listSpots = new ArrayList<Spot>();

        if(jsonResponse == null || jsonResponse.length() == 0)
            return null;

        try {

            JSONArray spotsArray = jsonResponse.getJSONArray("spots");

//            Toast.makeText(getActivity(), "JSON Response Parse " + spotsArray, Toast.LENGTH_LONG).show();
            Log.e("JSON Array ", "" + spotsArray);

            // adds the JSONArray contents to a
            for(int i=0; i < spotsArray.length(); i++) {

                String spotName = spotsArray.getString(i);

                // create an instance of spot & set spot name
                Spot spot = new Spot();
                spot.setSpotName(spotName);

                listSpots.add(spot);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listSpots;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_spots, container, false);

        recyclerViewSpots = (RecyclerView) view.findViewById(R.id.spotsList);

        // sets the appearance of the recycler view
        recyclerViewSpots.setLayoutManager(new LinearLayoutManager(getActivity()));

        // initialising the adapter calling getActivity to return the context and set adapter on recycler view
        spotsAdapter = new SpotsAdapter(getActivity());
        recyclerViewSpots.setAdapter(spotsAdapter);

        spotsAdapter.setNavDrawerListener(this);

        jsonRequestGet();

        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        // this returns a ref to main activity initializing to the interface property
        fragmentTransactions = (NavigationDrawerFragment.FragmentTransactions) getActivity();

        // bundle accesses the position clicked in Nav Drawer
        Bundle bundle = getArguments();
        pos = bundle.getInt("pos");
        Log.i("Bundle ", " value " + pos);
    }


    @Override
    public void itemViewClicked(View view, int position) {

        if(pos == 0) {
            Log.i("Btn 0 ", "clicked " + pos);

            ForecastFragment foreFrag = new ForecastFragment();
            fragmentTransactions.fragmentTransaction(foreFrag, pos);
        }


    /*
        else if(pos == 1) {
            Log.i("Btn 1 ", "clicked " + pos);

            ChartsFragment chartsFrag = new ChartsFragment();
            fragmentTransactions.fragmentTransaction(chartsFrag, pos);
        }
    */


        else if(pos == 4) {
            Log.i("Btn 2 ", "clicked " + pos);

            TidesFragment tidesFrag = new TidesFragment();
            fragmentTransactions.fragmentTransaction(tidesFrag, pos);
        }
    }
}



// From Spots Activity use this for Charts Fragment to implement Page Viewer

// Also see Charts and MyAdapterCharts from FYP project and Charts and Spots.xml files for ViewPager
///////////////////////////////////////////////////////////////////////////////////

/*
package com.surfsapp.stephen.surfsapp;

        import android.content.Context;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentPagerAdapter;
        import android.support.v4.view.ViewPager;
        import android.support.v7.app.ActionBarActivity;
        import android.util.Log;
        import android.view.Menu;
        import android.view.MenuItem;

        import com.android.volley.toolbox.Volley;



//public class Spots extends ActionBarActivity implements MyListFragment.OnFragmentInteractionListener, ChartsFragment.OnFragmentInteractionListener, LastSpotsFragment.OnFragmentInteractionListener {

    ViewPager viewPager = null;


    //instantiate a db object
    // TaskListDatabase task = new TaskListDatabase(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spots);

        viewPager = (ViewPager) findViewById(R.id.pager_switch_fragments);
        FragmentManager fragMan = getSupportFragmentManager();

        viewPager.setAdapter(new MyAdapter(fragMan));


        /*

        //try catch statement to open database
        try {
            task.open();
            Log.i("Db", "Opened");
        }
        catch (SQLException sqlExc) {

        }

        */

/*
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.spots_actionbar, menu);

//        MenuInflater spotsActionBar = getMenuInflater();
//        spotsActionBar.inflate(R.menu.menu_spots, menu);

        return (super.onCreateOptionsMenu(menu));

//        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    // this method comes from the interface inside MyListFragment in FYP project
    @Override
    public void onFragmentInteraction(Uri uri) {

    }



*/