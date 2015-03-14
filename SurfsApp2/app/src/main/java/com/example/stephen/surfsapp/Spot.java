package com.example.stephen.surfsapp;

/**
 * Created by Stephen on 13/03/2015.
 */
public class Spot {

    private String spotName;

    public Spot() {

    }

    public Spot(String spotName) {

        this.spotName = spotName;
    }

    public String getSpotName() {
        return spotName;
    }

    public void setSpotName(String spotName) {
        this.spotName = spotName;
    }

    @Override
    public String toString() {
        return "Spot: " + spotName;
    }

}
