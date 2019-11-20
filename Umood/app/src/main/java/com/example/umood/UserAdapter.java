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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

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
        TextView last_mood;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView =  view.findViewById(R.id.following_avatar);
            username =  view.findViewById(R.id.following_name);
            last_mood = view.findViewById(R.id.following_name2);

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
        Glide.with(mycontext)
            .load(R.drawable.zeldaflat)
            .transform(new CircleCrop())
            .into(holder.imageView);
        String text = "[Last Mood Event: ";
        ArrayList<Mood> moodList = user.getMoodHistory();
        if(moodList.size()==0){
            text = "[No Mood Event So Far]";
        }
        else {
            String text2 = moodList.get(moodList.size() - 1).getDate();
            text = text + text2 + "]";
        }
        holder.last_mood.setText(text);

    }
    @Override
    public int getItemCount(){
        return myUserList.size();
    }
}
