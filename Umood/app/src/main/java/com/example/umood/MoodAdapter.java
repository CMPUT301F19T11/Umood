package com.example.umood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;


import com.example.umood.Mood;

import java.util.ArrayList;
import java.util.List;

public class MoodAdapter extends ArrayAdapter<Mood> {
    private int resourceId;
    public MoodAdapter(Context context, int textViewResourceId, ArrayList<Mood> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Mood mood  = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId,
                        parent,
                        false
                );

        TextView dateName =  view.findViewById(R.id.date);
        TextView timeName =  view.findViewById(R.id.time);

        String strDate = mood.getDate()+"          ";
        String strTime = mood.getTime();

        dateName.setText(strDate);
        timeName.setText(strTime);

        return view;

    }
}
