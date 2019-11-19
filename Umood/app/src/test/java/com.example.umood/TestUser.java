package com.example.umood;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for user class.
 */

public class TestUser {

    public TestUser(){
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
    public void testSetFollowingList(){
        User user = new User("a");
        ArrayList<String> followings = new ArrayList<>();
        followings.add("b");
        user.setFollowing(followings);
        assertEquals(followings, user.getFollowing());
    }

    @Test
    public void testAddFollowings(){
        User user = new User("a");
        ArrayList<String> followings = new ArrayList<>();
        followings.add("b");
        user.setFollowing(followings);
        user.addFollowing("f");
        assertEquals("f", user.getFollowing().get(1));
    }

    @Test
    public void testRemoveFollowings(){
        User user = new User("a");
        ArrayList<String> followings = new ArrayList<>();
        followings.add("b");
        followings.add("c");
        user.setFollowing(followings);
        user.addFollowing("f");
        assertEquals("f", user.getFollowing().get(2));
        user.removeFollowing("f");
        assertEquals("c", user.getFollowing().get(1));
    }

    @Test
    public void testGetUsername(){
        User user = new User("angela");
        assertEquals("angela", user.getUsername());
    }

    @Test
    public void testSetUsername(){
        User user = new User("angela");
        user.setUsername("jack");
        assertEquals("jack", user.getUsername());
    }

    @Test
    public void testGetFollowerList(){
        User user = new User("a");
        ArrayList<String> followers = new ArrayList<>();
        followers.add("b");
        followers.add("c");
        user.setFollowing(followers);
        assertEquals(followers, user.getFollowing());
    }

    @Test
    public void testSetFollowerList(){
        User user = new User("a");
        ArrayList<String> followers = new ArrayList<>();
        followers.add("b");
        followers.add("c");
        user.setFollowing(followers);
        assertEquals(followers, user.getFollowing());
    }


    @Test
    public void testAddFollowers(){
        User user = new User("a");
        ArrayList<String> followers = new ArrayList<>();
        followers.add("b");
        user.setFollower(followers);
        user.addFollower("f");
        assertEquals("f", user.getFollower().get(1));
    }

    @Test
    public void testRemoveFollowers(){
        User user = new User("a");
        ArrayList<String> followers = new ArrayList<>();
        followers.add("b");
        followers.add("c");
        user.setFollower(followers);
        user.addFollower("f");
        assertEquals("f", user.getFollower().get(2));
        user.removeFollower("f");
        assertEquals("c", user.getFollower().get(1));
    }



}
