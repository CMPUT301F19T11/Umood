package com.example.umood;

import android.Manifest;
import android.content.Intent;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class DetailMoodActivity extends AppCompatActivity {
    private static final String TAG = "qian-follower";
    private Mood mood;
    Intent intent2;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Geocoder geocoder ;

    private CollectionReference collectionReference = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.information);
        Intent intent = getIntent();
        geocoder = new Geocoder(this, Locale.getDefault());
        mood = (Mood)intent.getSerializableExtra("myMood");

        TextView date = findViewById(R.id.reason_text);
        TextView time = findViewById(R.id.reason_text2);
        TextView reason = findViewById(R.id.reason_text3);
        TextView emotion = findViewById(R.id.textView12);
        ImageView photo = findViewById(R.id.image_import2);

        ImageView geoMap = findViewById(R.id.imageButton4);
        TextView cityNameView = findViewById(R.id.textView10);
        TextView addressNameView = findViewById(R.id.textView11);

        TextView socialSituation = findViewById(R.id.spinner2);

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

        //Spinner spinner = findViewById(R.id.spinner2);


        if(mood.getImagePath()!=null && !mood.getImagePath().isEmpty()){
            photo.setImageBitmap(BitmapFactory.decodeFile(mood.getImagePath()));
        }
        intent2 = new Intent(this,MainActivity.class);


        String social = mood.getSocialSituation();
        if(social == null){
            socialSituation.setText("No social situation");
        }
        socialSituation.setText(social);



        /**
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
         */

        double longitude = mood.getLongitude();
        double latitude = mood.getLatitude();

        if(longitude!=0) {
            try {
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                String cityName = addresses.get(0).getLocality();
                String address = addresses.get(0).getThoroughfare();
                cityNameView.setText(cityName);
                addressNameView.setText(address);
                geoMap.setImageResource(R.drawable.place);

            } catch(IOException exception){
                exception.printStackTrace();
            }
        }

        Button delete = findViewById(R.id.save_button2);

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

        Button editButton = findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DetailMoodActivity.this, EditMoodActivity.class));

            }
        });



    }
}
