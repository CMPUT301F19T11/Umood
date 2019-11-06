package com.example.umood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MoodHistory extends AppCompatActivity {
    private MoodAdapter adapter;
    private ArrayList<Mood> moodList;
    private ListView listView;
    User user;
    private static final String TAG = "qian-MoodHistory";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "0");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mood_history);

        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("User");
        Log.d(TAG, user.getUsername());
        moodList = user.getMoodHistory();
        Log.d(TAG, "1");
        adapter = new MoodAdapter(
                this,
                R.layout.content,
                moodList);
        Log.d(TAG, "2");

        listView = findViewById(R.id.moodListView);
        listView.setAdapter(adapter);
        Log.d(TAG, "3");
    }
}
