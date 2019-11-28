package com.example.umood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is LogInActivity
 *      This activity is to help user log in.
 *
 * Corresponding Backlog:
 *      - None
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */
public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "qian";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    CollectionReference collectionReference = db.collection("users");

    Intent intentSignUp;
    Intent intentSignIn;
    // Create a new user with a first and last name
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        Button btnLogin= findViewById(R.id.cancel2);
        intentSignIn = new Intent(this, MainActivity.class);
        intentSignUp = new Intent(this, SignUpActivity.class);
        TextView tv = (TextView) findViewById(R.id.signup);

        tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                startActivity(intentSignUp);
                return true;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.username);
                final String username = editText.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(getBaseContext(),"The username cannot be empty",Toast.LENGTH_LONG).show();
                }
                else{
                    collectionReference.document(username)
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    User user = document.toObject(User.class);
                                    intentSignIn.putExtra("User",user);
                                    intentSignIn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intentSignIn);
                                } else {
                                    Log.d(TAG, "No such document");
                                    Toast.makeText(getBaseContext(),"The username does not exist",Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Log.d(TAG, "get failed with ", task.getException());
                            }
                        }
                    });
                }
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
