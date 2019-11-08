package com.example.umood;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserList {
    public TestUserList(){
        super();
    }

    @Test
    public void testAddUser(){
        UserList list = new UserList();
        User user = new User();
        list.addUser(user);
        assertEquals(user, list.getList().get(0));
    }

    @Test
    public void testRemoveUser(){
        UserList list = new UserList();
        User user = new User();
        list.addUser(user);
        User user1 = new User();
        list.addUser(user1);
        list.removeUser(1);
        assertEquals(user, list.getList().get(0));
    }
    @Test
    public void testGetSize(){
        UserList list = new UserList();
        User user = new User();
        list.addUser(user);
        User user1 = new User();
        list.addUser(user1);
        assertEquals(2, list.size());
    }

    @Test
    public void testIsContain(){
        UserList list = new UserList();
        User user = new User();
        list.addUser(user);
        user.setUsername("b");
        assertTrue(list.is_contain(user.getUsername()));
    }

}
