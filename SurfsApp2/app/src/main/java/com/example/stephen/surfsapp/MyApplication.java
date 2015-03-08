package com.example.stephen.surfsapp;

import android.app.Application;
import android.content.Context;


/**
 * Created by Stephen on 06/03/2015.
 */
public class MyApplication extends Application {

    // static member of Application, initialised in onCreate
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
