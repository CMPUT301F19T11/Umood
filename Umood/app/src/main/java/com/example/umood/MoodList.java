package com.example.umood;

import java.io.Serializable;
import java.util.ArrayList;

public class MoodList implements Serializable {
    ArrayList<Mood> list;

    public MoodList() {
        list = new ArrayList<>();
    }
    public MoodList(ArrayList<Mood> list) {
        this.list = list;
    }


    public ArrayList<Mood>  getList() {
        return list;
    }
    public void addUser(Mood mood) {
        list.add(mood);
    }
    public void remove(int pos) {
        list.remove(pos);
    }
    public int size() {
        return list.size();
    }
}
