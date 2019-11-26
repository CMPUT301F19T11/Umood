package com.example.umood;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFollowingList{

    public TestFollowingList(){
        super();
    }

    @Test
    public void testGetFollowingList(){
        User user = new User("a");
        ArrayList<String> followings = new ArrayList<>();
        followings.add("b");
        followings.add("c");
        user.setFollowing(followings);
        assertEquals(followings, user.getFollowing());
    }

    @Test
    public void testGetFollowing(){
        User user = new User("Jack");
        User user1 = new User("Booth");
        ArrayList<User> following = new ArrayList<>();
        following.add(user);
        following.add(user1);
        assertEquals(user, following.get(0));
        assertEquals(user1, following.get(1));

    }

    @Test
    public void testAddFollowing(){
        User user = new User("c");
        FollowingList list = new FollowingList(user);
        list.addFollowing(user);

        assertEquals(user, list.getFollowing().get(0));
    }

    @Test
    public void testGetUser(){
        User user = new User("c");
        FollowingList list = new FollowingList(user);
        assertEquals(user, list.getUser());

    }

    @Test
    public void testSetUser(){
        User user = new User("c");
        FollowingList list = new FollowingList(user);
        User user1 = new User("d");
        list.setUser(user1);
        assertEquals(user1, list.getUser());
    }
}
