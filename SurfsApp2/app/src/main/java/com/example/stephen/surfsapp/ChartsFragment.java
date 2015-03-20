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
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ChartsFragment extends Fragment implements View.OnClickListener {

    // surfs/api/v1.0/        marine_forecast_data                    get_images_sw
    private static final String URL_SURFS_API_CHARTS = "http://49605095.ngrok.com/";


    private ImageLoader imageLoader;


    // references to instance objects, requesting a internet connection
    private VolleySingleton volleySingleton;
    private RequestQueue requestQueue;

    ImageView imageView;
    ArrayList<String> listChartImages = new ArrayList<>();


    String image;


    ChartImagesSw imgSw;
    String [] images;

    Button btnNext;
    Button btnPrev;
    private int counter = 0;


//    private ViewPager viewPager;
//    private SlidingTabLayout slidingTabLayout;


    public ChartsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialise instances
        volleySingleton = VolleySingleton.getVolleySingleton();


//        imageLoader = volleySingleton.getImageLoader();
    }


    // Check out
    // LongRangeForecast Fragment contains code for connection and returning JSON


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_charts, container, false);

        imageView = (ImageView) view.findViewById(R.id.imageChart);

        btnNext = (Button) view.findViewById(R.id.btn_next);
        btnPrev = (Button) view.findViewById(R.id.btn_previous);
        btnNext.setOnClickListener(this);
        btnPrev.setOnClickListener(this);

        requestQueue = VolleySingleton.getVolleySingleton().getRequestQueue();


    /*
        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        viewPager.setAdapter(new MyPagerAdapter(getFragmentManager()));

        slidingTabLayout = (SlidingTabLayout) view.findViewById(R.id.fixed_tabs);
        slidingTabLayout.setViewPager(viewPager);
    */


        jsonRequestGet();


        // http://10.0.2.2:80

/*
        imageLoader.get(URL_SURFS_API_CHARTS, new ImageLoader.ImageListener() {
            @Override
            public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {

//                imageView.setImageURI(Uri.parse(imageContainer.getRequestUrl()));

            /*
                try {
                    URL url = new URL(URL_SURFS_API_CHARTS);
                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    imageView.setImageBitmap(bmp);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                Log.i("Got ", "image " + URL_SURFS_API_CHARTS);
                @Override
                public void onErrorResponse(VolleyError volleyError) {

                    Log.i("Error ", " no image");
                }
            });
        */

        return view;
    }

    private void jsonRequestGet() {

        // create a JSON request, includes GET request, URL and anonymous listeners for response and error response
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_SURFS_API_CHARTS, (JSONObject)null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject jsonResponse) {

                jsonResponseParse(jsonResponse);

                Log.i("Returned ", "list");


//                listForecast = jsonResponseParse(jsonResponse);
//                forecastAdapter.setListForecast(listForecast);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });

        // add to the JSON request to Queue
        requestQueue.add(jsonObjectRequest);

//        return image;
    }


    // parse JSONArray to a list, this is the response from API call
    private void jsonResponseParse(JSONObject jsonResponse) {

        // initialize empty arrayList, if any exceptions returns empty ArrayList instead of null
//        ArrayList<String> listChartImages = new ArrayList<>();


        if(jsonResponse == null || jsonResponse.length() == 0)

            return;
        try {
            Log.e("Inside try ", "statement");

//            JSONObject jsonObject = jsonResponse.getJSONObject("urls");

            JSONArray jsonArray = (JSONArray) jsonResponse.get("urls");

            images = new String[jsonArray.length()];

//            ChartImagesSw imgSw = null;

            // extracts the JSONArray contents
            for(int i=0; i < jsonArray.length(); i++) {      // jsonObject.length()

                images[i] = jsonArray.getString(i);

//                listChartImages.add(images);

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

//        return images[0];

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


/* Code for pager Adapter for fixed tabs for Different charts

    class MyPagerAdapter extends FragmentPagerAdapter {

        Fragment newFrag = null;
        String [] fixedTabs;

        public MyPagerAdapter(FragmentManager fragMan) {
            super(fragMan);

            fixedTabs = getResources().getStringArray(R.array.fixes_tabs);
        }

        @Override
        public Fragment getItem(int pos) {

            Log.d("Ste", "get item called");

            if(pos == 0)
                newFrag = new ChartsFragment();
            if(pos == 1)
//                newFrag = new MyListFragment();
            if(pos == 2)
                newFrag = new LastSpotsFragment();

            return newFrag;
        }

        @Override
        public int getCount() {
            Log.d("Ste", "get count called");
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int pos) {

//            if(pos == 0)
//                return "Spots";
//            if(pos == 1)
//                return "Charts";
//            if(pos == 2)
//                return "Last Spots";

            return fixedTabs[pos];  // super.getPageTitle(pos);
        }

    }
*/


}
