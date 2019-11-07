package com.example.umood;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.umood.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

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
 * Due Date:             November 8, 2019
 * Team name:            CMPUT301F19T11
 * Mentor:               Alexander Filbert
 * Instructor:           Kenny Wong
 * Lab Section:          Monday 1700 - 1950
 * ------------------------------------------------------------------------------------------------------------
 *
 * ------------------------------------------------------------------------------------------------------------
 * Description:
 *      Umood is a simple, easy-to-use, user-friendly, attractive app with partial interaction function
 *  (a friend list which can track and share post but do not need functions to telephone each other ),
 *  and it can post, track, share users’ mood
 *
 * Last Modified:
 *      Nov 6 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */




public class MainActivity extends AppCompatActivity {

    private static final String TAG = "qian-main";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");
    private User user;

    private UserList UnverifiedUser = new UserList();
    private UserList followerUserList = new UserList();
    private UserList followingUserList = new UserList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        Intent intent = getIntent();
        Log.d(TAG, "debug1");
        user = (User) intent.getSerializableExtra("User");

        Log.d(TAG, user.getUsername());
        boolean a = user.getFollowing() == null;
        if(a){
            user.initFollowing();
        }
        boolean b = user.getFollower() == null;
        if(b){
            user.initFollower();
        }
        boolean c = user.getUnverifiedList() == null;
        if(c){
            user.initUnverifiedList();
        }
        Log.d(TAG, ""+a+b+c+"size: "+user.getFollowing().size());
    }


    public User getUser(){
        return user;
    }
    public UserList getUnverifiedUser(){
        return UnverifiedUser;
    }
    public UserList getFollowerUserList(){
        return followerUserList;
    }
    public UserList getFollowingUserList(){
        return followingUserList;
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
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

        boolean a = user.getFollowing() == null;
        if(a){
            user.initFollowing();
        }
        boolean b = user.getFollower() == null;
        if(b){
            user.initFollower();
        }
        boolean c = user.getUnverifiedList() == null;
        if(c){
            user.initUnverifiedList();
        }

        // Obtain Data from database
        ArrayList<String> followingList = user.getFollowing();
        Log.d(TAG, "followingListSize:"+user.getFollowing().size());
        if(followingList!=null) {
            for (String username:followingList) {
                collectionReference.document(username)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                user = document.toObject(User.class);
                                followingUserList.addUser(user);
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
        }

        ArrayList<String> unverifyList = user.getUnverifiedList();
        if(unverifyList!=null) {
            for (String username : unverifyList) {
                Log.d(TAG, "loop");
                collectionReference.document(username)
                    .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user = document.toObject(User.class);
                                boolean a = user==null;
                                Log.d(TAG, ""+a);
                                UnverifiedUser.addUser(user);
                            } else {
                                Log.d(TAG, "not exist");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
        }

        ArrayList<String> followerList = user.getFollower();
        if(followerList!=null) {
            for (String username:followerList) {
                Log.d(TAG, "loop");
                collectionReference.document(username)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user = document.toObject(User.class);
                                boolean a = user==null;
                                Log.d(TAG, ""+a);
                                followerUserList.addUser(user);
                            } else {
                                Log.d(TAG, "not exist");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart");
    }
}
