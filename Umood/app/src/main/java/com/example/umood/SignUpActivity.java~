package com.example.umood;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is SignUpActivity.
 *      This activity is to help user sign up.
 *
 * Corresponding Backlog:
 *      - None
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */

public class SignUpActivity extends AppCompatActivity {
    private static final String TAG = "qian-Signup";
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Map<String, Object> user = new HashMap<>();
    Intent intent;
    User newUser;
    Intent intent2;
    private int result = 0;
    private ImageButton avatar;
    private int picID;
    Context context;
    String imagePath;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.acitivity_signup);

        Button btnRegister = findViewById(R.id.signup);
        avatar = findViewById(R.id.imageButton2);


        context = avatar.getContext();
        imagePath = "avatar0";
        picID = context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
        avatar.setImageResource(picID);
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                result = (result+1)%5;
                switch (result){
                    case 0:
                        imagePath = "avatar0";
                        picID = context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
                        break;
                    case 1:
                        imagePath = "avatar1";
                        picID = context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
                        break;
                    case 2:
                        imagePath = "avatar2";
                        picID = context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
                        break;
                    case 3:
                        imagePath = "avatar3";
                        picID = context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
                        break;
                    default:
                        imagePath = "avatar4";
                        picID = context.getResources().getIdentifier(imagePath, "drawable", context.getPackageName());
                }
                avatar.setImageResource(picID);
            }
        });

        intent  = new Intent(this, MainActivity.class);
        intent2 = new Intent(this, LoginActivity.class);
        ImageButton cancelButton = findViewById(R.id.signup_cancel);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.username);
                String username = editText.getText().toString();
                if(username.isEmpty()){
                    Toast.makeText(getBaseContext(), "Username Cannot be empty!", Toast.LENGTH_LONG).show();
                }
                else {
                    if(imagePath.isEmpty()) {
                        newUser = new User(username);
                    } else{
                        newUser = new User(username, imagePath);
                    }
                    db.collection("users").document(username)
                            .get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.isSuccessful()) {
                                DocumentSnapshot document = task.getResult();
                                if (document.exists()) {
                                    Toast.makeText(getBaseContext(), "The Username has been taken!", Toast.LENGTH_LONG).show();
                                } else {
                                    // Add a new document with a generated ID
                                    String username = newUser.getUsername();
                                    db.collection("users").document(username)
                                            .set(newUser)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    intent.putExtra("User", newUser);
                                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    startActivity(intent);
                                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error writing document", e);
                                                }
                                            });
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
