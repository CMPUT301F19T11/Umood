package com.example.umood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.FieldValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddMoodFirstActivity extends AppCompatActivity {
    String emotion;
    int PICK_MOOD_REQUEST = 2;
    private static final String TAG = "qian-map";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_add_mood_first);

        ImageButton happy = findViewById(R.id.happyButton2);
        ImageButton sick = findViewById(R.id.sickButton2);
        ImageButton angry = findViewById(R.id.angryButton2);
        ImageButton scared = findViewById(R.id.scaredButton2);
        // 4 emotions:
        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Happy";
            }
        });
        sick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Sick";
            }
        });
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Angry";
            }
        });
        scared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Scared";

            }
        });

        final Intent intent = new Intent(this,addMoodInfo.class);
        Button next = findViewById(R.id.save_button3);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emotion == null){
                    Toast.makeText(getBaseContext(), "Choose an emotion!", Toast.LENGTH_LONG).show();
                }
                else {
                    intent.putExtra("Emotion",emotion);
                    startActivityForResult(intent, PICK_MOOD_REQUEST);
                }
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == PICK_MOOD_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {
                String emotion = data.getStringExtra("Mood");
                String situation = data.getStringExtra("SocialSituation");
                String reason = data.getStringExtra("Reason");
                String imagePath = data.getStringExtra("Path");
                double longitude = data.getDoubleExtra("Longitude",0);
                double latitude = data.getDoubleExtra("Latitude",0);
                String currentDate = data.getStringExtra("Date");
                String currentTime =  data.getStringExtra("Time");

                if(reason==null || reason.isEmpty())
                    reason = "";
                if(imagePath ==null || imagePath.isEmpty())
                    imagePath = "";

                Intent intent = new Intent();
                intent.putExtra("Time",currentTime);
                intent.putExtra("Date",currentDate);
                intent.putExtra("Reason",reason);
                intent.putExtra("SocialSituation", situation);
                intent.putExtra("Mood", emotion);
                intent.putExtra("Path",imagePath);
                intent.putExtra("Longitude",longitude);
                intent.putExtra("Latitude",latitude);

                setResult(RESULT_OK, intent);
                finish();
            }
            else
                Log.d(TAG, "Failed to get result!");
        }
        else
            Log.d(TAG, "What happened??");
    }

}
