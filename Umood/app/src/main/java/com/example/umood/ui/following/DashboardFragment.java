package com.example.umood.ui.following;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umood.AddFollowingActivity;
import com.example.umood.DisplayFollowerActivity;
import com.example.umood.FollowingRequest;
import com.example.umood.MainActivity;

import com.example.umood.R;
import com.example.umood.User;
import com.example.umood.UserAdapter;
import com.example.umood.UserList;

import java.util.ArrayList;


/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is FriendFragment
 *      In this page, you can see your following list
 *      Also, it provides many buttons. basically, it is the entrance to see your follower, request
 *      and to add following user
 *
 * Corresponding Backlog:
 *      - None
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */

public class DashboardFragment extends Fragment{
    // Data from database
    private User user;
    private UserList UnverifiedUser;
    private UserList followerUserList;
    private UserList followingUserList;

    // ListView Component:
    private UserAdapter adapter;
    private UserAdapter adapter2;
    RecyclerView recyclerView;
    private ArrayList<String> followingList;

    //Debug:
    private static final String TAG = "qian-following";
    private MainActivity activity;
    private Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        activity = (MainActivity) getActivity();

        // Obtain Data from database
        user = activity.getUser();
        followingUserList = activity.getFollowingUserList();
        followerUserList = activity.getFollowerUserList();
        UnverifiedUser = activity.getUnverifiedUser();


        ArrayList<User> userView = followingUserList.getList();
        ArrayList<User> userFollowerView = followerUserList.getList();
        Log.d(TAG, ""+userView.size());
        if(userView.size()>0) {
            for (User u : userView) {
                Log.d(TAG, u.getUsername());
            }
        }

        Button followingButton = root.findViewById(R.id.button21);
        Button followerButton = root.findViewById(R.id.button22);
        // RecycleView Components:
        recyclerView = root.findViewById(R.id.recycle_view_following);
        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserAdapter(userView);
        adapter2 = new UserAdapter(userFollowerView);
        recyclerView.setAdapter(adapter);

        followingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(adapter);
            }
        });


        followerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recyclerView.setAdapter(adapter2);
            }
        });


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        activity.update();
    }

    @Override
    public void onPause() {
        super.onPause();
        activity.update();
    }


}