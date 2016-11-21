package edu.brandeis.cs.jiahuiming.resumeshare.utils;
//LocationUtil.java
/**
 * Created by jiahuiming on 11/9/16.
 */

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;

import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import edu.brandeis.cs.jiahuiming.resumeshare.views.activities.HomeActivity;

/**
 * Created by Horace on 16/11/9.
 */
public class LocationUtil {
    private Context context;
    private LocationManager lm;
    private LocationListener locationListener;
    private double latitude=0;
    private double longitude=0;
    public LocationUtil(Context context){

        this.context=context;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public void method() {

        lm = (LocationManager) ((HomeActivity) context).getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            Log.d("connect,","failed");
            return;
        }
        Log.d("connect,","success");
        locationListener = new MyLocationListener();
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        updateView(location);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 8, locationListener);
    }

    private class MyLocationListener implements LocationListener {

        public void onLocationChanged(Location loc) {
            updateView(loc);

        }

        public void onProviderDisabled(String provider) {
            updateView(null);
        }

        public void onProviderEnabled(String provider) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                return;
            }
            updateView(lm.getLastKnownLocation(provider));
        }
        public void onStatusChanged(String provider,int status,Bundle extras){

        }
    }

    private void updateView(Location location) {
        if (location != null) {
            setLongitude(location.getLongitude());
            setLatitude(location.getLatitude());

            Toast.makeText(context,location.getLongitude()+" "+location.getLatitude(),Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(context,"Empty",Toast.LENGTH_SHORT).show();
        }
    }
}