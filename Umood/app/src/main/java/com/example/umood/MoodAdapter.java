package com.example.umood;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.umood.Mood;

import java.util.ArrayList;
import java.util.List;



public class MoodAdapter extends RecyclerView.Adapter<MoodAdapter.ViewHolder> {
    private int resourceId;
    private Context mycontext;
    private ArrayList<Mood> myMoodList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView dateAndTime;
        TextView socialSituation;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = (ImageView) view.findViewById(R.id.following_avatar);
            dateAndTime = (TextView) view.findViewById(R.id.date_and_time);
            socialSituation = (TextView) view.findViewById(R.id.social_situation);
        }
    }

    public MoodAdapter(ArrayList<Mood> objects) {
        myMoodList = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mycontext == null) {
            mycontext = parent.getContext();
        }
        View view = LayoutInflater.from(mycontext).inflate(R.layout.mood_item,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Mood mood = myMoodList.get(position);
        String text1 = mood.getDate()+mood.getTime();
        holder.dateAndTime.setText(text1);
        holder.socialSituation.setText(mood.getSocialSituation());
        String emo = mood.getEmotion();
        switch(emo){
            case"Happy":
                holder.imageView.setImageResource(R.drawable.happy);
                break;
            case "Sick":
                holder.imageView.setImageResource(R.drawable.sick);
                break;
            case "Scared":
                holder.imageView.setImageResource(R.drawable.scared);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.angry);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(mycontext, DetailMoodActivity.class);
                detailIntent.putExtra("myMood", mood);
                mycontext.startActivity(detailIntent);
            }
        });
    }
    @Override
    public int getItemCount(){
        return myMoodList.size();
    }

}


/*

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
*/

