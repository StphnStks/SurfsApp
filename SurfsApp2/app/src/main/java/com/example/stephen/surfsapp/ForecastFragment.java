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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ForecastFragment extends Fragment {

    // references to instance objects, requesting a internet connection
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;


    // private ImageLoader imageLoader; // and loading an Image


    private static final double CONVERT_KNOT_TO_MPH = 1.15077945;
    private static final double CONVERT_MTR_TO_FT = 3.2808399;


    private static final String GUST = "Gust";
    private static final String WAVE_DIR = "MeanWaveDirection";
    private static final String WAVE_HEIGHT = "WaveHeight";
    private static final String WAVE_PERIOD = "WavePeriod";
    private static final String WIND_DIR = "WindDirection";
    private static final String WIND_SPEED = "WindSpeed";
    private static final String STATION_ID = "station_id";
    private static final String DATE_TIME = "time";


                  // surfs/api/v1.0/        marine_forecast_data
    private static final String URL_SURFS_API_FORECAST = "http://49605095.ngrok.com/test";

    private ArrayList<Forecast> listForecast;

    private RecyclerView recyclerViewForecast;

    private ForecastAdapter forecastAdapter;


    public ForecastFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        volleySingleton = VolleySingleton.getVolleySingleton();
        requestQueue = volleySingleton.getRequestQueue();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_forecast, container, false);

        // get hold of the recycler view and set the appearance
        recyclerViewForecast = (RecyclerView) view.findViewById(R.id.forecastList);
        recyclerViewForecast.setLayoutManager(new LinearLayoutManager(getActivity()));

        // initialising the adapter calling getActivity to return the context and set adapter on recycler view
        forecastAdapter = new ForecastAdapter(getActivity());
        recyclerViewForecast.setAdapter(forecastAdapter);


//        spotsAdapter.setNavDrawerListener(this);


        jsonRequestGet();

        return view;
    }


    private void jsonRequestGet() {

        // create a JSON request, includes GET request, URL and anonymous listeners for response and error response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_SURFS_API_FORECAST, (JSONObject)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonResponse) {

                listForecast = jsonResponseParse(jsonResponse);

                forecastAdapter.setListForecast(listForecast);

//                spotsAdapter.setListSpots(listForecast);
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
    private ArrayList<Forecast> jsonResponseParse(JSONObject jsonResponse) {

        // initialize empty arrayList, if any exceptions returns empty ArrayList instead of null
        ArrayList<Forecast> listForecast = new ArrayList<>();

        if(jsonResponse == null || jsonResponse.length() == 0)
            return null;

        try {

            JSONObject jsonObject = jsonResponse.getJSONObject("data");

//            Toast.makeText(getActivity(), "JSON Response Parse " + jsonObject, Toast.LENGTH_LONG).show();
            Log.e("JSON Object ", "" + jsonObject);

            // extracts the JSONObject contents
            for(int i=0; i < 20; i++) {              // jsonObject.length()

                String stationId = jsonObject.getString(STATION_ID);
                String dateTime = jsonObject.getString(DATE_TIME);

                float waveDir = (float) jsonObject.getDouble(WAVE_DIR);

//                String waveDirTxt = ((waveDir / 22.5) + .5);

                float waveHeight = (float) jsonObject.getDouble(WAVE_HEIGHT);
                float waveHeightFt = (float) (waveHeight * CONVERT_MTR_TO_FT);
                float wavePeriod = (float) jsonObject.getDouble(WAVE_PERIOD);

                float gust = (float)jsonObject.getDouble(GUST);
                float gustMph = (float) (gust * CONVERT_KNOT_TO_MPH);

                float windDir = (float) jsonObject.getDouble(WIND_DIR);
                float windSpeed = (float) jsonObject.getDouble(WIND_SPEED);
                float windSpeedMph = (float) (windSpeed * CONVERT_KNOT_TO_MPH);

                // + gust + " knots " + waveHeight + " Mtr's " + windSpeed + " Knots "

                Log.i("Forecast values", "  "  + gustMph + " Mph " + waveDir + " Degrees " + waveHeightFt + " Ft " + wavePeriod + " Secs " + windDir + " Degrees " + windSpeedMph + " Mph " + stationId + " " + dateTime);


                Forecast forecast = new Forecast();


                forecast.setDateTime(dateTime);


                forecast.setWaveDir(waveDir);
                forecast.setWaveHeightFt(waveHeightFt);
                forecast.setWavePeriod(wavePeriod);
                forecast.setGustMph(gustMph);
                forecast.setWindDir(windDir);
                forecast.setWindSpeedMph(windSpeedMph);

                listForecast.add(forecast);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listForecast;
    }

}
