package com.example.umood;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class DisplayFollowerActivity extends AppCompatActivity {
    private static final String TAG = "qian-follower";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_display_follower);


        // Declare:
        Intent intent = getIntent();
        RecyclerView recyclerView;
        UserAdapter adapter;
        User user;
        user = (User)intent.getSerializableExtra("user");
        UserList followerUserList = (UserList)intent.getSerializableExtra("follower_list");
        Log.d(TAG, user.getUsername());

        recyclerView = findViewById(R.id.recycle_view_follower);
        ArrayList<User> userList = followerUserList.getList();
        ImageButton cancel = findViewById(R.id.imageButton);
        for(User u:userList){
            Log.d(TAG, u.getUsername());
        }

        // RecycleView Components:
        GridLayoutManager layoutManager = new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserAdapter(userList);
        recyclerView.setAdapter(adapter);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
