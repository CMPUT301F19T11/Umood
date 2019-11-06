package com.example.umood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class FollowingRequest extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");
    private static final String TAG = "qian-follow-Request";
    ArrayList<User> UnverifiedUser;
    private RequestAdapter adapter;
    ListView listView;

    User me;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_following_request);

        Intent intent = getIntent();
        me = (User)intent.getSerializableExtra("user");
        ArrayList<String> unverifyList = me.getUnverifiedList();
        UnverifiedUser = new ArrayList<>();

        if(unverifyList!=null) {
            for (final String username : unverifyList) {
                collectionReference.document(username)
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                Log.d(TAG, username);
                                User user = document.toObject(User.class);
                                UnverifiedUser.add(user);
                            } else {
                                Toast.makeText(getBaseContext(), "The username does not exist!!", Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
            }
        }
        if(UnverifiedUser.size()>0){
            listView = findViewById(R.id.friend_request_list);
            listView.setAdapter(adapter);
            adapter = new RequestAdapter(this, R.layout.content_following_request,UnverifiedUser);
            listView.setAdapter(adapter);
        }
    }
}
