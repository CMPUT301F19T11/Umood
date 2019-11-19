package com.example.umood;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFollowingList {

    public TestFollowingList(){
        super();
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
