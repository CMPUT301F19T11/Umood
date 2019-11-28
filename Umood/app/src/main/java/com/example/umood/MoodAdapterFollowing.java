package com.example.umood;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class MoodAdapterFollowing extends RecyclerView.Adapter<MoodAdapterFollowing.ViewHolder> {
    private int resourceId;
    private Context mycontext;
    private ArrayList<Mood> myMoodList;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");
    ViewHolder h;
    String TAG = "qian";
    CountDownLatch done = new CountDownLatch(1);

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView date;
        TextView time;
        TextView socialSituation;
        TextView gap;
        ImageView avatar;

        public ViewHolder(View view) {
            super(view);
            cardView = (CardView) view;
            imageView = (ImageView) view.findViewById(R.id.following_emotion);
            avatar = (ImageView) view.findViewById(R.id.following_avatar);
            gap = view.findViewById(R.id.time);

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
        View view = LayoutInflater.from(mycontext).inflate(R.layout.mood_following_item,
                parent,
                false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Mood mood = myMoodList.get(position);
        Log.d(TAG, "onBindViewHolder: "+mood.getUsername());
        holder.date.setText(mood.getUsername());
        holder.time.setText(mood.getEmotion());
        holder.gap.setText(mood.getTime());

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
        String name = mood.getUsername();

        collectionReference.document(name).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        User user = document.toObject(User.class);
                        Context context = holder.avatar.getContext();
                        Log.d(TAG, "onComplete: "+user.getAvatar());
                        int picID =  context.getResources().getIdentifier(user.getAvatar(), "drawable", context.getPackageName());
                        holder.avatar.setImageResource(picID);
                    } else {
                    }
                } else {
                }
            }
        });


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

