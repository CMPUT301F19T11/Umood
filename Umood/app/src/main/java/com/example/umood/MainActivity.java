package com.example.umood;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "qian";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "qian0");
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Log.d(TAG, "qian1");


        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();


        Log.d(TAG, "qian2");
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        Log.d(TAG, "qian3");
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        Log.d(TAG, "qian4");
        NavigationUI.setupWithNavController(navView, navController);
        Log.d(TAG, "qian5");

        Intent intent = getIntent();

        String username = intent.getStringExtra("username");
        Log.d(TAG, "qian6");



    }
}