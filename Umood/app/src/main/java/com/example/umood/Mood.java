package com.example.umood;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Mood implements Serializable{
    private User user;
    private String date;
    private String time;
    private String emotion;
    private String reason;
    private String socialSituation;
    private double latitude;
    private double longitude;

    /**
     *
     * @param user: the user who have a mood event
     * @param date: Date format: yyyy-mm-dd
     * @param time: Time Format: hh-mm
     * @param emotion: List of Emotion: Sad, Happy, Boring, Anxious, Fearful, Angry
     * @param reason: A string to explain your emotion. Should be less than 50 chars.
     * @param socialSituation: There are 3 social situation: alone. along with 1 person, along with many people
     * @param latitude: a google map data type to indicate where the user is when he/she upload  a new mood event.
     */
    public Mood(User user,String date, String time, String emotion, String reason, String socialSituation, double latitude,double longitude){
        this.user = user;
        this.date = date;
        this.time = time;
        this.emotion = emotion;
        this.reason = reason;
        this.socialSituation = socialSituation;
        this.latitude = latitude;
        this.longitude = longitude;

    }

    public Mood(){

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

    /**
     *
     * @return
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date: Date format: yyyy-mm-dd
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @return
     */
    public String getEmotion() {
        return emotion;
    }


    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSocialSituation() {
        return socialSituation;
    }

    public void setSocialSituation(String socialSituation) {
        this.socialSituation = socialSituation;
    }


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
