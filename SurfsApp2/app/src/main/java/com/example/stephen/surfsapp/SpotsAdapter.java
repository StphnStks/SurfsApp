package com.example.stephen.surfsapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.stephen.surfsapp.NavDrawerAdapter.NavDrawerListener;

/**
 * Created by Stephen on 13/03/2015.
 */
public class SpotsAdapter extends RecyclerView.Adapter<SpotsAdapter.RecyclerViewHolderSpots> {

    private LayoutInflater layoutInflater;

    private ArrayList<Spot> listSpots = new ArrayList<>();

    NavDrawerListener navDrawerListener;  // field to set listener


    public SpotsAdapter(Context context) {

        // initialize layout inflater with context passed into constructor
        layoutInflater = layoutInflater.from(context);
    }

    // setter method for spot objects
    public void setListSpots(ArrayList<Spot> listSpots) {
        this.listSpots = listSpots;
        notifyItemRangeChanged(0, listSpots.size());    // notify the amount of changed data
    }

    @Override
    public RecyclerViewHolderSpots onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflates the xml layout file, assigning to a view object
        View view = layoutInflater.inflate(R.layout.recycler_view_row_spots, parent, false);

        // pass view instance to View Holder constructor (inner class)
        RecyclerViewHolderSpots viewHolderSpots = new RecyclerViewHolderSpots(view);

        return viewHolderSpots;
    }

    // bind data to the view holder object
    @Override
    public void onBindViewHolder(RecyclerViewHolderSpots holder, int position) {

        // get the spot in current position of the ArrayList and set the text for it
        Spot currentSpot = listSpots.get(position);
        holder.spotName.setText(currentSpot.getSpotName());

    }

    // return the size of ArrayList
    @Override
    public int getItemCount() {
        return listSpots.size();
    }

    // method sets the listener for Spots, navDrawerListener parameter is a ref to instance inside SpotsFragment
    public void setNavDrawerListener(NavDrawerListener navDrawerListener) {
        this.navDrawerListener = navDrawerListener;
    }


    // inner class constructs the viewHolder widgets (text view etc)
    class RecyclerViewHolderSpots extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView spotName;

//        private ImageView imageView;

        public RecyclerViewHolderSpots(View itemView) {
            super(itemView);

            // get hold of widget in xml file, set listener on view
            spotName = (TextView) itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Log.e("Spots", " Adapter clicked");

            if(navDrawerListener != null)
                navDrawerListener.itemViewClicked(v, getPosition());
        }
    }


}
