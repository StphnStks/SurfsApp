package com.example.stephen.surfsapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartsWsFragment extends Fragment implements View.OnClickListener {


    // surfs/api/v1.0/        marine_forecast_data                    get_images_sw
    private static final String URL_SURFS_API_CHARTS = "http://49605095.ngrok.com/2";


    private RequestQueue requestQueue;

    ImageView imageView;
    ChartImagesSw imgSw;
    String [] images;
    Button btnNext;
    Button btnPrev;
    private int counter = 0;


    public ChartsWsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialise instances
        VolleySingleton volleySingleton = VolleySingleton.getVolleySingleton();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_charts_ws, container, false);


        imageView = (ImageView) view.findViewById(R.id.imageChart);

        btnNext = (Button) view.findViewById(R.id.btn_next);
        btnPrev = (Button) view.findViewById(R.id.btn_previous);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);

        requestQueue = VolleySingleton.getVolleySingleton().getRequestQueue();

        jsonRequestGet();


        return view;
    }


    private void jsonRequestGet() {

        // create a JSON request, includes GET request, URL and anonymous listeners for response and error response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_SURFS_API_CHARTS, (JSONObject)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonResponse) {

                jsonResponseParse(jsonResponse);

                Log.i("Returned ", "list");
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
    private void jsonResponseParse(JSONObject jsonResponse) {

        if(jsonResponse == null || jsonResponse.length() == 0)
            return;
        try {
            Log.e("Inside try ", "statement");

            JSONArray jsonArray = (JSONArray) jsonResponse.get("urls_ws_dir");

            images = new String[jsonArray.length()];

            // extracts the JSONArray contents
            for(int i=0; i < jsonArray.length(); i++) {

                images[i] = jsonArray.getString(i);
                imgSw = new ChartImagesSw();
                imgSw.setSwImg(images[i]);

                Log.i("values", " get " + imgSw.getSwImg(images[i]));
            }
            Log.i("Array values", " Img 1 "  + images + " Img 2 " + images);

//            Log.i("value", " from getter " + imgSw.getSwImg());

            Picasso.with(getActivity()).load(imgSw.getSwImg(images[0])).into(imageView);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        if(btnNext == v && counter <=6) {
            Picasso.with(getActivity()).load(imgSw.getSwImg(images[++counter])).into(imageView);

//                Toast.makeText(getActivity(), "Next clicked", Toast.LENGTH_LONG).show();
        }
        if(btnPrev == v && counter >= 1) {
            Picasso.with(getActivity()).load(imgSw.getSwImg(images[--counter])).into(imageView);

//                Toast.makeText(getActivity(), "Previous clicked", Toast.LENGTH_LONG).show();
        }
        else
            Log.i("End of ", "array");

    }

}
