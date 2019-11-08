package com.example.umood;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    private String username;

    private String avatar;

    private ArrayList<Mood> moodHistory;

    private ArrayList<String> followingList;

    private ArrayList<String> followerList;

    private ArrayList<String> unverifiedList;


    /**
     *
     * @param username: An unique ID
     * @param avatar: The user's avatar
     */
    public User(String username, String avatar){
        this.username = username;
        this.avatar = avatar;

        moodHistory = new ArrayList<>();
        followingList = new ArrayList<>();
        followerList = new ArrayList<>();
        unverifiedList = new ArrayList<>();
    }



    public User(String username){
        this.username = username;
        moodHistory = new ArrayList<>();
        followingList = new ArrayList<>();
        followerList = new ArrayList<>();
        unverifiedList = new ArrayList<>();
    }
    public User(){
    }


    /**
     *
     * @param moodHistory a list of mood events
     */


    public void setMoodHistory(ArrayList<Mood> moodHistory) {
        this.moodHistory = moodHistory;
    }

    /**
     *
     * @param followingList a list of people that user is following.
     */
    public void setFollowing(ArrayList<String> followingList) {
        this.followingList = followingList;
    }

    /**
     *
     * @param followerList a list of people who are following user
     */

    public void setFollower(ArrayList<String> followerList) {
        this.followerList = followerList;
    }

    /**
     *
     * @param unverifiedList a list of unverified
     */
    public void setUnverifiedList(ArrayList<String> unverifiedList) {
        this.unverifiedList = unverifiedList;
    }

    /**
     *
     * @return a unique username
     */

    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username a unique username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return a list mood events
     */

    public ArrayList<Mood> getMoodHistory() {
        return moodHistory;
    }

    /**
     *
     * @param mood add a new mood event to a list of mood events
     */
    public void addMood(Mood mood){
        moodHistory.add(mood);
    }

    /**
     *
     * @param mood delete a mood event from the list of mood events
     */
    public void removeMood(Mood mood){
        moodHistory.remove(mood);
    }


    /**
     *
     * @return a list of following
     */

    public ArrayList<String> getFollowing() {
        return this.followingList;
    }

    /**
     * create a new list as following list
     */
    public void initFollowing() {
        followingList = new ArrayList<>();
    }

    /**
     *
     * @param user add a new user to following list
     */
    public void addFollowing(String user){
        followingList.add(user);
    }

    /**
     *
     * @param user remove a user from following list
     */
    public void removeFollowing(String user){
        followingList.remove(user);
    }


    /**
     *
     * @return a list of follower
     */

    public ArrayList<String> getFollower() {
        return this.followerList;
    }

    /**
     * create a new list as follower list
     */
    public void initFollower(){
        followerList = new ArrayList<>();
    }

    /**
     *
     * @param user adding a new user to follower list
     */
    public void addFollower(String user){
        followerList.add(user);
    }

    /**
     *
     * @param user remove user from follower list
     */
    public void removeFollower(String user){
        followerList.remove(user);
    }


    /**
     *
     * @return a list of unverified
     */


    public ArrayList<String> getUnverifiedList() {
        return this.unverifiedList;
    }

    /**
     * create a new list as unverified list
     */
    public void initUnverifiedList(){
        unverifiedList = new ArrayList<>();
    }

    /**
     *
     * @param username add new created username to unverified list
     */
    public void addUnverifiedUser(String username) {
        this.unverifiedList.add(username);
    }

    /**
     *
     * @param username delete a username from unverified list
     */
    public void removeUnverifiedUser(String username) {
        this.unverifiedList.remove(username);
    }


    /**
     *
     * @return most recently mood event
     */


    public Mood getMostRecentMood() {
        if(this.moodHistory.isEmpty()){
            return null;
        }
        int length = this.moodHistory.size()-1;
        return this.moodHistory.get(length);
    }

}
