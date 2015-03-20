package com.example.stephen.surfsapp;

import android.util.Log;

/**
 * Created by Stephen on 19/03/2015.
 */
public class ChartImagesSw {

//    private String[] swImg = new String[2];

    private String swImg = new String();

    private int pos;

    public ChartImagesSw() {

    }

//    public ChartImagesSw(String swImg) {
//        this.swImg = swImg;
//    }

    public void setSwImg(String swImg) {
        this.swImg = swImg;

        Log.i("Set ", "img " + swImg);
//        this.swImg[pos] = swImg[pos];
    }

    public String getSwImg(String swImg) {

//        new String[]{swImg[pos]}

        Log.i("Get ", "img " + swImg);

        return swImg;
    }



}
