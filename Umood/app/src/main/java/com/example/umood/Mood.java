package com.example.umood;

import android.graphics.Bitmap;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Mood implements Serializable,Comparable<Mood>{
    private User user;
    private String date;
    private String time;
    private String emotion;
    private String reason;
    private String socialSituation;
    private double latitude;
    private double longitude;
    private String username;
    private String imagePath;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param date: Date format: yyyy-mm-dd
     * @param time: Time Format: hh-mm
     * @param emotion: List of Emotion: Sad, Happy, Boring, Anxious, Fearful, Angry
     * @param reason: A string to explain your emotion. Should be no more than 20 chars or 3 words.
     * @param socialSituation: There are 3 social situation: alone, with one other person, with two to several people, or with a crowd
     * @param latitude: a google map data type to indicate where the user is when he/she upload  a new mood event.
     * @param longitude : a google map data type to indicate where the user is when he/she upload  a new mood event.
     */

    public Mood(String date, String time, String emotion, String reason, String socialSituation, double latitude,double longitude,String username,String imagePath){
        this.date = date;
        this.time = time;
        this.emotion = emotion;
        this.reason = reason;
        this.socialSituation = socialSituation;
        this.latitude = latitude;
        this.longitude = longitude;
        this.username = username;
        this.imagePath = imagePath;
    }
    public Mood(){
    }

    /**
     *
     * @return the date when mood event was completed
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date: the current date when mood event was completed. Date format: yyyy-mm-dd
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return the time when this mood event was completed.
     */
    public String getTime() {
        return time;
    }

    /**
     *
     * @param time: the time when this mood event was completed.
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     *
     * @return the emotion that chose from the list of emotions.
     */
    public String getEmotion() {
        return emotion;
    }

    /**
     *
     * @param emotion:  the emotion that chose from the list of emotions.
     */


    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    /**
     *
     * @return the reason of emotional state for this mood event.
     */

    public String getReason() {
        return reason;
    }

    /**
     *
     * @param reason: the reason of emotional state for this mood event.
     */

    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     *
     * @return the social situation for this mood event.
     */

    public String getSocialSituation() {
        return socialSituation;
    }
    /**
     *
     * @param socialSituation: the social situation for this mood event.
     */

    public void setSocialSituation(String socialSituation) {
        this.socialSituation = socialSituation;
    }

    /**
     *
     * @return Latitude where the mood event was completed.
     */

    public double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude: Latitude where the mood event was completed.
     */

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return Longitude where the mood event was completed.
     */

    public double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude: Longitude where the mood event was completed.
     */

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }


    @Override
    public int compareTo(Mood o) {
        if (getTime() == null || o.getDate() == null|| o.getTime() == null|| getDate() == null)
            return 0;
        String s1 = getDate()+getTime();
        String s2 = o.getDate()+o.getTime();
        return s2.compareTo(s1);
    }
}
