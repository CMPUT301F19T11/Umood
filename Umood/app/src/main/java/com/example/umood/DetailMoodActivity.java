package com.example.umood;

import android.content.Intent;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is DetailMoodActivity
 *      This activity can check the detail for my own mood event
 *      It can also edit and/or delete this specific mood event
 *
 * Corresponding Backlog:
 *      - US 01.03.01: As a participant, I want to view a given mood event and all its available details.
 *
 *      - US 01.04.01: As a participant, I want to edit the details of a given mood event of mine.
 *
 *      - US 01.05.01: As a participant, I want to delete a given mood event of mine.
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */
public class DetailMoodActivity extends AppCompatActivity {
    private static final String TAG = "qian-follower";
    private Mood mood;
    Intent intent2;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private CollectionReference collectionReference = db.collection("users");

    TextView reason;
    Spinner spinner;
    String newSocialSituation;
    Mood newMood;
    MoodList moodList;
    ArrayList<Mood> moods;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.information);
        Intent intent = getIntent();
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        mood          = (Mood) intent.getSerializableExtra("myMood");
        moodList      = (MoodList) intent.getSerializableExtra("moodList");
        moods         = moodList.getList();
        position      = intent.getIntExtra("position",0);

        TextView date = findViewById(R.id.reason_text);
        TextView time = findViewById(R.id.reason_text2);
        reason = findViewById(R.id.reason_text3);
        spinner = findViewById(R.id.spinner2);
        TextView emotion = findViewById(R.id.textView12);
        ImageView photo = findViewById(R.id.image_import2);

        ImageView geoMap = findViewById(R.id.imageButton4);
        TextView cityNameView = findViewById(R.id.textView10);
        TextView addressNameView = findViewById(R.id.textView11);

        Button saveButton = findViewById(R.id.save_button);

        String e = mood.getEmotion();
        int color;
        switch (e){
            case "Happy":
                color = Color.parseColor("#fdee87");
                break;
            case "Scared":
                color = Color.parseColor("#88c8fa");
                break;
            case "Angry":
                color = Color.parseColor("#ee737a");
                break;
            default:
                color = Color.parseColor("#76dc93");
        }
        emotion.setText(e);
        emotion.setTextColor(color);
        date.setText(mood.getDate());
        time.setText(mood.getTime());
        reason.setText(mood.getReason());

        if(mood.getImagePath()!=null && !mood.getImagePath().isEmpty()){
            photo.setImageBitmap(BitmapFactory.decodeFile(mood.getImagePath()));
        }
        // intent2 = new Intent(this,MainActivity.class);


        String social = mood.getSocialSituation();
        newSocialSituation = social;
        switch(social){
            case "Alone":
                spinner.setSelection(1);
                break;
            case "Along with one person":
                spinner.setSelection(2);
                break;
            case "Along with two to several persons":
                spinner.setSelection(3);
                break;
            case "With a Crowd":
                spinner.setSelection(4);
                break;
            default:
                spinner.setSelection(0);
        }

        double longitude = mood.getLongitude();
        double latitude = mood.getLatitude();

        if(longitude!=0) {
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                String cityName = addresses.get(0).getLocality();
                String address = addresses.get(0).getThoroughfare();
                cityNameView.setText(cityName);
                addressNameView.setText(address);
                geoMap.setImageResource(R.drawable.ic_placeholder_red);

            } catch(IOException exception){
                exception.printStackTrace();
            }
        }



        Button delete = findViewById(R.id.save_button2);
        final Intent intent2  = new Intent(this, MainActivity.class);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                newSocialSituation = ((TextView)view).getText().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newReason = reason.getText().toString();
                newMood = new Mood(mood.getDate(),
                        mood.getTime(),
                        mood.getEmotion(),
                        newReason,
                        newSocialSituation,
                        mood.getLatitude(),
                        mood.getLongitude(),
                        mood.getUsername(),
                        mood.getImagePath());
                moods.set(position,newMood);
                collectionReference.document(mood.getUsername()).update("moodHistory", moods)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d(TAG, "DocumentSnapshot successfully updated!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w(TAG, "Error updating document", e);
                            }
                        });
                intent2.putExtra("User",new User(mood.getUsername()));
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, mood.getUsername());
                collectionReference.document(mood.getUsername()).update("moodHistory", FieldValue.arrayRemove(mood));
                intent2.putExtra("User",new User(mood.getUsername()));
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        });

        ImageButton buttonCancel = findViewById(R.id.add_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
