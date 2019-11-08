package com.example.umood;

import java.util.ArrayList;

/**
 *  A class user for a list of followings.
 */

public class FollowingList {
    ArrayList<User> following;


    User user;
    public FollowingList(User user) {
        this.user = user;
        following = new ArrayList<>();
    }

    /**
     *
     * @return following
     */

    public ArrayList<User> getFollowing() {
        return following;
    }

    /**
     *
     * @param user add a new user to following list.
     */
    public void addFollowing(User user) {
        following.add(user);
    }

    /**
     *
     * @return user
     */

    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

}
