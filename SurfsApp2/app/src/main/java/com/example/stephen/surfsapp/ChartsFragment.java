package com.example.stephen.surfsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartsFragment extends Fragment {


    public ChartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charts, container, false);
    }


/*

    // Check out

    // LongRangeForecast Fragment contains code for connection and returning JSON

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // VolleySingleton.getVolleySingleton().getRequestQueue();

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());


        //  http://bohemianfc.com/              // http://php.net/
                                                                        //        localhost:5000/
        // 127.0.0.1:5000/surfs/api/v1.0/favourite_spots

                                            // 192.168.1.2:80         // http://127.0.0.1:5000/   // http://10.0.2.2:80

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://5583511a.ngrok.com/", new Response.Listener<String>() {

            // once page is downloaded get response from onResponse method or ErrorListener if there's an error
            @Override
            public void onResponse(String response) {

                Toast.makeText(getActivity(), "Response " + response, Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Toast.makeText(getActivity(), "Error " + volleyError.getMessage(), Toast.LENGTH_LONG).show();

            }

        });

        requestQueue.add(stringRequest);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_charts, container, false);
    }

 */


}
