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
    private ArrayList<String> followingList;
    @SerializedName("field5")
    @Expose
    private ArrayList<String> followerList;
    @SerializedName("field6")
    @Expose
    private ArrayList<String> unverifiedList;


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
        unverifiedList = new ArrayList<>();
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



    public ArrayList<String> getFollowing() {
        return this.followingList;
    }
    public void addFollowing(String user){
        followingList.add(user);
    }
    public void removeFollowing(String user){
        followingList.remove(user);
    }



    public ArrayList<String> getFollower() {
        return this.followerList;
    }
    public void addFollower(String user){
        followerList.add(user);
    }
    public void removeFollower(String user){
        followerList.remove(user);
    }



    public ArrayList<String> getunverifiedList() {
        return this.unverifiedList;
    }

    public Mood getMostRecentMood() {
        if(this.moodHistory.isEmpty()){
            return null;
        }
        int length = this.moodHistory.size()-1;
        return this.moodHistory.get(length);
    }



}
