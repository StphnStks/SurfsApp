package com.example.stephen.surfsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartsFragment extends Fragment {


    public ChartsFragment() {
        // Required empty public constructor
    }


    // Check out
    // LongRangeForecast Fragment contains code for connection and returning JSON


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Volley.newRequestQueue(getActivity());

        // Note !! Volley Singleton class working
        RequestQueue requestQueue = VolleySingleton.getVolleySingleton().getRequestQueue();


        // http://10.0.2.2:80

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://267c24a5.ngrok.com/surfs/api/v1.0/spots", new Response.Listener<String>() {

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




}
