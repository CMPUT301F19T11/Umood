package com.example.umood;


import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class for user class.
 */

public class TestUser{

    public TestUser(){
        super();
    }


    @Test
    public void testSetMoodHistory(){
        User user = new User("a");
        Mood mood = new Mood("2019-11-24","11:23", "Scared","not finish hw", "Alone", -1000, 50, "yifan","");
        ArrayList<Mood> moodHistory = new ArrayList<>();
        moodHistory.add(mood);
        user.setMoodHistory(moodHistory);
        assertEquals(mood, user.getMoodHistory().get(0));
    }

    @Test
    public void testSetFollowing(){
        User user = new User("a");
        ArrayList<String> followings = new ArrayList<>();
        followings.add("b");
        user.setFollowing(followings);
        assertEquals(followings, user.getFollowing());
    }

    @Test
    public void testSetFollower(){
        User user = new User("a");
        ArrayList<String> followers = new ArrayList<>();
        followers.add("b");
        followers.add("c");
        user.setFollower(followers);
        assertEquals(followers, user.getFollower());
    }

    @Test
    public void testSetUnverifiedList(){
        User user = new User("Jack");
        ArrayList<String> unverifiedList = new ArrayList<>();
        unverifiedList.add("Sam");
        unverifiedList.add("peter");
        user.setUnverifiedList(unverifiedList);
        assertEquals(unverifiedList, user.getUnverifiedList());
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
    public void testGetMoodHistory(){
        User user = new User("Sean");
        Mood mood = new Mood("2019-11-20","11:23", "Happy","finish hw", "Alone", -100, 50, "yifan","");
        ArrayList<Mood> moodHistory = new ArrayList<>();
        moodHistory.add(mood);
        user.setMoodHistory(moodHistory);
        assertEquals(mood, user.getMoodHistory().get(0));
    }

    @Test
    public void testAddMood(){
        User user = new User("a");
        Mood mood = new Mood("2019-11-24","11:23", "Happy","finish hw", "Alone", -100, 50, "yifan","");
        ArrayList<Mood> moodHistory = new ArrayList<>();
        moodHistory.add(mood);
        user.setMoodHistory(moodHistory);
        assertEquals(mood, user.getMoodHistory().get(0));
    }

    @Test
    public void testRemoveMood(){
        User user = new User("a");
        Mood mood = new Mood("2019-11-24","11:23", "Happy","finish hw", "Alone", -100, 50, "yifan","");
        Mood mood1 = new Mood("2019-11-12","24:23", "Happy","eat food", "Alone", -300, 50, "yifan","");
        ArrayList<Mood> moodHistory = new ArrayList<>();
        moodHistory.add(mood);
        moodHistory.add(mood1);
        user.setMoodHistory(moodHistory);
        moodHistory.remove(0);
        assertEquals(mood1, user.getMoodHistory().get(0));
    }

    @Test
    public void testGetFollowing(){
        User user = new User("a");
        ArrayList<String> followings = new ArrayList<>();
        followings.add("b");
        followings.add("c");
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
        user.removeFollowing("c");
        assertEquals("f", user.getFollowing().get(1));
    }


    @Test
    public void testGetFollower(){
        User user = new User("a");
        ArrayList<String> followers = new ArrayList<>();
        followers.add("b");
        followers.add("c");
        user.setFollower(followers);
        assertEquals(followers, user.getFollower());
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
        user.removeFollower("c");
        assertEquals("f", user.getFollower().get(1));
    }

    @Test
    public void testGetUnverifiedList(){
        User user = new User("a");
        ArrayList<String> unverifiedList = new ArrayList<>();
        unverifiedList.add("g");
        unverifiedList.add("h");
        user.setUnverifiedList(unverifiedList);
        assertEquals(unverifiedList, user.getUnverifiedList());

    }


    @Test
    public void testAddUnverifiedUser(){
        User user = new User("yifan");
        ArrayList<String> unverifiedList = new ArrayList<>();
        unverifiedList.add("b");
        unverifiedList.add("c");
        user.setUnverifiedList(unverifiedList);
        user.addUnverifiedUser("g");
        assertEquals("g", user.getUnverifiedList().get(2));
    }

    @Test
    public void testRemoveUnverifiedUser(){
        User user = new User("a");
        ArrayList<String> unverifiedList = new ArrayList<>();
        unverifiedList.add("b");
        unverifiedList.add("c");
        user.setUnverifiedList(unverifiedList);
        assertEquals("c", user.getUnverifiedList().get(1));
        user.removeUnverifiedUser("b");
        assertEquals("c", user.getUnverifiedList().get(0));
    }

}
