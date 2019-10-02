package com.example.test;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public class MyLocationManager implements LifecycleObserver {

    private static final String TAG = "MyLocationManager";
    private LocationManager mLocationManager;
    private Context mContext;
    private LocationListener mLocationListener;

    public MyLocationManager(LifecycleOwner lifecycleOwner, Context context, LocationListener locationListener) {
        mContext = context;
        mLocationListener = locationListener;
        lifecycleOwner.getLifecycle().addObserver(this);

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void addListener() {

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {


            mLocationManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
            if (mLocationManager == null) {
                return;
            }
            mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
            Log.d(TAG, "Listener added");

            // Force an update with the last location, if available.
            Location lastLocation = mLocationManager.getLastKnownLocation(
                    android.location.LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                mLocationListener.onLocationChanged(lastLocation);
            }
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void removeListener() {

        if (mLocationManager == null) {
            return;
        }
        mLocationManager.removeUpdates(mLocationListener);
        mLocationManager = null;
        Log.d(TAG, "Listener removed");
    }

}

