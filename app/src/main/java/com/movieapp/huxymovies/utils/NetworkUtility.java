package com.movieapp.huxymovies.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtility extends Activity {


    Context context;
    ConnectivityManager connectivityManager;
    boolean connected = false;

    public NetworkUtility(Context context) {
        this.context = context;
    }

    public boolean isOnline() {
        try {
            connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isConnected() && networkInfo.isConnected();
            return connected;

        } catch (Exception e) {
            Log.v("connectivity", e.toString());
        }
        return connected;
    }
}
