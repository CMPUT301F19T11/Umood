package com.example.umood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is AddFollowingActivity
 *      You can enter a username,
 *      and the server will automatically send a following request to that user
 *      once that user accept your following request
 *      He/She will show in your following list
 *
 * Corresponding Backlog:
 *      - US 05.01.01: As a participant, I want to ask another participant to follow their most recent mood event.
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */

public class AddFollowingActivity extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");
    private static final String TAG = "qian-addFollow";
    EditText addFollowing;
    User me;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.add_following);

        //XML touchable
        addFollowing = findViewById(R.id.add_following);
        Button confirm = findViewById(R.id.confirm);
        ImageButton back = findViewById(R.id.cancel2);

        Intent intent = getIntent();
        me = (User) intent.getSerializableExtra("user");



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = addFollowing.getText().toString();
                Log.d(TAG, username);
                if(!username.isEmpty()) {
                    collectionReference.document(username)
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    User user = document.toObject(User.class);
                                    user.addUnverifiedUser(me.getUsername());
                                    collectionReference.document(username).update("unverifiedList", FieldValue.arrayUnion(me.getUsername()));
                                    Toast.makeText(getBaseContext(),"Following request has been sent!!",Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getBaseContext(),"The username does not exist!!",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(getBaseContext(),"The username Cannot Be Empty!!",Toast.LENGTH_LONG).show();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
