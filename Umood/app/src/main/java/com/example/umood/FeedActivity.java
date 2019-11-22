package com.example.umood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is DisplayFollowerActivity
 *      This activity can display list of most recent events of your following users
 *
 * Corresponding Backlog:
 *      - 05.03.01:As a participant, I want to view as a list the most recent mood events of the other participants
 *      I am granted to follow, sorted by date and time, in reverse chronological order (most recent coming first).
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */

public class FeedActivity extends AppCompatActivity {
    private static final String TAG = "qian-Feed";
    private User user;

    private ArrayList<String> followingList;
    private MoodAdapterFollowing adapter;
    private ListView listView;
    MainActivity activity;

    private ArrayList<Mood> moodEventList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_feed);

        Intent intent = getIntent();
        user = (User) intent.getSerializableExtra("User");
        MoodList EventList = (MoodList) intent.getSerializableExtra("EventList");
        followingList = user.getFollowing();

        moodEventList = EventList.getList();
        Log.d(TAG, ""+moodEventList.size());
        Collections.sort(moodEventList);
        RecyclerView recyclerView = findViewById(R.id.history_recycle_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MoodAdapterFollowing(moodEventList);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);


        ImageButton cancelButton = findViewById(R.id.signup_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }








/*
        adapter = new MoodAdapter(
                this,
                R.layout.content,
                moodEventList);
        listView = findViewById(R.id.moodListView);
        listView.setAdapter(adapter);

 */



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG,"onResume");
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
