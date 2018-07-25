package com.ksn.kraiponn.workshopdao.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.ksn.kraiponn.workshopdao.Manifest;
import com.ksn.kraiponn.workshopdao.R;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    private TextView tvLatitude;
    private TextView tvLongitude;
    private GoogleApiClient mGoogleApi;
    private double mLastLat = 0;
    private double mLastLon = 0;
    private FusedLocationProviderClient providerClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    if(Build.VERSION.SDK_INT >= 21){
        requestRuntimePermission();
    }else {
        //
    }

        initInstance();
    }

    private void initInstance() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        tvLatitude = findViewById(R.id.tvLatitude);
        tvLongitude = findViewById(R.id.tvLongtitude);
        init();
    }

    private void init() {
        mGoogleApi = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(mCallBack)
                .addOnConnectionFailedListener(mOnFailed).build();
    }


    private GoogleApiClient.ConnectionCallbacks mCallBack =
            new GoogleApiClient.ConnectionCallbacks() {
        @SuppressLint("MissingPermission")
        @Override
        public void onConnected(@Nullable Bundle bundle) {
            LocationRequest request = new LocationRequest()
                    .setInterval(5000)
                    .setFastestInterval(5000)
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

            //LocationServices.FusedLocationApi
            providerClient =
                    LocationServices.getFusedLocationProviderClient(getBaseContext());
            providerClient.getLastLocation().addOnSuccessListener(
                    Main2Activity.this,
                    new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            double lat = location.getLatitude();
                            double lon = location.getLongitude();
                            tvLatitude.setText("\n " + lat);
                            tvLongitude.setText("\n " + lon);
                        }
                    }
            );

            /*providerClient.requestLocationUpdates(
                    request, mLocationCallBack, null
            );*/
        }

        @Override
        public void onConnectionSuspended(int i) {

        }
    };

    private LocationCallback mLocationCallBack = new LocationCallback() {
        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            for (Location location : locationResult.getLocations()) {
                tvLatitude.setText(
                        String.valueOf(location.getLatitude())
                );
                tvLongitude.setText(String.valueOf(location.getLongitude()));
            }
        }

        @Override
        public void onLocationAvailability(LocationAvailability locationAvailability) {
            super.onLocationAvailability(locationAvailability);
            return;
        }
    };

    private LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            double lat = location.getLatitude();
            double lon = location.getLongitude();
            if (lat != mLastLat && lon != mLastLon) {
                tvLatitude.setText("\n " + lat);
                tvLongitude.setText("\n " + lon);
                mLastLat = lat;
                mLastLon = lon;
            }
        }
    };

    private GoogleApiClient.OnConnectionFailedListener mOnFailed =
            new GoogleApiClient.OnConnectionFailedListener() {
                @Override
                public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                    //
                }
            };


    @Override
    protected void onStart() {
        if (mGoogleApi != null) {
            mGoogleApi.connect();
        }
        super.onStart();
    }

    @Override
    protected void onStop() {
        if (mGoogleApi != null && mGoogleApi.isConnected()) {
            mGoogleApi.disconnect();
        }
        super.onStop();
    }


    private void requestRuntimePermission() {
        Dexter.withActivity(this)
                .withPermissions(
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                )
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        //
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }


}
