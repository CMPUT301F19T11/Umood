package com.example.umood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/** * ------------------------------------------------------------------------------------------------------------
 * Description for this file:
 *      This is DetailMoodActivity
 *      This activity can check the detail for the mood event of your following user
 *
 * Corresponding Backlog:
 *      - US 01.03.01: As a participant, I want to view a given mood event and all its available details.
 *
 * Last Modified:
 *      Nov 21 by Qian Yu
 * ------------------------------------------------------------------------------------------------------------
 */
public class DetailMoodFollowingActivity extends AppCompatActivity {
    private static final String TAG = "qian-follower";
    Geocoder geocoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_detail_mood_following);

        geocoder = new Geocoder(this, Locale.getDefault());

        Intent intent = getIntent();
        Mood mood = (Mood)intent.getSerializableExtra("myMood");


        TextView date = findViewById(R.id.reason_text);
        TextView time = findViewById(R.id.reason_text2);
        TextView reason = findViewById(R.id.reason_text5);
        TextView emotion = findViewById(R.id.textView12);
        ImageView photo = findViewById(R.id.image_import2);

        ImageView geoMap = findViewById(R.id.imageButton4);
        TextView cityNameView = findViewById(R.id.textView10);
        TextView addressNameView = findViewById(R.id.textView11);

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

        emotion.setText(e);
        emotion.setTextColor(color);
        date.setText(mood.getDate());
        time.setText(mood.getTime());
        reason.setText(mood.getReason());


        TextView spinner = findViewById(R.id.reason_text4);
        spinner.setText(mood.getSocialSituation());


        if(mood.getImagePath()!=null && !mood.getImagePath().isEmpty()){
            photo.setImageBitmap(BitmapFactory.decodeFile(mood.getImagePath()));
        }

        ImageButton buttonCancel = findViewById(R.id.add_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Log.d(TAG, "onCreate:");

    }
}

