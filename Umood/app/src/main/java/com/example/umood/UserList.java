package com.example.umood;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable {
    ArrayList<User> list;

    public UserList() {
        list = new ArrayList<>();
    }


    public ArrayList<User> getList() {
        return list;
    }
    public void addUser(User user) {
        list.add(user);
    }
    public void removeUser(int pos) {
        list.remove(pos);
    }
    public int size() {
        return list.size();
    }
    public boolean is_contain(String username){
        if(list==null)
            return false;
        for(User user:list){
            if(username.equals(user.getUsername())){
                return true;
            }
        }
        return false;
    }
}
