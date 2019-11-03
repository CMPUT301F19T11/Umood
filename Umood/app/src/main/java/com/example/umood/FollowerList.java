package com.example.umood;

import java.util.ArrayList;

public class FollowerList {

    ArrayList<User> following;
    User user;
    public FollowerList(User user) {
        this.user = user;
        following = new ArrayList<>();
    }


    public ArrayList<User> getFollowing() {
        return following;
    }
    public User getUser() {
        return user;
    }


}


