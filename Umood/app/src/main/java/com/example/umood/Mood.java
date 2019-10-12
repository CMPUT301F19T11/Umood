package com.example.umood;

public class Mood {
    private User user;
    private String date;
    private String time;
    private String emotion;
    private String reason;
    private String socialSituation;
    private String location;


    Mood(User user,String date, String time, String emotion, String reason, String socialSituation, String location){
        this.user = user;
        this.date = date;
        this.time = time;
        this.emotion = emotion;
        this.reason = reason;
        this.socialSituation = socialSituation;
        this.location = location;

    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}
