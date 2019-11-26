package com.example.umood.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.umood.AddFollowingActivity;
import com.example.umood.ChartActivity;
import com.example.umood.FeedActivity;
import com.example.umood.FollowingRequest;
import com.example.umood.MainActivity;
import com.example.umood.MoodHistory;
import com.example.umood.MoodList;
import com.example.umood.R;
import com.example.umood.SettingActivity;
import com.example.umood.User;
import com.example.umood.UserList;

/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is ProfileFragment
 *      This fragment can display your username and other basic information
 *      Also, It provides many buttons.
 *      You can go to check your mood history and your friend's mood event from here.
 *
 * Corresponding Backlog:
 *      - US 03.01.01
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */

public class NotificationsFragment extends Fragment {
    private User user;
    private MainActivity activity;
    private static final String TAG = "qian-profile";


    private Intent intent;

    private UserList UnverifiedUser;

    public View onCreateView(@NonNull final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        activity = (MainActivity) getActivity();
        final MoodList moodEventList = activity.getMoodEventList();
        user = activity.getUser();



        ImageView imageView = root.findViewById(R.id.avatar);
        Context context = imageView.getContext();
        int picID =  context.getResources().getIdentifier(user.getAvatar(), "drawable", context.getPackageName());
        imageView.setImageResource(picID);

        // Text view Setting
        TextView usernameView = root.findViewById(R.id.username);
        TextView followingNumber = root.findViewById(R.id.following_number);
        TextView followerNumber = root.findViewById(R.id.follower_number);
        TextView postNumber =  root.findViewById(R.id.postNumber);

        usernameView.setText(user.getUsername());
        followerNumber.setText(String.valueOf(activity.getFollowerUserList().size()) );
        followingNumber.setText(String.valueOf(activity.getFollowingUserList().size()));
        postNumber.setText(String.valueOf(user.getMoodHistory().size()));

        Button history =  root.findViewById(R.id.button_history);
        Button chart =  root.findViewById(R.id.button_chart);
        Button feed =  root.findViewById(R.id.button_feed);
        Button setting =  root.findViewById(R.id.button_setting);



        // Buttons and views in XML
        Button request = root.findViewById(R.id.request);
        Button addFollowing = root.findViewById(R.id.addFollowing);

        UnverifiedUser = activity.getUnverifiedUser();
        if(!UnverifiedUser.getList().isEmpty()){
            Log.d(TAG, "haha");
            GradientDrawable gd = new GradientDrawable();
            gd.setShape(GradientDrawable.OVAL);
            gd.setColor(Color.rgb(134, 135, 255));
            gd.setCornerRadius(5);
            gd.setStroke(4, Color.rgb(255, 255, 255));
            int h = gd.getIntrinsicHeight();
            int w = gd.getIntrinsicWidth();
            gd.setBounds( 0, 0, w, h );

            request.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.ic_followers),
                    null,
                    getResources().getDrawable(R.drawable.profilearrow),
                    null);


        }


        addFollowing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, AddFollowingActivity.class);
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

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, MoodHistory.class);
                intent.putExtra("User",user );
                startActivity(intent);
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, FeedActivity.class);
                intent.putExtra("User",user);
                intent.putExtra("EventList",moodEventList);
                startActivity(intent);
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, SettingActivity.class);
                startActivity(intent);

            }
        });

        chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, ChartActivity.class);
                intent.putExtra("User",user);
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
