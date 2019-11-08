package com.example.umood;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;


public class DetailMoodActivity extends AppCompatActivity {
    private static final String TAG = "qian-follower";
    private Mood mood;
    Intent intent2;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("users");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.information);
        Intent intent = getIntent();
        mood = (Mood)intent.getSerializableExtra("myMood");


        TextView date = findViewById(R.id.reason_text);
        TextView time = findViewById(R.id.reason_text2);
        TextView reason = findViewById(R.id.reason_text3);

        date.setText(mood.getDate());
        time.setText(mood.getTime());
        reason.setText(mood.getReason());
        Spinner spinner = findViewById(R.id.spinner2);
        intent2 = new Intent(this,MainActivity.class);


        String social = mood.getSocialSituation();
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

        Button delete = findViewById(R.id.save_button);

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
