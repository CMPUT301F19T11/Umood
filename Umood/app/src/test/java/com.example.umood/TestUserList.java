package com.example.umood;

import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserList {
    public TestUserList(){
        super();
    }


    @Test
    public void testGetList(){
        ArrayList<User> list = new ArrayList<>();
        User user = new User("a");
        User user1 = new User("b");
        list.add(user);
        list.add(user1);
        assertEquals(user, list.get(0));
        assertEquals(user1, list.get(1));

    }

    @Test
    public void testAddUser(){
        UserList list = new UserList();
        User user = new User("a");
        list.addUser(user);
        assertEquals(user, list.getList().get(0));
    }

    @Test
    public void testRemoveUser(){
        UserList list = new UserList();
        User user = new User("a");
        list.addUser(user);
        User user1 = new User("b");
        list.addUser(user1);
        list.removeUser(1);
        assertEquals(user, list.getList().get(0));
    }
    @Test
    public void testGetSize(){
        UserList list = new UserList();
        User user = new User("a");
        list.addUser(user);
        User user1 = new User("b");
        list.addUser(user1);
        assertEquals(2, list.size());
    }

    @Test
    public void testIsContain(){
        UserList list = new UserList();
        User user = new User("a");
        list.addUser(user);
        //user.setUsername("b");
        assertTrue(list.is_contain(user.getUsername()));
    }

}
