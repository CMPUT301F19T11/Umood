package com.example.umood;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    MapView mapView;
    GoogleMap gmap;
    public static final String TAG = "qian";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView =  findViewById(R.id.mapView);
        Log.d(TAG, "anshishabi2");
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        Log.d(TAG, "anshishabi3");

        if(mapView==null)
            Log.d(TAG, "mapview is null");
        Log.d(TAG, "anshishabi4");
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Log.d(TAG, "anshishabi4.5");
                gmap = googleMap;
                Log.d(TAG, "anshishabi5");
                gmap.setMyLocationEnabled(true);
                Log.d(TAG, "anshishabi6");
                LatLng edmonton = new LatLng(53, -113);
                Log.d(TAG, "anshishabi7");
                gmap.addMarker(new MarkerOptions().position(edmonton).title("Marker Title").snippet("Marker Description"));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(edmonton).zoom(12).build();
                gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

            }
        });



    }

}




/*
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
 */