package com.example.umood;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
    private RequestAdapter adapter;
    ListView listView;
    private User user;
    private User vuser;

    private UserList UnverifiedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_following_request);

        Intent intent = getIntent();
        UnverifiedUser = (UserList)intent.getSerializableExtra("user_list");
        user = (User)intent.getSerializableExtra("user");
        Log.d(TAG, user.getUsername());


        Log.d(TAG, "second: "+UnverifiedUser.size());
        if(UnverifiedUser.size()>0){
            Log.d(TAG, "wozaizhe");
        }
        listView = findViewById(R.id.friend_request_list);

        adapter = new RequestAdapter(this, R.layout.content_following_request,UnverifiedUser.getList());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int pos = (int) adapterView.getItemIdAtPosition(i);
                vuser = UnverifiedUser.getList().get(pos);
                boolean a = vuser == null;
                Log.d(TAG, ""+a);
                Log.d(TAG, vuser.getUsername());

                collectionReference.document(user.getUsername())
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                User me = document.toObject(User.class);
                                collectionReference.document(user.getUsername()).update("follower", FieldValue.arrayUnion(vuser.getUsername()));
                                collectionReference.document(user.getUsername()).update("unverifiedList", FieldValue.arrayRemove(vuser.getUsername()));
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });
                collectionReference.document(vuser.getUsername())
                        .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                collectionReference.document(vuser.getUsername()).update("following", FieldValue.arrayUnion(user.getUsername()));
                            } else {
                                Log.d(TAG, "No such document");
                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

                UnverifiedUser.removeUser(pos);
                adapter.notifyDataSetChanged();
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,"onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d(TAG,"onRestart");
    }
}
