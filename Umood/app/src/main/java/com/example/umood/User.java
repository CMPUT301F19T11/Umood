package com.example.umood;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class User implements Serializable {
    @SerializedName("field1")
    @Expose
    private String username;
    @SerializedName("field2")
    @Expose
    private String avatar;
    @SerializedName("field3")
    @Expose
    private ArrayList<Mood> moodHistory;
    @SerializedName("field4")
    @Expose
    private ArrayList<User> followingList;
    @SerializedName("field5")
    @Expose
    private ArrayList<User> followerList;


    /**
     *
     * @param username: An unique ID
     * @param avatar: The user's avatar
     */
    public User(String username, String avatar){
        this.username = username;
        this.avatar = avatar;

        moodHistory = new ArrayList<>();
        followingList = new ArrayList<>();
        followerList = new ArrayList<>();
    }

    public User(String username){
        this.username = username;

        moodHistory = new ArrayList<>();
        followingList = new ArrayList<>();
        followerList = new ArrayList<>();
    }
    public User(){

    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }



    public ArrayList<Mood> getMoodHistory() {
        return moodHistory;
    }
    public void addMood(Mood mood){
        moodHistory.add(mood);
    }
    public void removeMood(Mood mood){
        moodHistory.remove(mood);
    }



    public ArrayList<User> getFollowing() {
        return this.followingList;
    }
    public void addFollowing(User user){
        followingList.add(user);
    }
    public void removeFollowing(User user){
        followingList.remove(user);
    }



    public ArrayList<User> getFollower() {
        return this.followerList;
    }
    public void addFollower(User user){
        followerList.add(user);
    }
    public void removeFollower(User user){
        followerList.remove(user);
    }


}
