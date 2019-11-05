package com.example.umood.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.umood.MainActivity;
import com.example.umood.Mood;
import com.example.umood.R;

import com.example.umood.User;
import com.example.umood.addMoodInfo;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;


public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private static final String TAG = "qian-map";
    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final int PICK_MOOD_REQUEST = 1;
    private GoogleMap gMap;
    private Intent intentAdd;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docref;
    private CollectionReference collectionReference = db.collection("users");
    private Mood mood;


    private User user;

    @NonNull
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        MainActivity activity = (MainActivity) getActivity();
        user = activity.getUser();
        docref = collectionReference.document(user.getUsername());

        Log.d(TAG, "Almost2 ");

        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        if(mapView==null)
            Log.d(TAG, "map view is null");

        mapView.getMapAsync(this);

        intentAdd = new Intent(getActivity(), addMoodInfo.class);

        FloatingActionButton floatingActionButton = root.findViewById(R.id.floatingActionButton);

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
        if(requestCode == PICK_MOOD_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String strMood = data.getStringExtra("Mood");
                double latitude = 53.5232+ 0.04*Math.random();
                double longitude = -113.5263 + 0.04*Math.random();
                LatLng edmonton = new LatLng(latitude, longitude);
                gMap.addMarker(new MarkerOptions().position(edmonton).title(strMood).snippet("I am "+strMood));

                mood = new Mood(user,"DatePlaceholder","TimePlaceholder",strMood,"reason","social",latitude,longitude);
                docref.update("moodHistory", FieldValue.arrayUnion(mood));
                mapView.getMapAsync(this);
            }
            else
                Log.d(TAG, "????2");
        }
        else
            Log.d(TAG, "What happened??");
    }


    @Override
    public void onMapReady(GoogleMap googleMap){
        // Need to call MapsInitializer before doing any CameraUpdateFactory calls
        gMap = googleMap;
        double latitude = 53.5232;
        double longitude = -113.5263;
        LatLng edmonton = new LatLng(latitude, longitude);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(edmonton)           // Sets the center of the map to location user
                .zoom(12)                   // Sets the zoom
                .bearing(0)                 // Sets the orientation of the camera to east
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        ArrayList<Mood> moodHistory = user.getMoodHistory();
        for(Mood mood:moodHistory) {
            String emotion = mood.getEmotion();
            double latitudeMood = mood.getLatitude();
            double longitudeMood = mood.getLongitude();
            LatLng location = new LatLng(latitudeMood, longitudeMood);
            googleMap.addMarker(new MarkerOptions().position(location).title(emotion).snippet("I am " + emotion));
        }

        // CameraPosition cameraPosition = new CameraPosition.Builder().target(edmonton).zoom(12).build();
        // gmap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    @Override
    public void onResume() {
        super.onResume();

        mapView.onResume();
        collectionReference.document(user.getUsername())
                .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        user = document.toObject(User.class);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
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

