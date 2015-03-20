package com.example.stephen.surfsapp;

import android.app.Application;
import android.content.Context;


/**
 * Created by Stephen on 06/03/2015.
 *
 * Custom Application class to get hold of the Application context
 * used by request queue inside VolleySingleton constructor
 */
public class MyApplication extends Application {

    // static ref of  MyApplication, initialised in onCreate
    private static MyApplication myApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
    }

    public static MyApplication getMyApplication() {

        return myApplication;
    }

    // method returns the Application context, and VolleySingleton constructor calls it to create a new request
    public static Context getAppContext() {

        return myApplication.getApplicationContext();
    }
}
