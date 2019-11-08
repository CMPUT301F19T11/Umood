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
     * @param date: Date format: yyyy-mm-dd
     * @param time: Time Format: hh-mm
     * @param emotion: List of Emotion: Sad, Happy, Boring, Anxious, Fearful, Angry
     * @param reason: A string to explain your emotion. Should be no more than 20 chars or 3 words.
     * @param socialSituation: There are 3 social situation: alone, with one other person, with two to several people, or with a crowd
     * @param latitude: a google map data type to indicate where the user is when he/she upload  a new mood event.
     * @param longitude : a google map data type to indicate where the user is when he/she upload  a new mood event.
     */
    public Mood(String date, String time, String emotion, String reason, String socialSituation, double latitude,double longitude){
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
}
