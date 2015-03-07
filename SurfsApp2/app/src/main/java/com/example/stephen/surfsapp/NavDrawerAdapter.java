package com.example.stephen.surfsapp;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Stephen on 01/03/2015.
 *
 * This class is a custom adapter class for the navigation drawer recyclerView creating a view
 * which will be used to bind an icon and title for each row of the navigation drawer options menu
 *
 */


// adapter for the navigation drawer's recycle view
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.RecyclerViewHolder> {

    private final LayoutInflater inflater;

    Context context;
    List<NavDrawerMenu> iconsAndTitles = Collections.emptyList();

    NavDrawerListener navDrawerListener;    // field to set listener

    // initialises the view and list of titles and icons
    public NavDrawerAdapter(Context context, List<NavDrawerMenu> iconsAndTitles) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.iconsAndTitles = iconsAndTitles;
    }

    // returns item view (icon and text) it's type being the inner class
    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // inflates the layout for the recycler view (icon and text)
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);

        // initializes the viewHolder, this will contain the row views (populated with menu title icon)
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        // binding the menu's title and icon to row
        NavDrawerMenu currentPos = iconsAndTitles.get(position);
        holder.menuTitle.setText(currentPos.menuTitle);
        holder.menuIcon.setImageResource(currentPos.menuIcon);
    }

    @Override
    public int getItemCount() {
        return iconsAndTitles.size();   //returns the size of the list
    }

    // method sets the listener for Navigation Drawer, navDrawerListener parameter is a ref to instance inside NavigationDrawerFragment
    public void setNavDrawerListener(NavDrawerListener navDrawerListener) {
        this.navDrawerListener = navDrawerListener;
    }

    // this inner class represents the item view rows (icon and text) inside the recyclerView
    class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView menuTitle;
        ImageView menuIcon;

        // constructs the text and icon views for the recyclerView rows
        public RecyclerViewHolder(View itemView) {
            super(itemView);

            menuTitle = (TextView) itemView.findViewById(R.id.menuTitle);
            menuIcon = (ImageView) itemView.findViewById(R.id.menuIcon);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            // only if the NavigationDrawerFragment sets the listener then itemViewClicked is called
            if(navDrawerListener != null)
                navDrawerListener.itemViewClicked(v, getPosition());
        }
    }

    // an interface for activities & fragments to implement allowing them to handle item view clicks inside the Navigation drawer
    public interface NavDrawerListener {
        public void itemViewClicked(View view, int position);
    }
}

