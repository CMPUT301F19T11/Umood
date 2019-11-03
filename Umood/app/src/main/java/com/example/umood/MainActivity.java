package com.example.umood;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

/**
 * ------------------------------------------------------------------------------------------------------------
 * License:
 * This group project is under the MIT License
 *
 * MIT License
 *
 * Copyright (c) 2019 CMPUT301F19T11
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 * ------------------------------------------------------------------------------------------------------------
 *
 * ------------------------------------------------------------------------------------------------------------
 * Assignment:           Group Project Part 3
 * Due Date:             November 1, 2019
 * Team name:            CMPUT301F19T11
 * Mentor:               Alexander Filbert
 * Instructor:           Kenny Wong
 * Lab Section:          Monady 1700 - 1950
 * ------------------------------------------------------------------------------------------------------------
 *
 * ------------------------------------------------------------------------------------------------------------
 * Description:
 *      Umood is a simple, easy-to-use, user-friendly, attractive app with partial interaction function
 *  (a friend list which can track and share post but do not need functions to telephone each other ),
 *  and it can post, track, share users’ mood
 *
 * Last Modified:
 *      October 31 by Qian Yu
 *
 * ------------------------------------------------------------------------------------------------------------
 */




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


        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);



    }
}

/*

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, addEmotionalState.class);
                startActivity(intent);
            }
        });
 */