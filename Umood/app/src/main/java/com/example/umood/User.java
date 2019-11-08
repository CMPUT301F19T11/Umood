package com.example.umood;

import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String username;

    private String avatar;

    private ArrayList<Mood> moodHistory;

    private ArrayList<String> followingList;

    private ArrayList<String> followerList;

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
        unverifiedList = new ArrayList<>();
    }
    public User(){
    }


    public void setMoodHistory(ArrayList<Mood> moodHistory) {
        this.moodHistory = moodHistory;
    }

    public void setFollowing(ArrayList<String> followingList) {
        this.followingList = followingList;
    }

    public void setFollower(ArrayList<String> followerList) {
        this.followerList = followerList;
    }

    public void setUnverifiedList(ArrayList<String> unverifiedList) {
        this.unverifiedList = unverifiedList;
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
    public void initFollowing() {
        followingList = new ArrayList<>();
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
    public void initFollower(){
        followerList = new ArrayList<>();
    }
    public void addFollower(String user){
        followerList.add(user);
    }
    public void removeFollower(String user){
        followerList.remove(user);
    }





    public ArrayList<String> getUnverifiedList() {
        return this.unverifiedList;
    }
    public void initUnverifiedList(){
        unverifiedList = new ArrayList<>();
    }
    public void addUnverifiedUser(String username) {
        this.unverifiedList.add(username);
    }
    public void removeUnverifiedUser(String username) {
        this.unverifiedList.remove(username);
    }





}
