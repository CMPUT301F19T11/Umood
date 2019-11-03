package com.example.umood;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class User {
    private String username;
    private String avatar;
    private ArrayList<Mood> moodHistory;
    private ArrayList<User> followingList;
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
