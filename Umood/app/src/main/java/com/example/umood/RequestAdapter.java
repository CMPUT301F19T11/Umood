package com.example.umood;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.TextView;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class RequestAdapter extends ArrayAdapter<User> {
    private int resourceId;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");
    private static final String TAG = "qian-follow-Request";
    User vuser;

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
        TextView secondName =  view.findViewById(R.id.foll);
        Button button = view.findViewById(R.id.acceptButton);

        UserName.setText(user.getUsername());
        secondName.setText(user.getUsername());


        return view;

    }
}
