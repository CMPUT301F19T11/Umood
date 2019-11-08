package com.example.umood;

import com.example.umood.Mood;

import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 *  Test class for Mood class.
 */

public class TestMood {
    public TestMood(){

    }

    @Test
    public void testGetDate(){
        Mood mood = new Mood();
        mood.setDate("2019-11-07");
        assertEquals("2019-11-07", mood.getDate());

    }

    @Test
    public void testSetDate(){
        Mood mood = new Mood();
        mood.setDate("2019-11-07");
        assertEquals("2019-11-07", mood.getDate());

    }


    @Test
    public void testGetLongitude(){
        Mood mood = new Mood();
        mood.setLongitude(-50);
        assertEquals(-50,mood.getLongitude());

    }

    @Test
    public void testSetLongitude(){
        Mood mood = new Mood();
        mood.setLongitude(-50);
        assertEquals(-50,mood.getLongitude());
    }

    @Test
    public void testGetLatitude(){
        Mood mood = new Mood();
        mood.setLatitude(-50);
        assertEquals(-50,mood.getLatitude());

    }

    @Test
    public void testSetLatitude(){
        Mood mood = new Mood();
        mood.setLatitude(-50);
        assertEquals(-50,mood.getLatitude());
    }


    @Test
    public void testGetReason(){
        Mood mood = new Mood();
        mood.setReason("pass the couse");
        assertEquals("pass the couse", mood.getReason());
    }

    @Test
    public void testSetReason(){
        Mood mood = new Mood();
        mood.setReason("pass the couse");
        assertEquals("pass the couse", mood.getReason());
    }

    @Test
    public void testGetSocialSituation(){
        Mood mood = new Mood();
        mood.setSocialSituation("Alone");
        assertEquals("Alone", mood.getSocialSituation());
    }

    @Test
    public void testSetSocialSituation(){
        Mood mood = new Mood();
        mood.setSocialSituation("Alone");
        assertEquals("Alone", mood.getSocialSituation());
        mood.setSocialSituation("with one other person");
        assertEquals("with one other person", mood.getSocialSituation());
    }

    @Test
    public void testGetTime(){
        Mood mood = new Mood();
        mood.setTime("11:39");
        assertEquals("11:39",mood.getTime());
    }

    @Test
    public void testSetTime(){
        Mood mood = new Mood();
        mood.setTime("11:39");
        assertEquals("11:39",mood.getTime());
    }




}
