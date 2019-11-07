package com.example.umood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class DisplayFollowerActivity extends AppCompatActivity {
    private UserAdapter adapter;
    private ArrayList<Mood> moodList;
    private ListView listView;
    User user;
    private static final String TAG = "qian-follower";
    private long click;
    private UserList followerUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_display_follower);
        Intent intent = getIntent();
        user = (User)intent.getSerializableExtra("user");
        followerUserList = (UserList)intent.getSerializableExtra("follower_list");
        Log.d(TAG, user.getUsername());

        listView = findViewById(R.id.display_follower_list);

        ArrayList<User> userList = followerUserList.getList();
        for(User u:userList){
            Log.d(TAG, u.getUsername());
        }

        // adapter = new UserAdapter(this, R.layout.content_following_request,userList);
        // listView.setAdapter(adapter);



    }
}
