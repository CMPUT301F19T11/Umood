package com.example.umood.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.umood.R;

import com.example.umood.addMoodInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private static final String TAG = "qian-map";
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    static final int PICK_MOOD_REQUEST = 1;
    String mood = "sad";
    Intent intentAdd;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mapView =  root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);



        if(mapView==null)
            Log.d(TAG, "map view is null");
        mapView.getMapAsync(this);



        intentAdd = new Intent(getActivity(), addMoodInfo.class);

        Log.d(TAG, "1");
        FloatingActionButton floatingActionButton = root.findViewById(R.id.floatingActionButton);
        Log.d(TAG, "2 ");
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Almost ");
                startActivityForResult(intentAdd,PICK_MOOD_REQUEST);
            }
        });
        return root;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        int pos;
        if(requestCode == PICK_MOOD_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                mood = data.getStringExtra("DATE");
            }
        }
        Log.d(TAG, "What happened??");

    }
    @Override
    public void onMapReady(GoogleMap googleMap){
        // Need to call MapsInitializer before doing any CameraUpdateFactory calls

        double latitude = 53.5232;
        double longitude = -113.5263;
        LatLng edmonton = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(edmonton).title(mood).snippet("I am "+mood));

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(edmonton)           // Sets the center of the map to location user
                .zoom(15)                   // Sets the zoom
                .bearing(0)                 // Sets the orientation of the camera to east
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        // CameraPosition cameraPosition = new CameraPosition.Builder().target(edmonton).zoom(12).build();
        // gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


}


/*
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
 */