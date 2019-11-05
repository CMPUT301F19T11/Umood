package com.example.umood;

import java.util.ArrayList;

public class FollowingList {
    ArrayList<User> following;


    User user;
    public FollowingList(User user) {
        this.user = user;
        following = new ArrayList<>();
    }


    public ArrayList<User> getFollowing() {
        return following;
    }
    public void addFollowing(User user) {
        following.add(user);
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

}
