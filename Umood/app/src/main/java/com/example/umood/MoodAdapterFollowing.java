package com.example.umood;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;



public class MoodAdapterFollowing extends RecyclerView.Adapter<MoodAdapterFollowing.ViewHolder> {
    private int resourceId;
    private Context mycontext;
    private ArrayList<Mood> myMoodList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView date;
        TextView time;
        TextView socialSituation;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = (ImageView) view.findViewById(R.id.following_avatar);
            date = (TextView) view.findViewById(R.id.mood_date);
            time =  (TextView) view.findViewById(R.id.mood_time);
            socialSituation = (TextView) view.findViewById(R.id.social_situation);
        }
    }

    public MoodAdapterFollowing(ArrayList<Mood> objects) {
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
        holder.date.setText(mood.getDate());
        holder.time.setText(mood.getTime());
        String text = mood.getSocialSituation();
        if(text.isEmpty())
            text = "No Social Situation Specified";
        holder.socialSituation.setText(text);
        String emo = mood.getEmotion();
        switch(emo){
            case"Happy":
                holder.imageView.setImageResource(R.drawable.ic_happy);
                break;
            case "Sick":
                holder.imageView.setImageResource(R.drawable.ic_sick);
                break;
            case "Scared":
                holder.imageView.setImageResource(R.drawable.ic_scared);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.ic_angry);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailIntent = new Intent(mycontext, DetailMoodFollowingActivity.class);
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

