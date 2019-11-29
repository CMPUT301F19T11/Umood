package com.example.umood;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.umood.Mood;
import com.example.umood.MoodList;
import com.example.umood.User;
import com.example.umood.UserList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;

public class fire {
    private static final String TAG = "qian-firestore";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");
    private User user;
    private UserList UnverifiedUser;
    private UserList followerUserList;
    private UserList followingUserList;
    private MoodList moodEventList;

    public static String getTAG() {
        return TAG;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    public CollectionReference getCollectionReference() {
        return collectionReference;
    }

    public User getUser() {
        return user;
    }

    public UserList getUnverifiedUser() {
        return UnverifiedUser;
    }

    public UserList getFollowerUserList() {
        return followerUserList;
    }

    public UserList getFollowingUserList() {
        return followingUserList;
    }

    public MoodList getMoodEventList() {
        return moodEventList;
    }

    public void setDb(FirebaseFirestore db) {
        this.db = db;
    }

    public void setCollectionReference(CollectionReference collectionReference) {
        this.collectionReference = collectionReference;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUnverifiedUser(UserList unverifiedUser) {
        UnverifiedUser = unverifiedUser;
    }

    public void setFollowerUserList(UserList followerUserList) {
        this.followerUserList = followerUserList;
    }

    public void setFollowingUserList(UserList followingUserList) {
        this.followingUserList = followingUserList;
    }

    public void setMoodEventList(MoodList moodEventList) {
        this.moodEventList = moodEventList;
    }

    public fire(User user){
        UnverifiedUser = new UserList();
        followerUserList = new UserList();
        followingUserList  = new UserList();
        moodEventList = new MoodList();
        this.user = user;
        update();
    }


    public void update(){
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

        // Obtain Data from database
        ArrayList<String> followingList = user.getFollowing();
        if(followingList!=null) {
            for (String username:followingList) {
                collectionReference.document(username)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user2 = document.toObject(User.class);
                                boolean a = followingUserList.getList().contains(user2);
                                if(!followingUserList.is_contain(user2.getUsername()))
                                    followingUserList.addUser(user2);
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
            int index = 0;
            for(User test:followingUserList.getList()){
                if(!followingList.contains(test.getUsername())){
                    followingUserList.removeUser(index);
                }
                index++;
            }

        }



        ArrayList<String> unverifyList = user.getUnverifiedList();
        if(unverifyList!=null) {
            for (String username : unverifyList) {
                collectionReference.document(username)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user2 = document.toObject(User.class);
                                boolean a = user2==null;
                                if(!UnverifiedUser.is_contain(user2.getUsername()))
                                    UnverifiedUser.addUser(user2);
                            } else {
                                Log.d(TAG, "not exist");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
            int index = 0;
            for(User test:UnverifiedUser.getList()){
                if(!unverifyList.contains(test.getUsername())){
                    UnverifiedUser.removeUser(index);
                }
                index++;
            }
        }



        ArrayList<String> followerList = user.getFollower();
        if(followerList!=null) {
            for (String username:followerList) {
                collectionReference.document(username)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User user2 = document.toObject(User.class);
                                boolean a = user==null;
                                if(!followerUserList.is_contain(user2.getUsername()))
                                    followerUserList.addUser(user2);
                            } else {
                                Log.d(TAG, "follower not exist");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
            int index = 0;
            for(User test:followerUserList.getList()){
                if(!followerList.contains(test.getUsername())){
                    followerUserList.removeUser(index);
                }
                index++;
            }
        }


        if(followerList!=null) {
            for (String username : followingList) {
                collectionReference.document(username)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User following = document.toObject(User.class);
                                if (following.getMoodHistory() != null && !following.getMoodHistory().isEmpty()) {
                                    ArrayList<Mood> moodhis = following.getMoodHistory();
                                    Collections.sort(moodhis);
                                    Mood madd = moodhis.get(0);
                                    boolean cond = true;
                                    ArrayList<Mood> abc = moodEventList.getList();
                                    for (Mood m : abc) {
                                        if (m.getTime().equals(madd.getTime())) {
                                            cond = false;
                                            break;
                                        }
                                    }
                                    if (cond) {
                                        moodEventList.addUser(madd);
                                    }
                                }

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
    }
}
