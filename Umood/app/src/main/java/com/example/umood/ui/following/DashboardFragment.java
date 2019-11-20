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

public class DashboardFragment extends Fragment{
    // Data from database
    private User user;
    private UserList UnverifiedUser;
    private UserList followerUserList;
    private UserList followingUserList;

    // ListView Component:
    private UserAdapter adapter;
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
        Log.d(TAG, ""+userView.size());
        if(userView.size()>0) {
            for (User u : userView) {
                Log.d(TAG, u.getUsername());
            }
        }

        // Buttons and views in XML
        Button request = root.findViewById(R.id.request);
        Button follower = root.findViewById(R.id.follower);
        Button addFollowing = root.findViewById(R.id.addFollowing);


        if(!UnverifiedUser.getList().isEmpty()){
            Log.d(TAG, "haha");
            ImageView imageDot = root.findViewById(R.id.red_dot);
            GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.OVAL);
            gd.setColor(Color.rgb(134, 135, 255));
            gd.setCornerRadius(5);
            gd.setStroke(4, Color.rgb(255, 255, 255));



            int h = gd.getIntrinsicHeight();
            int w = gd.getIntrinsicWidth();
            gd.setBounds( 0, 0, w, h );

            request.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.followingrequest),
                    null,
                    getResources().getDrawable(R.drawable.newstate),
                    null);
        }

        Log.d(TAG, "size+"+userView.size());
        // RecycleView Components:
        RecyclerView recyclerView = root.findViewById(R.id.recycle_view_following);
        GridLayoutManager layoutManager = new GridLayoutManager(root.getContext(),1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new UserAdapter(userView);
        recyclerView.setAdapter(adapter);


        addFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity,AddFollowingActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, FollowingRequest.class);
                intent.putExtra("user",user);
                intent.putExtra("user_list",UnverifiedUser);
                startActivity(intent);
            }
        });

        follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, DisplayFollowerActivity.class);
                intent.putExtra("user",user);
                intent.putExtra("follower_list",followerUserList);
                startActivity(intent);
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