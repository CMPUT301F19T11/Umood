package com.example.umood.ui.following;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.umood.AddFollowingActivity;
import com.example.umood.DisplayFollowerActivity;
import com.example.umood.FollowingRequest;
import com.example.umood.MainActivity;
import com.example.umood.MoodAdapter;
import com.example.umood.R;
import com.example.umood.User;
import com.example.umood.UserAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DashboardFragment extends Fragment{
    User user;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference docref;
    private CollectionReference collectionReference = db.collection("users");
    private static final String TAG = "yan";
    private ArrayAdapter<User> adapter;
    private ArrayList<String> followingList;
    private ArrayList<User> followingUserList;
    MainActivity activity;

    Intent intent;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        activity = (MainActivity) getActivity();
        user = activity.getUser();


        // Buttons and views in XML
        Button request = root.findViewById(R.id.request);
        Button follower = root.findViewById(R.id.follower);
        Button addFollowing = root.findViewById(R.id.addFollowing);
        ListView listView = root.findViewById(R.id.friendList);


        // Init
        followingUserList = new ArrayList<>();

        // Obtain Data from database

        followingList = user.getFollowing();
        if(followingList!=null) {
            for (final String username : followingList) {
                collectionReference.document(username)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                user = document.toObject(User.class);
                                followingUserList.add(user);

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

        adapter = new UserAdapter(activity , R.layout.content_following, followingUserList);
        listView.setAdapter(adapter);



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
                startActivity(intent);
            }
        });

        follower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(activity, DisplayFollowerActivity.class);
                intent.putExtra("user",user);
                startActivity(intent);
            }
        });

        return root;
    }
}