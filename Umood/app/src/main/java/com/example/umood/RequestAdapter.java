package com.example.umood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class RequestAdapter extends ArrayAdapter<User> {
    private int resourceId;
    public RequestAdapter(Context context, int textViewResourceId, ArrayList<User> objects){
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        User user  = getItem(position);

        View view = LayoutInflater.from(getContext()).inflate(resourceId,
                parent,
                false
        );

        TextView UserName =  view.findViewById(R.id.following_username);

        UserName.setText(user.getUsername());

        return view;

    }
}