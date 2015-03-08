package com.example.stephen.surfsapp;


import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Stephen on 06/03/2015.
 */
public class VolleySingleton {

    // static member instance of the VolleySingleton object
    private static VolleySingleton volleySingleton = null;

    private RequestQueue requestQueue;

    // Helper object which handles loading and caching images from remote URLS
    private ImageLoader imageLoader;

    // private so other classes can't access constructor
    private VolleySingleton() {

        requestQueue = Volley.newRequestQueue(MyApplication.getAppContext());

        // anonymous class, constructor takes two args requestQueue and image cache interface
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            // LruCache object this holds references to values, when accessed values moved to head of queue
            // if cache is full. value at the end of queue is removed
            private LruCache<String, Bitmap> cache = new LruCache<>((int) (Runtime.getRuntime().maxMemory()/1024/8));

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {

                cache.put(url, bitmap);
            }
        });
    }

    // if VolleySingleton object instance is null, instantiate new instance, and return
    public static VolleySingleton getVolleySingleton() {

        if(volleySingleton == null)
            volleySingleton = new VolleySingleton();

        return volleySingleton;
    }

    // method allows other activities and fragments access to requestQueue
    public RequestQueue getRequestQueue() {

        return requestQueue;
    }

    // method allows other activities and fragments access to imageLoader
    public ImageLoader getImageLoader() {

        return imageLoader;
    }

}
