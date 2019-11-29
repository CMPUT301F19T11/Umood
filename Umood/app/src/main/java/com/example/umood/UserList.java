package com.example.umood;

import java.io.Serializable;
import java.util.ArrayList;

public class UserList implements Serializable {
    private ArrayList<User> list;

    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    public UserList() {
        list = new ArrayList<>();
    }

    /**
     * User
     * @return a list of users
     */
    public ArrayList<User> getList() {
        return list;
    }

    /**
     * 
     * @param user add a new user to the list
     */
    public void addUser(User user) {
        list.add(user);
    }

    /**
     * 
     * @param pos remove a user from the list
     */
    public void removeUser(int pos) {
        list.remove(pos);
    }

    /**
     * 
     * @return the size of user list
     */
    public int size() {
        return list.size();
    }

    /**
     * 
     * @param username check if user inside the list
     * @return true or false
     */
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

    public void clear(){
        list = null;
        list = new ArrayList<>();

    }
}
