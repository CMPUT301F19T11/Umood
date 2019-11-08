package com.example.umood;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private int resourceId;
    private Context mycontext;
    private ArrayList<User> myUserList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView username;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = (ImageView) view.findViewById(R.id.following_avatar);
            username = (TextView) view.findViewById(R.id.following_name);

        }
    }

    public UserAdapter(ArrayList<User> objects) {
        myUserList = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mycontext == null) {
            mycontext = parent.getContext();
        }
        View view = LayoutInflater.from(mycontext).inflate(R.layout.user_item,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = myUserList.get(position);
        holder.username.setText(user.getUsername());
        holder.imageView.setImageResource(R.drawable.zeldaflat);

    }
    @Override
    public int getItemCount(){
        return myUserList.size();
    }
}
