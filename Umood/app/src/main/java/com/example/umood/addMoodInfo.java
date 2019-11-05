package com.example.umood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class addMoodInfo extends AppCompatActivity {
    private static final String TAG = "qian-addMood";

    private String emotion;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        Log.d(TAG,"in 1");
        setContentView(R.layout.emotional_state);

        ImageButton happy = findViewById(R.id.happyButton);
        ImageButton sad = findViewById(R.id.sadButton);
        ImageButton confused = findViewById(R.id.confusedButton);
        ImageButton love = findViewById(R.id.loveButton);
        ImageButton cry = findViewById(R.id.cryButton);
        ImageButton bored = findViewById(R.id.boringButton);
        Button next = findViewById(R.id.next);
        Button cancel = findViewById(R.id.cancel);

        happy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Happy";


            }
        });
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Sad";

            }
        });
        confused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Confused";


            }
        });
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Love";

            }
        });
        cry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Cry";

            }
        });
        bored.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emotion = "Bored";
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(emotion.isEmpty()){
                    Toast.makeText(getBaseContext(), "Choose an emotion!", Toast.LENGTH_LONG).show();
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra("Mood", emotion);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
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
