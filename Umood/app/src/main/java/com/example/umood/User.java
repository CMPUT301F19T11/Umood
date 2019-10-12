package com.example.umood;

import java.util.ArrayList;

public class User {
    private String username;
    private ArrayList<Mood> moodHistory;
    private ArrayList<User> friendList;

    public User(String username){
        this.username = username;
        moodHistory = new ArrayList<>();
        friendList = new ArrayList<>();
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

    public ArrayList<User> getFriendList() {
        return friendList;
    }
    public void addFriend(User user) {
        friendList.add(user);
    }
    public void removeFriend(User user){
        friendList.remove(user);
    }
}
